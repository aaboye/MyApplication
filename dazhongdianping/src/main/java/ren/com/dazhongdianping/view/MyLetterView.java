package ren.com.dazhongdianping.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import ren.com.dazhongdianping.R;

/**
 * Created by tarena on 2017/6/23.
 */

public class MyLetterView extends View {
    private String[] letters = {"热门", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    Paint paint;
    OnTouchLetterListener listener;
    int letterColor;


    public MyLetterView(Context context, AttributeSet attrs) {
        super(context, attrs);

//        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.MyLetterView);
//        letterColor = t.getColor(R.styleable.MyLetterView_letter_color,Color.BLACK);
//        t.recycle();


        initPaint();
    }

    public void setOnTouchTetterListener(OnTouchLetterListener listener) {
        this.listener = listener;
    }

    //画笔初始化
    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //绘制文字的大小
        float size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics());
        paint.setTextSize(size);
       // paint.setColor(letterColor);

    }

    /**
     * 设定自定义view尺寸
     * 此方法不一定需要重写
     * view的onmeasure已经有了设定尺寸的方法，可以使用
     * 只有当view中的方法不满足使用要求时要重写
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //改写时一定要保留View的onMeasure方法调用
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //进行针对WRAP_CONTENT的改写
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if(mode == MeasureSpec.AT_MOST){
            int lPadding = getPaddingLeft();
            int rPadding = getPaddingRight();
            int contentWidth = 0;
            for(int i=0;i<letters.length;i++){
                String letter = letters[i];

                Rect bounds = new Rect();
                paint.getTextBounds(letter,0,letter.length(),bounds);

                if(bounds.width()>contentWidth){
                    contentWidth = bounds.width();
                }

            }
            int size = lPadding + contentWidth+rPadding;
            setMeasuredDimension(size,MeasureSpec.getSize(heightMeasureSpec));

        }


    }

    /**
     * 摆放其他view
     *
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * 此方法必须重写
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        for (int i = 0; i < letters.length; i++) {
            String letter = letters[i];
            //获取文字的边界大小
            Rect bounds = new Rect();
            paint.getTextBounds(letter, 0, letter.length(), bounds);

            float x = width / 2 - bounds.width() / 2;
            float y = height / letters.length / 2 + bounds.height() / 2 + i * height / letters.length;
            canvas.drawText(letter, x, y, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //改变背景
                setBackgroundColor(Color.TRANSPARENT);
                if (listener != null) {
                    //手指按下或移动时自定义view顶的距离
                    float y = event.getY();
                    //根据距离y换算成文字的下标值
                    int idex = (int) ((y * letters.length) / getHeight());
                    if (idex >= 0 && idex < letters.length) {
                        String letter=letters[idex];
                        listener.onTouchLetter(this,letter );
                    }
                }
                break;
            default:
                setBackgroundColor(Color.TRANSPARENT);
                break;
        }
        return true;
    }

    public interface OnTouchLetterListener {
        void onTouchLetter(MyLetterView view, String letter);
    }
}
