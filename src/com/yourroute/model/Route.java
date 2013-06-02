package com.yourroute.model;

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
    private final String duration;
    private final String length;
    private final String interval;
    private final String startTime;
    private final String endTime;


    public Route(int id, String name, CarType type, String startEnd, String duration,
                 String length, String interval, String startTime, String endTime) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.startEnd = startEnd;
        this.duration = duration;
        this.length = length;
        this.interval = interval;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public String getDuration() {
        return this.duration;
    }

    public String getLength() {
        return this.length;
    }

    public String getInterval() {
        return this.interval;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s %s %s", this.id, this.name, this.getType().toString(), this.startEnd);
    }


}
