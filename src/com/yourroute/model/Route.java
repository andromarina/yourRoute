package com.yourroute.model;

import com.yourroute.R;

import java.util.ArrayList;

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
    private StopsCollection forwardStops;
    private StopsCollection backwardStops;


    public Route(int id, String name, CarType type, String startEnd, String duration,
                 String length, String interval, String startTime, String endTime) {
        this.id = id;
        this.name = (name == null) ? "" : name;
        this.type = type;
        this.startEnd = (startEnd == null) ? "" : startEnd;
        this.duration = (duration == null) ? "" : duration;
        this.length = (length == null) ? "" : length;
        this.interval = (interval == null || interval.equals("00:00")) ? "" : interval;
        this.startTime = (startTime == null) ? "" : startTime;
        this.endTime = (endTime == null) ? "" : endTime;
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

    public int getIconResource(int carTypeId) {
        int iconResource;
        switch (carTypeId) {
            case 1:
                iconResource = R.drawable.ic_bus;
                return iconResource;
            case 2:
                iconResource = R.drawable.ic_trolley;
                return iconResource;
            case 3:
                iconResource = R.drawable.ic_tram;
                return iconResource;
            case 4:
                iconResource = R.drawable.ic_taxi;
                return iconResource;
            case 5:
                iconResource = R.drawable.ic_bus;
                return iconResource;
            case 6:
                iconResource = R.drawable.ic_bus;
                return iconResource;
            case 7:
                iconResource = R.drawable.ic_bus;
                return iconResource;
            case 8:
                iconResource = R.drawable.ic_electro_train;
                return iconResource;
        }
        return 0;
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

    public String getLengthAndDuration() {
        if (this.length.isEmpty() && this.duration.isEmpty()) {
            return new String();
        }

        StringBuilder sb = new StringBuilder();

        if (!this.length.isEmpty()) {
            sb.append(this.length);
            sb.append(" ");
            sb.append("km ");
        }

        if (!this.duration.isEmpty()) {
            sb.append(this.duration);
            sb.append(" ");
            sb.append("min");
        } else {
            sb.delete(sb.length() - 1, sb.length() - 1);
        }

        String lengthAndDuration = sb.toString();
        return lengthAndDuration;


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

    public String getOperationalHours() {
        if (this.startTime.isEmpty() && this.endTime.isEmpty()) {
            return new String();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(startTime);
        sb.append(" - ");
        sb.append(endTime);
        String operationalHours = sb.toString();
        return operationalHours;
    }

    public void initializeStops(ArrayList<Stop> stops) {
        this.forwardStops = new StopsCollection(stops, true);
        this.backwardStops = new StopsCollection(stops, false);
    }

    public StopsCollection getForwardStops() {
        return this.forwardStops;
    }

    public StopsCollection getBackwardStops() {
        return this.backwardStops;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s %s %s", this.id, this.name, this.getType().toString(), this.startEnd);
    }


}
