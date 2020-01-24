package com.example.brandon.mario.Game;

import com.example.brandon.mario.GameItems;

/**
 * Created by brandon on 5/31/2017.
 */

public class PointsAggregator {
    int currScore;

    public int addPoints(GameItems gameItem){
        SuperMarioVisitor visitor = new SuperMarioVisitorImpl();
        currScore += gameItem.getPoints(visitor);
        return  currScore;
    }

    public int getCurrScore() {
        //Levelone Array is Passed in

        return currScore;
    }
}
