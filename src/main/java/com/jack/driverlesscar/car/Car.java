package com.jack.driverlesscar.car;

import com.jack.driverlesscar.exception.DriveException;

public interface Car {
    void move(String command) throws DriveException;
    int getPositionX();
    int getPositionY();
    String getOrientation();
}
