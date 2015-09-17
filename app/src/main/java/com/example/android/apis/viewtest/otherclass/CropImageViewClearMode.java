package com.example.android.apis.viewtest.otherclass;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class CropImageViewClearMode extends ImageView implements View.OnTouchListener {

    private Paint backgroundPaint;
    private Paint circlePaint;

    // X Y coordinate of the touched position
    private float coordinateX, coordinateY, circleRadius;

    public CropImageViewClearMode(Context context) {
        super(context);
        init();

    }

    public CropImageViewClearMode(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public CropImageViewClearMode(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        backgroundPaint = new Paint();
        backgroundPaint.setColor(0xcc000000);

        circlePaint = new Paint();
        circlePaint.setColor(getResources().getColor(android.R.color.transparent));
        circlePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, getWidth(), getHeight(), backgroundPaint);
        canvas.drawCircle(coordinateX + circleRadius / 2, coordinateY + circleRadius / 2, circleRadius, circlePaint);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
