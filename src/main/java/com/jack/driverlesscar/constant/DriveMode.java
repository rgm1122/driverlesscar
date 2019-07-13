package com.jack.driverlesscar.constant;

/**
 * 驾驶模式
 */
public enum DriveMode {
    AUTO,
    MANUAL;
    /**
     * 切换驾驶模式
     * @return
     */
    public DriveMode change(){
        switch (this){
            case AUTO:
                return MANUAL;
            case MANUAL:
                return AUTO;
            default:
                return null;
        }
    }
}
