package com.example.brandon.mario.Map;

import com.example.brandon.mario.Items.Items;

/**
 * Created by brandon on 6/4/2017.
 */

public abstract class PowerBlock extends Blocks {
    public Items item;

    public PowerBlock(int x, int y, Items item)
    {
        this.x = x;
        this.y = y;
        this.item = item;
    }

}
