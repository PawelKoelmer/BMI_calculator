package com.example.bmicalculator.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    public static final double AMBULANCE_BASE_WIDTH_PERCENT = 8.0 / 40;
    public static final double AMBULANCE_BASE_HEIGHT_PERCENT = 5.0 / 40;
    public static final double AMBULANCE_BARREL_WIDTH_PERCENT = 1.0 / 40;
    public static final double AMBULANCE_BARREL_LENGTH_PERCENT = 2.0 / 10;

    public static final double AID_SPEED_PERCENT = 3.0 / 2;

    private double totalElapsedTime; // elapsed seconds
    private Ambulance ambulance;
    private GameThread gameThread;
    private int screenWidth;
    private int screenHeight;
    private int aidsFired = 0;

    private Paint textPaint; // Paint used to draw text
    private Paint backgroundPaint; // Paint used to clear the drawing area

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        getHolder().addCallback(this);
        textPaint = new Paint();
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.GRAY);
    }


    public void newGame(){
        double barrelLength = AMBULANCE_BARREL_LENGTH_PERCENT * screenHeight;
        ambulance =  new Ambulance(this,
                (int) (AMBULANCE_BASE_WIDTH_PERCENT * screenWidth),
                (int) (AMBULANCE_BASE_HEIGHT_PERCENT * screenHeight),
                (int) (barrelLength),
                (int) (AMBULANCE_BARREL_WIDTH_PERCENT * screenWidth));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        screenWidth = w; // store CannonView's width
        screenHeight = h; // store CannonView's height

    }

    public void alignAndFireCannonball(MotionEvent event) {
        // get the location of the touch in this view
        Point touchPoint = new Point((int) event.getX(),
                (int) event.getY());

        // compute the touch's distance from center of the screen
        // on the y-axis
        double centerMinusX = (screenWidth / 2 - touchPoint.x);

        double angle = 0; // initialize angle to 0

        // calculate the angle the barrel makes with the horizontal
        angle = Math.atan2(touchPoint.y, centerMinusX);

        // point the barrel at the point where the screen was touched
        ambulance.align(angle);
//
        // fire Cannonball if there is not already a Cannonball on screen
        if (ambulance.getAid() == null ||
                !ambulance.getAid().isOnScreen()) {
            ambulance.fireCannonball();
            ++aidsFired;
        }
    }

    private void updatePositions(double elapsedTimeMS) {
        double interval = elapsedTimeMS / 1000.0; // convert to seconds

        //update cannonball's position if it is on the screen
        if (ambulance.getAid() != null)
            ambulance.getAid().update(interval);

//        blocker.update(interval); // update the blocker's position
//
//        for (GameElement target : targets)
//            target.update(interval); // update the target's position
//
//        timeLeft -= interval; // subtract from time left
//
//         if the timer reached zero
//        if (timeLeft <= 0) {
//            timeLeft = 0.0;
//            gameOver = true; // the game is over
//            cannonThread.setRunning(false); // terminate thread
//            showGameOverDialog(R.string.lose); // show the losing dialog
//        }

        // if all pieces have been hit
//        if (targets.isEmpty()) {
//            cannonThread.setRunning(false); // terminate thread
//            showGameOverDialog(R.string.win); // show winning dialog
//            gameOver = true;
        }

    public int getScreenWidth() {
        return screenWidth;
    }
    public int getScreenHeight() {
        return screenHeight;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
            newGame();
            gameThread = new GameThread(holder);
            gameThread.setRunning(true); // start game running
            gameThread.start(); // start the game loop thread
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // get int representing the type of action which caused this event
        int action = e.getAction();

        // the user touched the screen or dragged along the screen
        if (action == MotionEvent.ACTION_DOWN ||
                action == MotionEvent.ACTION_MOVE) {
            // fire the cannonball toward the touch point
            alignAndFireCannonball(e);
        }
        return true;
    }


    private void drawGameElements(Canvas canvas){
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(),
                backgroundPaint);
        ambulance.draw(canvas);

        if (ambulance.getAid() != null &&
                ambulance.getAid().isOnScreen())
            ambulance.getAid().draw(canvas);
    }
    private class GameThread extends Thread{

        private SurfaceHolder surfaceHolder; // for manipulating canvas
        private boolean threadIsRunning = true; // running by default

        // initializes the surface holder
        public GameThread(SurfaceHolder holder) {
            surfaceHolder = holder;
            setName("CannonThread");
        }

        public void setRunning(boolean running) {
            threadIsRunning = running;
        }


        public void run(){
            Canvas canvas = null;
            long previousFrameTime = System.currentTimeMillis();
            while (threadIsRunning){
            try {
                canvas = surfaceHolder.lockCanvas(null);

                synchronized (surfaceHolder) {
                    long currentTime = System.currentTimeMillis();
                    double elapsedTimeMS = currentTime - previousFrameTime;
                    totalElapsedTime += elapsedTimeMS / 1000.0;
                    updatePositions(elapsedTimeMS); // update game state
                    drawGameElements(canvas); // draw using the canvas
                }

            }
            finally {
                if (canvas != null)
                    surfaceHolder.unlockCanvasAndPost(canvas);
            }
            }
        }
    }
}


