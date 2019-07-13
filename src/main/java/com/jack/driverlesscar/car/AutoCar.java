package com.jack.driverlesscar.car;

import com.jack.driverlesscar.constant.Orientation;

public interface AutoCar extends Car{
    void changeDriveMode();
    void stopAuto();
    void turn(Orientation orientation);
    void forward(int step);
}
