package com.example.lizejun.repocanvaslearn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

public class PaintShaderView extends View {

    public PaintShaderView(Context context) {
        super(context);
        init();
    }

    public PaintShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PaintShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Bitmap mOriginalBitmap;
    private Paint mPaint;

    private void init() {
        mOriginalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shader_pic);
        mPaint = new Paint();
    }

    private void drawBitmapShader(Canvas canvas) {
        BitmapShader shader = new BitmapShader(mOriginalBitmap, Shader.TileMode.CLAMP, Shader.TileMode.MIRROR);
        mPaint.setShader(shader);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), mPaint);
    }

    private void drawLinearGradient(Canvas canvas) {
        LinearGradient gradient = new LinearGradient(0, 0, 100, 10, new int[]{ Color.WHITE, Color.BLUE, Color.BLACK }, new float[]{0, 0.5f, 1f}, Shader.TileMode.REPEAT);
        mPaint.setShader(gradient);
        canvas.drawRect(0, 0, 900, 900, mPaint);
    }

    private void drawSweepGradient(Canvas canvas) {
        SweepGradient gradient = new SweepGradient(450, 450, Color.WHITE, Color.BLACK);
        mPaint.setShader(gradient);
        canvas.drawRect(0, 0, 900, 900, mPaint);
    }

    private void drawRadialGradient(Canvas canvas) {
        RadialGradient gradient = new RadialGradient(200, 200, 50, Color.BLUE, Color.RED, Shader.TileMode.REPEAT);
        mPaint.setShader(gradient);
        canvas.drawRect(0, 0, 900, 900, mPaint);
    }

    private void drawComposeShader(Canvas canvas) {
        BitmapShader bitmapShader = new BitmapShader(mOriginalBitmap, Shader.TileMode.CLAMP, Shader.TileMode.MIRROR);
        RadialGradient radialGradient = new RadialGradient(300, 300, 300, Color.TRANSPARENT, Color.WHITE, Shader.TileMode.CLAMP);
        ComposeShader composeShader = new ComposeShader(bitmapShader, radialGradient, PorterDuff.Mode.SRC_OVER);
        mPaint.setShader(composeShader);
        canvas.drawCircle(300, 300, 300, mPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawComposeShader(canvas);
    }


}
