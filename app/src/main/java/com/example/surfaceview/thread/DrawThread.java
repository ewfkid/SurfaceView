package com.example.surfaceview.thread;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class DrawThread extends Thread {
    private SurfaceHolder surfaceHolder;

    private boolean running;

    private Context context;

    private Paint paint;

    private float x, y, radius;

    private int speedX, speedY;


    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        this.context = context;
        this.surfaceHolder = surfaceHolder;
        this.running = true;
        this.x = 0;
        this.y = 0;
        this.radius = 150;
        this.speedX = 5;
        this.speedY = 5;
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
    }


    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    x += speedX;
                    y += speedY;

                    if (x > canvas.getWidth() - radius) {
                        speedX *= -1;
                    }

                    if (y > canvas.getHeight() - radius) {
                        speedY *= -1;
                    }

                    if (x < 0) {
                        speedX *= -1;
                    }

                    if (y < 0) {
                        speedY *= -1;
                    }

                    canvas.drawColor(Color.WHITE);
                    canvas.drawCircle(x, y, radius, paint);

                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    public void requestStop() {
        running = false;
    }


}
