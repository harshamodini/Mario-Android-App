package com.example.brandon.mario.Items;

import com.example.brandon.mario.Game.SuperMarioVisitor;
import com.example.brandon.mario.Map.Mario;

/**
 * Created by brandon on 6/4/2017.
 */

public class Coins extends Items {

    public Coins(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int addCoin(int x, Mario mario){
        if ((mario.getY() == this.y || mario.getY() == this.y + 1)&& x == this.x) {
            this.x = 100;
            return 200;
        }
        else
            return 0;
    }

    @Override
    public int getPoints(SuperMarioVisitor visitor) {
        //return super.getPoints(visitor);
        return 200;
    }
}
