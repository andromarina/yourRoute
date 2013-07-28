package com.andromarina.yourRoute.model;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 6/4/13
 * Time: 8:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Stop {
    private final int id;
    private final String name;
    private final int stopIndex;

    public Stop(int id, String name, int stopIndex) {
        this.id = id;
        this.name = name;
        this.stopIndex = stopIndex;
    }

    public int getStopId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getStopIndex() {
        return this.stopIndex;
    }

}

