package com.yourRoute.model;

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
    private final int cityId;
    private final float lat;
    private final float lng;

    public Stop(int id, String name, int stopIndex, int cityId, float lat, float lng) {
        this.id = id;
        this.name = name;
        this.stopIndex = stopIndex;
        this.cityId = cityId;
        this.lat = lat;
        this.lng = lng;
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

    public int getCityId() {
        return this.cityId;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }
}

