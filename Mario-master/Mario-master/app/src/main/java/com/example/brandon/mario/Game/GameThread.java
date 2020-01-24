package com.example.brandon.mario.Game;

/**
 * Created by brandon on 6/4/2017.
 */

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
    private GameController controller;
    private GameView gameView;

    public GameThread(GameController controller, GameView gameView) {
        this.controller = controller;
        this.gameView = gameView;
    }

    public void run () {
        try {
            SurfaceHolder sh = gameView.getHolder();

            while (true) {
                Canvas canvas = sh.lockCanvas();

                if (canvas != null) {
                    controller.update();
                    gameView.draw(canvas);
                    sh.unlockCanvasAndPost(canvas);
                }
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Exception occured");
                }
            }
        }
        catch(NullPointerException e){
            System.out.println("Exception caught");
        }
    }

}