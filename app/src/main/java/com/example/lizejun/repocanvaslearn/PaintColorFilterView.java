package com.example.lizejun.repocanvaslearn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

public class PaintColorFilterView extends View {

    public PaintColorFilterView(Context context) {
        super(context);
        init();
    }

    public PaintColorFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PaintColorFilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //drawColorMatrixColorFilter(canvas);
        //drawLightingColorFilter(canvas);
        drawPorterDuffXferMode(canvas);
    }

    private void drawColorMatrixColorFilter(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        //1,得到一个颜色矩阵
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.5f);
        //2.通过颜色矩阵构建ColorMatrixColorFilter对象
        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
        Paint matrixPaint = new Paint();
        //3.把构建的对象设置给Paint
        matrixPaint.setColorFilter(colorMatrixColorFilter);
        canvas.drawBitmap(bitmap, 0, 0, matrixPaint);
    }

    private void drawLightingColorFilter(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.back_white);
        //1.构建一个LightingColorFilter
        LightingColorFilter lightingColorFilter = new LightingColorFilter(0, 0);
        Paint matrixPaint = new Paint();
        //2.设置给画笔
        matrixPaint.setColorFilter(lightingColorFilter);
        //3.绘制
        canvas.drawBitmap(bitmap, 0, 0, matrixPaint);
    }

    private void drawPorterDuffColorFilter(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN));
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

    private Paint mDstPaint;
    private Paint mSrcPaint;
    private Canvas mDstCanvas;
    private Canvas mSrcCanvas;
    private Bitmap mSrcBitmap;
    private Bitmap mDstBitmap;

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mDstPaint = new Paint();
        mSrcPaint = new Paint();
        mDstPaint.setColor(Color.YELLOW);
        mSrcPaint.setColor(Color.BLUE);
        mDstBitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
        mSrcBitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
        mDstCanvas = new Canvas(mDstBitmap);
        mSrcCanvas = new Canvas(mSrcBitmap);
    }

    private void drawPorterDuffXferMode(Canvas canvas) {
        //绘制DST图像.
        mDstCanvas.drawCircle(100, 100, 100, mDstPaint);
        canvas.drawBitmap(mDstBitmap, 0, 0, mDstPaint);
        //绘制SRC图像
        mSrcCanvas.drawRect(100, 100, 300, 300, mSrcPaint);
        mSrcPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
        canvas.drawBitmap(mSrcBitmap, 0, 0, mSrcPaint);
    }
}
