package com.example.brandon.mario.Enemy;

import com.example.brandon.mario.Map.Mario;

import static java.lang.Math.abs;

/**
 * Created by brandon on 6/4/2017.
 */

public class BuzzyBeetle extends Enemy {
    int direction = 1;
    public BuzzyBeetle(int x, int y){
        this.posx = x;
        this.posy = y;
    }

    public void move(int[][] level) {
        if (this.posx != 1000) {
            if (direction == 1) {//going right
                if (level[this.posy - 1][this.posx] == 16) {//nothing on right go right
                    this.posx++;
                } else {//go left
                    direction = 2;
                    this.posx--;
                }
            } else {//going left
                if (level[this.posy - 1][this.posx - 2] == 16) {//nothing on left go left
                    this.posx--;
                } else {//go right
                    direction = 1;
                    this.posx++;
                }
            }
        }
    }
    public void attack(int marioXPos, Mario mario){
        if (marioXPos == this.posx && (mario.getY() == this.posy)) {
            mario.changeLives(-1);
        }
    }
    public void changeX(int i){this.posx = i;}
    @Override
    public int getReward() {
        return 100;
    }
}
