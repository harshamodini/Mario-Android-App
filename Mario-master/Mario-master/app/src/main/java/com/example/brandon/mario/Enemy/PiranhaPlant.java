package com.example.brandon.mario.Enemy;

import com.example.brandon.mario.Map.Mario;

import static java.lang.Math.abs;

/**
 * Created by brandon on 6/4/2017.
 */

public class PiranhaPlant extends Enemy {

    public PiranhaPlant(int x, int y){
        this.posx = x;
        this.posy = y;
    }
    @Override
    public int getReward(){
        return 100;
    }

    public void attack(int marioXPos, Mario mario){
        if (marioXPos == this.posx && (mario.getY() == this.posy || mario.getY() == this.posy - 1   )) {
            this.posy = 9;
            mario.changeLives(-1);
        }
        else if (abs(marioXPos - this.posx) < 3 ){
            this.posy = 10;
        }
    }
}
