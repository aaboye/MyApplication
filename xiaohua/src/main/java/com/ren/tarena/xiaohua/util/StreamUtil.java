package com.ren.tarena.xiaohua.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by tarena on 2017/6/1.
 */

public class StreamUtil {

    private static BufferedReader reader;

    public  static String createStr(InputStream is){
        String jsonStr="";
        try {
            reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
            String line="";
            StringBuilder builder=new StringBuilder();
            while ((line=reader.readLine())!=null){
                builder.append(line);
            }
            jsonStr=builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonStr;
    }

    public static byte[] creatBytes(InputStream is) {
        ByteArrayOutputStream os = null;
        byte[] dates = null;

        //娴佺殑鐩爣鏄唴瀛樻暟缁?
        os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        try {
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        dates=os.toByteArray();
        return dates;

    }

}
