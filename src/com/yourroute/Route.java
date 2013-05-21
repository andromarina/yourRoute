package com.yourroute;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/18/13
 * Time: 3:44 PM
 * To change this template use File | Settings | File Templates.
 */


public class Route {
    private final int id;
    private final String name;
    private final CarType type;
    private final String startEnd;

    public Route (int id, String name, CarType type, String startEnd ) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.startEnd = startEnd;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public CarType getType() {
        return this.type;
    }

    public String getStartEnd() {
        return this.startEnd;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s %s %s", this.id, this.name, this.getType().toString(), this.startEnd);
    }


}
