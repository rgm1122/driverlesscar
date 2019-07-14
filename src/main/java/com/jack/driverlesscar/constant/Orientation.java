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
    public Orientation turnClockwise(){
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

    /**
     * 根据当前位置逆时针转向
     * @return
     */
    public Orientation turnAnticlockwise(){
        switch (this){
            case NORTH:
                return WEST;
            case EAST:
                return NORTH;
            case SOUTH:
                return EAST;
            case WEST:
                return SOUTH;
            default:
                return null;
        }
    }
}
