package com.example.brandon.mario.Map;

import com.example.brandon.mario.Game.SuperMarioVisitor;
import com.example.brandon.mario.GameItems;

/**
 * Created by brandon on 6/4/2017.
 */

public class Mario implements GameItems {
    int state = 10 ;
    int y = 10; // from 1 to 9 max
    int lives = 3;
    int[][] i;
    int fireCount;

    public Mario(){
    }

    public void changeState(int state){
        this.state = state;
    }

    public void fire(){
    }

    public int getState(){return state;}

    public int getLives(){
        return lives;
    }

    public void changeLives(int i) {
        if (state == 9 || state == 10){
            lives += i;
            this.y = 3;
        }
        else if (state == 16) state = 10;
        else if (state == 15) state = 9;
        else if (state == 6) state = 16;
        else if (state == 5) state = 15;
    }

    public int getY(){return y;}

    public void changeY(int i){//for jump
        this.y -= i;
        if(this.y < 0) this.y = 0;

    }

    public int getPoints(SuperMarioVisitor visitor){
        return 1;
    }


}
