package com.uiolert.stars_.modes.ball;

import java.util.IdentityHashMap;
import java.util.Map;

public class FreeMove {
    boolean left = true;
    boolean top = true;
    boolean right = true;
    boolean bottom = true;
    Ball ball;
    Map<PossibleMove,Ball> interfereFreeMove = new IdentityHashMap<>();

    public FreeMove(Ball ball) {
        this.ball = ball;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isBottom() {
        return bottom;
    }

    public void setBottom(boolean bottom) {
        this.bottom = bottom;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Map<PossibleMove, Ball> getInterfereFreeMove() {
        return interfereFreeMove;
    }

    public void setInterfereFreeMove(Map<PossibleMove, Ball> interfereFreeMove) {
        this.interfereFreeMove = interfereFreeMove;
    }
}
