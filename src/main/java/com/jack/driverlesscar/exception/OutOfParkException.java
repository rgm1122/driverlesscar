package com.jack.driverlesscar.exception;

public class OutOfParkException extends DriveException{

    public OutOfParkException(String errorMsg) {
        super(errorMsg);
    }
}
