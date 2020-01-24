package com.example.brandon.mario.Enemy;

import com.example.brandon.mario.GameItems;
import com.example.brandon.mario.Game.SuperMarioVisitor;

/**
 * Created by brandon on 5/31/2017.
 */

public abstract class Enemy implements GameItems {
    int reward;
    int posx;
    int posy;

    public int getReward(){
        return reward;
    }

    public int getX(){
        return posx;
    }

    public int getY(){
        return posy;
    }



    public void attack(){}

    public int getPoints(SuperMarioVisitor visitor){
        return visitor.visit(this);
    }
}

