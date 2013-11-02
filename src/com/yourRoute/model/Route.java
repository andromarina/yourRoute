package com.yourRoute.model;

import com.yourRoute.R;

import java.nio.ByteBuffer;
import java.sql.Blob;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/18/13
 * Time: 3:44 PM
 * To change this template use File | Settings | File Templates.
 */


public class Route implements Comparable<Route> {
    private final int id;
    private String name;
    private final int carType;
    private final String startEnd;
    private final String length;
    private final String interval;
    private final String workTime;
    private StopsCollection forwardStops;
    private StopsCollection backwardStops;
    private int cityId;
    private final float price;
    private final int extremeStopFirstId;
    private final int extremeStopSecondId;
    private final ByteBuffer geometryForward;
    private final ByteBuffer geometryBackward;


    public Route(int id, String name, int carType, String startEnd,
                 String length, String interval, String workTime, int cityId, float price, int extremeStopFirstId,
                 int extremeStopSecondId, ByteBuffer geometryForward, ByteBuffer geometryBackward) {
        this.id = id;
        this.name = (name == null) ? "" : name;
        this.carType = carType;
        this.startEnd = (startEnd == null) ? "" : startEnd;
        this.length = (length == null) ? "" : length;
        this.interval = (interval == null || interval.equals("00:00")) ? "" : interval;
        this.workTime = (workTime == null) ? "" : workTime;
        this.cityId = cityId;
        this.price = price;
        this.extremeStopFirstId = extremeStopFirstId;
        this.extremeStopSecondId = extremeStopSecondId;
        this.geometryForward = geometryForward;
        this.geometryBackward = geometryBackward;

    }

    private Route() {
        this.id = -1;
        this.name = null;
        this.carType = -1;
        this.startEnd = null;
        this.length = null;
        this.interval = null;
        this.workTime = null;
        this.cityId = -1;
        this.price = -1;
        this.extremeStopFirstId = -1;
        this.extremeStopSecondId = -1;
        this.geometryForward = null;
        this.geometryBackward = null;
    }


    public int getId() {
        return this.id;
    }

    public String getName() {
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return this.name;
    }

    public int getCarType() {
        return this.carType;
    }

    public int getIconResource(int carTypeId) {
        int iconResource;
        switch (carTypeId) {
            case 1:
                iconResource = R.drawable.ic_metro;
                return iconResource;
            case 2:
            case 5:
                iconResource = R.drawable.ic_trolley;
                return iconResource;
            case 3:
            case 7:
                iconResource = R.drawable.ic_tram;
                return iconResource;
            case 4:
            case 8:
                iconResource = R.drawable.ic_bus;
                return iconResource;
            case 6:
                iconResource = R.drawable.ic_electro_train;
                return iconResource;
            case 9:
                iconResource = R.drawable.ic_boat;
                return iconResource;
        }
        return 0;
    }

    public String getStartEnd() {
        return this.startEnd;
    }

    public String getLength() {
        return this.length;
    }

    public String getInterval() {
        return this.interval;
    }

    public String getWorkTime() {
        return this.workTime;
    }

    public float getPrice() {
        return price;
    }

    public int getExtremeStopFirstId() {
        return extremeStopFirstId;
    }

    public int getExtremeStopSecondId() {
        return extremeStopSecondId;
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

    public int getCityId() {
        return this.cityId;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s %s %s", this.id, this.name, this.getCarType(), this.startEnd);
    }

    public int compareTo(Route route) {

        int routeId = route.getId();

        //ascending order
        return this.id - routeId;
    }

    public static Route createFakeRouteForSeparator() {
        return new Route();
    }
}
