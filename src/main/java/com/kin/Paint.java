package com.kin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by link on 10/05/17.
 */
public class Paint {
    private int[][] currentState;

    public Paint(int width, int height) {
        this.currentState = new int[width][height];
    }

    public Change change(Change change){
        this.currentState[change.getX()][change.getY()] = change.getValue();
        return change;
    }

    public int[][] getCurrentState(){
        return currentState;
    }

}
