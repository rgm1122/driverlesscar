package com.jack.driverlesscar.park;

import java.util.ArrayList;
import java.util.List;

/**
 * 停车场
 */
public class Park {
    private int wide;
    private int length;
    private List<Position> barrieList = new ArrayList<>();//障碍点

    public Park(int wide, int length) {
        this.wide = wide;
        this.length = length;
    }

    public int getWide() {
        return wide;
    }

    public void setWide(int wide) {
        this.wide = wide;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void addBarrier(Position barrier) {
        this.barrieList.add(barrier);
    }

    public boolean checkPos(Position pos){
        if(pos == null){
            return false;
        }
        if(pos.getX() > 0 && pos.getX() <= wide
            && pos.getY() > 0 && pos.getY() <= length
            && !barrieList.contains(pos)){//在范围内且不是障碍点
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Park{" +
                "wide=" + wide +
                ", length=" + length +
                '}';
    }
}
