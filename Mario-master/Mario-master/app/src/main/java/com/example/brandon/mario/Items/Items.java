package com.example.brandon.mario.Items;

import com.example.brandon.mario.GameItems;
import com.example.brandon.mario.Game.SuperMarioVisitor;

/**
 * Created by brandon on 5/31/2017.
 */

public abstract class Items implements GameItems {

    int value;
    int x;
    int y;

    /* Write Constructor here, if necessary */

    public int getValue(){
        return value;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public int getPoints(SuperMarioVisitor visitor){
        return visitor.visit(this);
    }
}
