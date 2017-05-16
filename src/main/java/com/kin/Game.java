package com.kin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by link on 10/05/17.
 */
public class Game {

    private Paint paint;

    private List<Change> history;

    public Game() {
        this.paint = new Paint(1<<10, 1<<10);
        this.history = new ArrayList<>();
    }

    public Change change(int x, int y, int value){
        Change change = new Change(x, y, value);
        this.paint.change(change);
        history.add(change);
        return change;
    }

    public List<Change> pullHistory(int fromStep){
        return history.subList(fromStep, history.size());
    }

    public int[][] getCurrentState(){
        return paint.getCurrentState();
    }
}
