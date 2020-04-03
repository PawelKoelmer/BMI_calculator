package com.example.bmicalculator.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class Aid extends GameElement {

    private boolean onScreen;
    private double angle;
    private float velocityX;
    private int aidWidth;
    private int aidHeight;
    private int aidX;
    private int aidY;
    Bitmap bMap;

    public Aid(GameView view,int aidX , int aidY,float velocityX, float velocityY, double angle, Bitmap bMap){
        super(view,aidX,aidY, bMap.getWidth(),bMap.getHeight(),velocityY);
        this.angle=Math.toDegrees(angle);
        this.velocityX = velocityX;
        this.bMap = bMap;
        aidWidth = bMap.getWidth();
        aidHeight = bMap.getHeight();
        this.aidX = aidX;
        this.aidY = aidY;
        onScreen = true;
    }

    public boolean isOnScreen() {
        return onScreen;
    }

    private int getRadius() {
        return (shape.right - shape.left) / 2;
    }


    @Override
    public void draw(Canvas canvas) {
        Matrix matrix = new Matrix();
        matrix.setRotate((float)angle * 4,aidWidth,aidHeight);
        matrix.postTranslate(aidX-aidWidth/2,aidY);
        canvas.drawBitmap(bMap,matrix,null);
    }

    @Override
    public void update(double interval) {
        super.update(interval); // updates Cannonball's vertical position

        // update horizontal position
        shape.offset((int) (velocityX * interval), 0);

        // if Cannonball goes off the screen
        if (shape.top < 0 || shape.left < 0 ||
                shape.bottom > view.getScreenHeight() ||
                shape.right > view.getScreenWidth())
            onScreen = false; // set it to be removed
    }
}
