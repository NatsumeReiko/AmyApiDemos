package com.example.android.apis.viewtest.otherclass;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class CropImageView extends ImageView implements View.OnTouchListener {

    // X coordinate of the touched position
    private float mX;

    // Y Coordinate of the touched position
    private float mY;
    int w, h;


    public CropImageView(Context context) {
        super(context);
        initilizeView();

    }

    public CropImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initilizeView();

    }

    public CropImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initilizeView();
    }

    private Bitmap mSrcB;
    private Bitmap mDstB;
    int index = 0;

    Paint backgroundPaint;
    Paint circlePaint;

    private void initilizeView() {

        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(0xaa000000);
        backgroundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        w = 500;
        h = 500;
        mSrcB = makeSrc();
        mDstB = makeDst();


        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setShader(null);

        // draw the src/dst example into our offscreen bitmap
        int sc = canvas.saveLayer(mX, mY, mX + w, mY + h, null,
                Canvas.MATRIX_SAVE_FLAG |
                        Canvas.CLIP_SAVE_FLAG |
                        Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                        Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                        Canvas.CLIP_TO_LAYER_SAVE_FLAG);
        canvas.translate(mX, mY);
        canvas.drawBitmap(mDstB, 0, 0, paint);
        if (index == sModes.length) {
            index = 0;
        }
//        Toast.makeText(this.getContext(), "index:" + index, Toast.LENGTH_SHORT).show();
//        paint.setXfermode(sModes[index++]);
//        canvas.drawBitmap(mSrcB, 0, 0, paint);
        canvas.drawRect(0, 0, getWidth(), getHeight(), backgroundPaint);


        paint.setXfermode(null);
        canvas.restoreToCount(sc);

    }

    // create a bitmap with a circle, used for the "dst" image
    Bitmap makeDst() {
        int w, h;
        w = h = 200;


        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFFFFCC44);
        c.drawOval(new RectF(mX, mY, w * 3 / 4, h * 3 / 4), p);
        return bm;
    }

    // create a bitmap with a rect, used for the "src" image
    Bitmap makeSrc() {

        Bitmap bm = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xaa000000);
        c.drawRect(0, 0, getWidth(), getHeight(), p);
        return bm;
    }

    private static final Xfermode[] sModes = {
            new PorterDuffXfermode(PorterDuff.Mode.CLEAR),
            new PorterDuffXfermode(PorterDuff.Mode.SRC),
            new PorterDuffXfermode(PorterDuff.Mode.DST),
            new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER),
            new PorterDuffXfermode(PorterDuff.Mode.DST_OVER),
            new PorterDuffXfermode(PorterDuff.Mode.SRC_IN),
            new PorterDuffXfermode(PorterDuff.Mode.DST_IN),
            new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT),//ok
            new PorterDuffXfermode(PorterDuff.Mode.DST_OUT),
            new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP),
            new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP),
            new PorterDuffXfermode(PorterDuff.Mode.XOR),
            new PorterDuffXfermode(PorterDuff.Mode.DARKEN),
            new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN),
            new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY),
            new PorterDuffXfermode(PorterDuff.Mode.SCREEN)
    };

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Getting the X-Coordinate of the touched position
                mX = 200;

                // Getting the Y-Coordinate of the touched position
                mY = 200;
                invalidate();
                break;
        }
        return true;
    }
}
