package com.jack.driverlesscar.exception;

import com.jack.driverlesscar.park.Park;
import com.jack.driverlesscar.park.Position;

public class DriveException extends Exception{
    private Park park;
    private Position pos;

    public DriveException(Park park, Position pos) {
        this.park = park;
        this.pos = pos;
    }
}
