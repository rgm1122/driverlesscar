package com.jack.driverlesscar.exception;

public abstract class DriveException extends Exception{

    public DriveException(String errorMsg) {
        super(errorMsg);
    }
}
