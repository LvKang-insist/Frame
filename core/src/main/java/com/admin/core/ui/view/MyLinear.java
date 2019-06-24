package com.admin.core.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.LinearLayoutCompat;

/**
 * @author Lv
 * Created at 2019/6/23
 */
public class MyLinear extends LinearLayoutCompat {
    Paint mPaint;
    Path mPath;

    public MyLinear(Context context) {
        this(context, null);
    }

    public MyLinear(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLinear(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPath = new Path();
        setWillNotDraw(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.parseColor("#FDFFFF"));
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        //弧度的长
        int rWidth = width/8;

        //弧度的控制点
        int x = 50;
        int y = 50;

        mPath.moveTo(0,height);
        mPath.quadTo(x, y, rWidth, 20);
        mPath.rLineTo(width-rWidth*2,0);
        mPath.quadTo(width-x, y,width, height);
        canvas.drawPath(mPath, mPaint);
    }
}
