package com.example.brandon.mario.Game;

import com.example.brandon.mario.Enemy.Enemy;
import com.example.brandon.mario.Items.Items;

/**
 * Created by brandon on 5/31/2017.
 */

public interface SuperMarioVisitor {
    public int visit(Enemy enemy);
    public int visit(Items item);
}
