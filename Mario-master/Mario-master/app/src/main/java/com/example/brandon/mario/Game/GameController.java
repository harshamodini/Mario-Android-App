package com.example.brandon.mario.Game;

/**
 * Created by brandon on 6/4/2017.
 */


public class GameController {
    private GameView view;

    //map.initMap();
    private GameThread gameThread;



    public GameController(GameView view) {
        gameThread = new GameThread(this, view);
        gameThread.start();
        this.view = view;
    }

    public void processInput(int buttonPressed) {
        if (buttonPressed == 1) {
        }
        else if (buttonPressed == 2) {
        }
        else if (buttonPressed == 3) {
        }
        else if (buttonPressed == 4) {
        }
        else if (buttonPressed == 5) {
        }
        else if (buttonPressed == 6) {
        }

    }

    public void update() {
        //System.out.print("help");
        /*for (int i = 0; i < monsters.length; i++) {
            //monsters[i].attack();
            if (monsters[i].xPos == digDug.xPos && monsters[i].yPos == digDug.yPos)
                digDug.alive = false;
            if (digDug.attacking){
                if (digDug.direction == 1 && monsters[i].xPos == digDug.xPos && (digDug.yPos - monsters[i].yPos < 3))
                    monsters[i].alive = false;
                if (digDug.direction == 2 && (monsters[i].xPos - digDug.xPos < 3) && monsters[i].yPos == digDug.yPos)
                    monsters[i].alive = false;
                if (digDug.direction == 3 && monsters[i].xPos == digDug.xPos && (monsters[i].yPos - digDug.yPos < 3))
                    monsters[i].alive = false;
                if (digDug.direction == 4 && (digDug.xPos - monsters[i].xPos < 3) && monsters[i].yPos == digDug.yPos)
                    monsters[i].alive = false;

            }
        }
        for (int i = 0; i < rocks.length; i++) {
            if (rocks[i].shouldFall())
                rocks[i].fallCheck();
        }*/

    }
}
