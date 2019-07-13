package com.jack.driverlesscar.park;

import com.jack.driverlesscar.constant.Orientation;

import java.util.Objects;

public class Position {
    private int x;
    private int y;
    private Position lastPos;//记录上一次地点用于回退

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * 根据方向前进
     * @param orientation
     * @param step
     */
    public void forward(Orientation orientation, int step){
        this.lastPos = new Position(this.x, this.y);
        Position position = tryForward(orientation, step);
        this.x = position.x;
        this.y = position.y;
    }

    public Position tryForward(Orientation orientation, int step){
        Position p = new Position(this.x, this.y);
        switch (orientation){
            case NORTH:
                p.y += step;
                break;
            case EAST:
                p.x += step;
                break;
            case SOUTH:
                p.y -= step;
                break;
            case WEST:
                p.x -= step;
                break;
            default:
                break;
        }
        return p;
    }

    /**
     * 回退上一个位置
     */
    public void backward(){
        this.x = this.lastPos.x;
        this.y = this.lastPos.y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
