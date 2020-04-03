package com.example.bmicalculator.game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class GameElement {

    protected GameView view; // the view that contains this GameElement
    protected Paint paint = new Paint(); // Paint to draw this GameElement
    protected Rect shape; // the GameElement's rectangular bounds
    private float velocityY; // th

    public GameElement(GameView view, int elX,int elY, int width, int height,float velocityY) {
        this.view = view;
        this.velocityY = velocityY;
        shape = new Rect(elX, elY, elX + width, elY + height);
    }

   public void draw(Canvas canvas) {
        canvas.drawRect(shape, paint); }

    public void update(double interval) {
         //update vertical position
        shape.offset(0, (int) (velocityY * interval));

        // if this GameElement collides with the wall, reverse direction
        if (shape.top < 0 && velocityY < 0 ||
                shape.bottom > view.getScreenHeight() && velocityY > 0)
            velocityY *= -1; // reverse this GameElement's velocity
    }

}
