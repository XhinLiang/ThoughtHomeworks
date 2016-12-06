package com.xhinliang.thoughthomeworks;

/**
 * @author xhinliang xhinliang@gmail.com
 */
public class Talk {
    private static final int CONST_LIGHTING_COST_TIME = 5;
    private int time;
    private String name;
    private boolean isLightning;

    Talk(String name, int time) {
        this.time = time;
        this.name = name;
        isLightning = false;
    }

    Talk(String name) {
        this.name = name;
        this.time = CONST_LIGHTING_COST_TIME;
        this.isLightning = true;
    }

    int getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        if (isLightning)
            return name + " lightning";
        return name + " " + time + "min";
    }
}
