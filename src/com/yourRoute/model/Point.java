package com.yourRoute.model;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 11/17/13
 * Time: 6:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Point {
    private float lat;
    private float lng;

    public Point(float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

}
