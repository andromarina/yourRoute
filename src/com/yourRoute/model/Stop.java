package com.yourRoute.model;

import android.util.Log;

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
    private final String LOG_TAG = getClass().getSimpleName();

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
        Log.d(LOG_TAG, this.name + " lat: " + lat);
        return lat;
    }

    public float getLng() {
        Log.d(LOG_TAG, this.name + " lng: " + lng);
        return lng;
    }
}

