package com.jack.driverlesscar.constant;

/**
 * 方向枚举
 */
public enum Orientation {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    /**
     * 根据当前位置顺时针转向
     * @return
     */
    public Orientation turn(){
        switch (this){
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
            default:
                    return null;
        }
    }
}
