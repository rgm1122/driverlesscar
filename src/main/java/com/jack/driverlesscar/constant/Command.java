package com.jack.driverlesscar.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * 命令枚举
 */
public enum Command {
    TURN,//转向
    FORWARD; //前进

    public static Command getCommand(String command){
        if(StringUtils.isEmpty(command)){
            return null;
        }
        Command[] commands = values();
        for(Command com : commands){
            if(com.name().equals(command)){
                return com;
            }
        }
        return null;
    }
}
