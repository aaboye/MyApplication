package com.ren.tarena.xiaohua.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.LruCache;
import android.widget.ImageView;


import com.ren.tarena.xiaohua.util.StreamUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tarena on 2017/6/5.
 * 自定义一个音乐图片加载器
 * 实现音乐图片的本地缓存的处理
 */

public class MyImageLoader {
    /**
     * 如果我们需要加载一个音乐的专辑图片，可以先从内存缓存中去查找图片，
     * 如果内存缓存中没有，再从文件缓存中查找
     * 如果文件缓存中也没有，活命我们还没有从网上加载过要是用的图片
     * 此时我们在从网络上加载，加载完成后在分别把图片缓存在内存或文件中
     * 以便下次使用时直接从缓存中拿到
     */
    //强引用里面的数据永远不会被垃圾回收器回收
    //软引用里存的数据在android3.0之后变得不可靠
   /*
    lrucache 集合最近最少使用的数据会被回收掉
    存在于该集合中的数据都是强引用
    我们可以在这样的集合中设置最大的存储空间，当空间填满是数据会根据
    最近最少使用的算法回收掉
     */

    public static LruCache<String, Bitmap> lruCacheMemory = null;

    static {
        //设置内存的最大缓存区域
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
       lruCacheMemory= new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //图片高度和图片水平方向的字节数
                return value.getHeight() * value.getRowBytes();
            }
        };

    }

    /**
     * @param context
     * @param imageView 图片控件
     * @param imageUrl  网络图片路径
     */
    public static void setBitmapFromCache(Context context, ImageView imageView, String imageUrl) {
        //从缓存中获得的图片
        Bitmap bitmap = null;
        //判断如果没有提供图片的路径就直接返回
        if (TextUtils.isEmpty(imageUrl)) {
            return;
        }
        //先从内存中查找有没有要加载的图片，如果有直接使用
        bitmap = getBitmapFromMemory(imageUrl);
        if (bitmap != null) {
            //如果有直接应用图片
            imageView.setImageBitmap(bitmap);
            return;
        }
        bitmap = getBitmapFromFile(context, imageUrl);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }

        loadBitmapFromHttp(context, imageView, imageUrl);


    }

    /**
     * @param context
     * @param imageView 设置图片的控件
     * @param imageUrl  所需加载图片的路径
     */
    private static void loadBitmapFromHttp(Context context, ImageView imageView, String imageUrl) {
        //要访问网络，实现异步加载
        ImageAsyncTask task = new ImageAsyncTask(context, imageView);
        //启动异步任务
        task.execute(imageUrl);

    }

    //实现异步图片加载的任务类
    private static class ImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
        Context context;
        ImageView imageView;
        private Bitmap bitmap;

        ImageAsyncTask(Context context, ImageView imageView) {
            this.context = context;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String path = params[0];
            //构建URL对象
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setDoInput(true);

                connection.connect();

                int statusCode = connection.getResponseCode();
                if (statusCode == 200) {
                    //获得响应的结果
                    InputStream is = connection.getInputStream();
                    //设置压缩图片
                    //setCompressBitmap(is);


                    bitmap = BitmapFactory.decodeStream(is);
                    //判断该图片是否加载成功
                    if (bitmap != null) {
                        //将图片缓存到内存中
                        lruCacheMemory.put(path, bitmap);
                        //将图片缓存到文件中
                        saveBitmapToFile(context,path, bitmap);
                        return bitmap;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }
        //设置压缩图片
        private void setCompressBitmap(InputStream is) {
            //把输入流转成一个字节数组
            byte[] datas= StreamUtil.creatBytes(is);
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inJustDecodeBounds=true;
            bitmap=BitmapFactory.decodeByteArray(datas,0,datas.length,options);
            //获得图片的宽度和高度
            int outWidth=options.outWidth;
            int outHeight=options.outHeight;
            //设置压缩后的高度和宽度
            int targetWidth=65;
            int targetHeight=65;
            //计算宽度方向上的压缩比例
            int blw=outWidth/targetWidth;
            //计算高度方向上的压缩比例
            int blh=outHeight/targetHeight;
            int bl=blw>blh?blw:blh;
            if (bl<=0){
                bl=1;
            }
            options.inSampleSize=bl;
            options.inJustDecodeBounds=false;
            bitmap= BitmapFactory.decodeByteArray(datas,0,datas.length,options);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //把图片应用到控件上
            imageView.setImageBitmap(bitmap);
        }


    }
    private static void saveBitmapToFile(Context context, String path, Bitmap bitmap) {
        File cacheDir=context.getCacheDir();
        if (!cacheDir.exists()){
            cacheDir.mkdir();
        }
        try {
            //获得要缓存的文件的名字
            String fileName = path;
            //创建一个文件对象
            File file = new File(cacheDir, fileName);
            OutputStream os=new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,os);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static Bitmap getBitmapFromFile(Context context, String imageUrl) {
        Bitmap bitmap = null;
        //获得要查找的文件的名字
        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        //获得应用缓存目录
        File cacheDir = context.getCacheDir();
        //遍历该缓存目录下的所有文件，判断是否有要是用的图片
        if (cacheDir != null) {
            //获得缓存目录下所有文件对象构成的集合
            File[] files = cacheDir.listFiles();
            for (File file : files) {
                String name = file.getName();
                if (name.equals(fileName)) {
                    //查找到要是用的图片
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    return bitmap;
                }
            }
        }

        return bitmap;
    }

    private static Bitmap getBitmapFromMemory(String imageUrl) {
        Bitmap bitmap = null;
        bitmap = lruCacheMemory.get(imageUrl);
        return bitmap;
    }


}
