package com.kin;

/**
 * Created by link on 10/05/17.
 */
public class Change {
    private final int value;
    private final int y;
    private final int x;

    public Change(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
