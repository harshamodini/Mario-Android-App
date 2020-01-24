package com.example.brandon.mario.Items;

import com.example.brandon.mario.Game.SuperMarioVisitor;
import com.example.brandon.mario.Map.Mario;

/**
 * Created by brandon on 6/4/2017.
 */

public class SuperMushroom extends Items {

    public SuperMushroom(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int powerUp(int x, Mario mario){
        if ((mario.getY() == this.y || mario.getY() == this.y + 1)&& x == this.x) {
            if (mario.getState() == 9 || mario.getState() == 10) mario.changeState(15);
            this.x = 100;
            return 1000;
        }
        else
            return 0;
    }

    @Override
    public int getPoints(SuperMarioVisitor visitor) {
        //return super.getPoints(visitor);
        return 1000;
    }
}
