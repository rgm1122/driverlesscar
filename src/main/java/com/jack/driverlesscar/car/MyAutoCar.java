package com.jack.driverlesscar.car;

import com.jack.driverlesscar.constant.Command;
import com.jack.driverlesscar.constant.DriveMode;
import com.jack.driverlesscar.constant.Orientation;
import com.jack.driverlesscar.exception.DriveException;
import com.jack.driverlesscar.park.Park;
import com.jack.driverlesscar.park.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 自动驾驶简单实现类
 * 当为手动模式时，由用户控制前进和转向
 * 当为自动模式时，则用户不能前进和转向，由系统按照既定模式行驶，会自动检测停车场边界和绕过障碍物
 */
public class MyAutoCar implements AutoCar{

    private static final Logger logger = LoggerFactory.getLogger(MyAutoCar.class);

    private Park park;
    private DriveMode driveMode;
    private Position pos;
    private Orientation orientation;
    private DriveThread driveThread;

    public MyAutoCar(Park park, Position initPos, Orientation initOrientation) {
        this.park = park;
        this.pos = initPos;
        this.orientation = initOrientation;
        this.driveMode = DriveMode.MANUAL;//初始为人工模式
        logger.info("Make a new autoCar park={} initPos={} initOrientation={} driveMode={}",
                park, initPos, initOrientation, driveMode);
    }

    /**
     * 手动模式，接受命令改变方向
     * @param command
     */
    public void move(String command) throws DriveException{
        if(driveMode == DriveMode.AUTO){//如果是自动模式则不会执行命令
            logger.warn("Current driveMode is AUTO please change driveMode first");
            return;
        }
        Command com = Command.getCommand(command);
        if(com == null){
            logger.error("Unknown command");
            return;
        }
        switch (com){
            case TURN_CLOCKWISE:
                turnClockwise();
                break;
            case TURN_ANTICLOCKWISE:
                turnAnticlockwise();
                break;
            case FORWARD:
                forward(1);
                break;
        }
    }

    public int getPositionX() {
        return pos.getX();
    }

    public int getPositionY() {
        return pos.getY();
    }

    public String getOrientation() {
        return orientation.name();
    }

    public void changeDriveMode() {
        this.driveMode =  driveMode.change();
        logger.info("Change driveMode success, current driveMode is {}", driveMode);
        switch (driveMode){
            case AUTO:
                startAuto();
                break;
            case MANUAL:
                break;
        }
    }

    /**
     * 自动驾驶线程，在停车场内自动行驶，每隔一段时间前进一步，当遇到边界或障碍时则自动转向
     */
    private class DriveThread extends Thread{
        @Override
        public void run() {
            while(driveMode == DriveMode.AUTO){
                try {
                    if(park.checkPosWithNoException(pos.tryForward(orientation, 1))){
                        pos.forward(orientation, 1);
                        logger.info("Forward success, current position is {}", pos);
                    }else{
                        turnClockwise();
                    }
                    Thread.sleep(2000);
                } catch (Exception e) {
                    logger.error("AutoMode is error");
                    changeDriveMode();//出现异常转为自动模式
                    break;
                }
            }
        }
    }

    //开始自动驾驶
    private void startAuto(){
        stopAuto();
        driveThread = new DriveThread();
        driveThread.start();
    }

    /**
     * 销毁车辆
     */
    public void stopAuto() {
        if(driveThread != null && driveThread.isAlive()){
            driveThread.interrupt();
        }
    }

    @Override
    public void turnClockwise() {
        orientation = orientation.turnClockwise();
        logger.info("Turn clockwise success, current orientation is {}", orientation);
    }

    @Override
    public void turnAnticlockwise() {
        orientation = orientation.turnAnticlockwise();
        logger.info("Turn anticlockwise success, current orientation is {}", orientation);
    }

    @Override
    public void forward(int step) throws DriveException {
        pos.forward(orientation, step);//按现在位置前进N步
        park.checkPos(pos);
        logger.info("Forward success, current position is {}", pos);
    }
}
