package com.example.brandon.mario.Enemy;

import com.example.brandon.mario.Map.Mario;

/**
 * Created by brandon on 6/4/2017.
 */

public class Bloober extends Enemy {
    int pace = 1;
    public Bloober(int x, int y){
        this.posx = x;
        this.posx = y;
    }

    @Override
    public int getReward(){
        return 100;
    }

    public void changeX(int i){ this.posx = i;}
    public void chaseMario(int x, Mario mario){
        if ((mario.getY() == this.posy || mario.getY() == this.posy + 1)&& x == this.posx)
            mario.changeLives(-1);
        else if (pace == 1){
            if(x < this.posx) this.posx--;
            else if (x > this.posx) this.posx++;
            else if (mario.getY() < this.posy) this.posy--;
            else if (mario.getY() > this.posy) this.posy++;
            pace = 0;
        }
        else pace = 1;
    }

}
