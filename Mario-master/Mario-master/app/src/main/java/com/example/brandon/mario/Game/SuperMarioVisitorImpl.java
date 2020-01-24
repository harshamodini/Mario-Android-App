package com.example.brandon.mario.Game;

import com.example.brandon.mario.Enemy.Enemy;
import com.example.brandon.mario.Game.SuperMarioVisitor;
import com.example.brandon.mario.Items.Items;

/**
 * Created by brandon on 5/31/2017.
 */

public class SuperMarioVisitorImpl implements SuperMarioVisitor {
    @Override
    public int visit(Enemy enemy){
        return enemy.getReward();
    }

    @Override
    public int visit(Items item){
        return item.getValue();
    }
}

