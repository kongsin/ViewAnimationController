package com.example.kognsin.animate;

/**
 * Created by kognsin on 9/24/2016.
 */

public class Pos {

    private float x, y;

    public Pos(int[] pos) {
        setX(pos[0]);
        setY(pos[1]);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
