package com.example.bmicalculator.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.bmicalculator.R;


public class Ambulance {

    private Aid aid;
    private Rect rect;
    private int baseWidth;
    private int baseHeigt;
    private int point0;
    private int barrelLength;
    private int barrelWidth;
    private int barrelX;
    private int barrelY;
    private double barrelAngle;
    private Point barrelEnd = new Point(); // endpoint of Cannon's barrel
    private Paint paint = new Paint();
    private GameView view;
    private Rect crossH;
    private Rect crossV;

    public Ambulance(GameView view, int baseWidth,int baseHeigt ,int barrelLength, int barrelWidth){
        barrelX = view.getScreenWidth()/2;
        barrelY = view.getScreenHeight();
        point0 = view.getScreenWidth()/2;
        this.view = view;
        this.baseWidth = baseWidth;
        this.barrelLength = barrelLength;
        this.barrelWidth = barrelWidth;

        this.baseHeigt = baseHeigt;
        setAmbulance();
        setCrossH();
        setCrossV();
        //Tworzy kąt armaty
        align(Math.PI * 0.5);
    }

    public void align(double barrelAngle) {
        this.barrelAngle = barrelAngle;
        double cos = Math.cos(barrelAngle);
        double sin = Math.sin(barrelAngle);
        int tmp1 = (int)((cos) * barrelLength);
        int tmp2 = (int)((sin) * barrelLength);
        barrelEnd.x = barrelX - tmp1;
        barrelEnd.y = barrelY - tmp2;
    }

    private void setCrossH() {
        crossH = new Rect();
        crossH.left = view.getScreenWidth()/2 - (int)(0.4 * baseWidth);
        crossH.bottom = view.getScreenHeight() - (int)(0.3 * baseHeigt);
        crossH.right = view.getScreenWidth() / 2 + (int)(0.4 * baseWidth);
        crossH.top = view.getScreenHeight() - (int)(0.6* baseHeigt);
    }
    private void setCrossV() {
        crossV = new Rect();
        crossV.left = view.getScreenWidth()/2 - (int)(0.15 * baseWidth);
        crossV.bottom = view.getScreenHeight() - (int)(0.9 * baseHeigt);
        crossV.right = view.getScreenWidth() / 2 + (int)(0.15 * baseWidth);
        crossV.top = view.getScreenHeight() - (int)(0.1 * baseHeigt);
    }

    public void setAmbulance(){
        rect = new Rect();
        rect.left = (view.getScreenWidth() / 2) - (baseWidth / 2);
        rect.bottom = view.getScreenHeight();
        rect.right = (view.getScreenWidth() / 2) + (baseWidth / 2);
        rect.top = view.getScreenHeight() - baseHeigt;
    }

    public void draw(Canvas canvas) {
        //Draw ambulance
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(barrelWidth); // set width of barrel
        canvas.drawLine( barrelX,barrelY, barrelEnd.x,
                barrelEnd.y, paint);

        paint.setColor(Color.WHITE);
        canvas.drawRect(rect,paint);
        //DrawCross
        paint.setColor(Color.RED);
        canvas.drawRect(crossH,paint);
        canvas.drawRect(crossV,paint);
    }

    public void fireCannonball() {
        // calculate the Cannonball velocity's x component
        int velocityX = (int) (GameView.AID_SPEED_PERCENT *
                view.getScreenWidth() * Math.sin(barrelAngle))/1000;

        // calculate the Cannonball velocity's y component
        int velocityY = (int) (GameView.AID_SPEED_PERCENT *
                view.getScreenWidth() * -Math.cos(barrelAngle))/1000;

        // construct Cannonball and position it in the Cannon
        Bitmap bMap = BitmapFactory.decodeResource(view.getResources(), R.drawable.aid);
        aid = new Aid(view, barrelEnd.x , barrelEnd.y ,velocityX, velocityY,barrelAngle, bMap);
    }

    public Aid getAid() {
        return aid;
    }

    // removes the Cannonball from the game
    public void removeAid() {
        aid = null;
    }

}
