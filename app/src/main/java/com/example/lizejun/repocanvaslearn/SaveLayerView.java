package com.example.lizejun.repocanvaslearn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

public class SaveLayerView extends View {

    private int mRadius;
    private Paint mBlueP;
    private Paint mRedP;
    private Paint mGreenP;
    private Xfermode mDstOverXfermode;
    private Paint mPaint;

    public SaveLayerView(Context context) {
        super(context);
        init();
    }

    public SaveLayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SaveLayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mRadius = 100;
        mBlueP = new Paint();
        mGreenP = new Paint();
        mRedP = new Paint();
        mBlueP.setColor(getResources().getColor(android.R.color.holo_blue_light));
        mRedP.setColor(getResources().getColor(android.R.color.holo_red_light));
        mGreenP.setColor(getResources().getColor(android.R.color.holo_green_light));
        mDstOverXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OVER);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //useSaveLayer(canvas);
        //useSaveLayerAlpha(canvas);
        useSaveLayerHasAlphaOrFullColor(canvas);
    }

    private void saveMatrix(Canvas canvas) {
        //绘制蓝色矩形
        mPaint.setColor(getResources().getColor(android.R.color.holo_blue_light));
        canvas.drawRect(0, 0, 200, 200, mPaint);
        //保存
        canvas.save();
        //裁剪画布,并绘制红色矩形
        mPaint.setColor(getResources().getColor(android.R.color.holo_red_light));
        //平移.
        //canvas.translate(100, 0);
        //缩放.
        //canvas.scale(0.5f, 0.5f);
        //旋转
        //canvas.rotate(-45);
        //倾斜
        canvas.skew(0, 0.5f);
        canvas.drawRect(0, 0, 200, 200, mPaint);
        //恢复画布
        canvas.restore();
        //绘制绿色矩形
        mPaint.setColor(getResources().getColor(android.R.color.holo_green_light));
        canvas.drawRect(0, 0, 50, 200, mPaint);
    }

    private void saveClip(Canvas canvas) {
        //绘制蓝色矩形
        mPaint.setColor(getResources().getColor(android.R.color.holo_blue_light));
        canvas.drawRect(0, 0, 200, 200, mPaint);
        //保存.
        canvas.save();
        //裁剪画布,并绘制红色矩形
        mPaint.setColor(getResources().getColor(android.R.color.holo_red_light));
        canvas.clipRect(150, 0, 200, 200);
        canvas.drawRect(0, 0, 200, 200, mPaint);
        //恢复画布
        canvas.restore();
        //绘制绿色矩形
        mPaint.setColor(getResources().getColor(android.R.color.holo_green_light));
        canvas.drawRect(0, 0, 50, 200, mPaint);
    }

    private void useSaveLayer(Canvas canvas) {
        //先划一个蓝色的圆形.
        canvas.drawCircle(mRadius, mRadius, mRadius, mBlueP);
        //这里产生一个新的图层
        canvas.saveLayer(0, 0, mRadius + mRadius, mRadius + mRadius, null);
        //现先在该图层上画一个矩形
        canvas.drawRect(mRadius, mRadius, mRadius + mRadius, mRadius + mRadius, mGreenP);
        //设为取下面的部分
        mRedP.setXfermode(mDstOverXfermode);
        //再画一个圆形
        canvas.drawCircle(mRadius, mRadius, mRadius/2, mRedP);
    }

    private void useSaveLayerAlpha(Canvas canvas) {
        //先划一个蓝色的圆形.
        canvas.drawCircle(mRadius, mRadius, mRadius, mBlueP);
        //canvas.save();
        //这里产生一个新的图层
        canvas.saveLayerAlpha(0, 0, mRadius + mRadius, mRadius + mRadius, 128);
        //现先在该图层上画一个矩形
        canvas.drawRect(mRadius, mRadius, mRadius + mRadius, mRadius + mRadius, mGreenP);
    }

    private void useSaveLayerHasAlphaOrFullColor(Canvas canvas) {
        //先划一个蓝色的圆形.
        canvas.drawRect(0, 0, mRadius * 2, mRadius * 2, mBlueP);
        //这里产生一个新的图层
        canvas.saveLayer(0, 0, mRadius, mRadius, null, Canvas.FULL_COLOR_LAYER_SAVE_FLAG);
        //canvas.saveLayer(0, 0, mRadius, mRadius, null, Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);
        //绘制一个红色矩形.
        canvas.drawRect(0, 0, mRadius / 2, mRadius / 2, mRedP);
    }
}
