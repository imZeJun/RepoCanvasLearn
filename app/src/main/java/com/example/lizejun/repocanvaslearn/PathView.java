package com.example.lizejun.repocanvaslearn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class PathView extends View{

    public PathView(Context context) {
        super(context);
        init();
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawOp(canvas);
    }

    Paint mPaint;

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(3);
    }

    private void drawArcTo(Canvas canvas) {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        Path path = new Path();
        path.moveTo(0, 300);
        path.lineTo(300, 300);
        path.arcTo(0, 0, 500, 500, 0, -90, true);
        path.rLineTo(0, 250);
        path.close();
        canvas.drawPath(path, mPaint);
    }

    private void drawAddArc(Canvas canvas) {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        Path path = new Path();
        path.moveTo(0, 250);
        path.lineTo(500, 250);
        path.addArc(0, 0, 500, 500, 0, -90);
        path.close();
        canvas.drawPath(path, mPaint);
    }

    private void drawFillType(Canvas canvas) {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        Path fillTypePath = new Path();
        fillTypePath.addCircle(250, 250, 250, Path.Direction.CW);
        fillTypePath.addCircle(500, 500, 250, Path.Direction.CCW);
        fillTypePath.setFillType(Path.FillType.WINDING);
        canvas.drawPath(fillTypePath, mPaint);
    }

    private void drawOp(Canvas canvas) {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        Path path1 = new Path();
        path1.addCircle(250, 250, 250, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(400, 250, 250, Path.Direction.CW);
        path2.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        path1.op(path2, Path.Op.XOR);
        canvas.drawPath(path1, mPaint);
    }
}
