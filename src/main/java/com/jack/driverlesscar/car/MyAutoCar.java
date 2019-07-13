package com.jack.driverlesscar.car;

import com.jack.driverlesscar.constant.Command;
import com.jack.driverlesscar.constant.DriveMode;
import com.jack.driverlesscar.constant.Orientation;
import com.jack.driverlesscar.exception.DriveException;
import com.jack.driverlesscar.park.Park;
import com.jack.driverlesscar.park.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
        this.driveMode = DriveMode.MANUAL;
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
            case TURN:
                turn();
                break;
            case FORWARD:
                forward(1);
                if(park.checkPos(pos)){
                    logger.info("Forward success, current position is {}", pos);
                }else{
                    logger.error("Drive Exception  park={} pos={}", park, pos);
                    throw new DriveException(park, pos);
                }
                break;
        }
    }

    private void turn() {
        orientation = orientation.turn();
        logger.info("Turn success, current orientation is {}", orientation);
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
     * 自动驾驶线程，每隔一段时间前进一步，当遇到边界时则自动转向
     */
    private class DriveThread extends Thread{
        @Override
        public void run() {
            while(driveMode == DriveMode.AUTO){
                try {
                    if(park.checkPos(pos.tryForward(orientation, 1))){
                        forward(1);
                        logger.info("Forward success, current position is {}", pos);
                    }else{
                        turn();
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

    public void turn(Orientation orientation) {
        this.orientation = orientation;
        logger.info("Turn success, current orientation is {}", orientation);
    }

    public void forward(int step) {
        pos.forward(orientation, step);//按现在位置前进N步
    }
}
