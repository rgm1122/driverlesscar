package com.jack.driverlesscar.park;

import com.jack.driverlesscar.exception.BarrieException;
import com.jack.driverlesscar.exception.DriveException;
import com.jack.driverlesscar.exception.OutOfParkException;

import java.util.ArrayList;
import java.util.List;

/**
 * 停车场
 */
public class Park {
    private int wide;
    private int length;
    private List<Position> barrierList = new ArrayList<>();//障碍点

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
        this.barrierList.add(barrier);
    }

    /**
     * 检查位置是否合法，如果不合法则抛出异常
     * @param pos
     * @throws DriveException
     */
    public void checkPos(Position pos)throws DriveException{
        if(!(pos.getX() > 0 && pos.getX() <= wide
                && pos.getY() > 0 && pos.getY() <= length)){//超出范围
            throw new OutOfParkException(String.format("%s is out of the park=%s", pos, this));
        }
        if(barrierList.contains(pos)){//是障碍点
            throw new BarrieException(String.format("%s is the barrier of park", pos));
        }
    }

    /**
     * 检查位置是否合法，合法返回true，非法为false
     * @param pos
     * @return
     */
    public boolean checkPosWithNoException(Position pos){
        try {
            checkPos(pos);
        } catch (DriveException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Park{" +
                "wide=" + wide +
                ", length=" + length +
                ", barrierList=" + barrierList +
                '}';
    }
}
