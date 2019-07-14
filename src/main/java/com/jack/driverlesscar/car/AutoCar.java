package com.jack.driverlesscar.car;

import com.jack.driverlesscar.exception.DriveException;

/**
 * 自动驾驶基类
 */
public interface AutoCar extends Car{
    void changeDriveMode();
    void stopAuto();
    void turnClockwise();
    void turnAnticlockwise();
    void forward(int step) throws DriveException;
}
