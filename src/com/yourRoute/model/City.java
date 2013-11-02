package com.yourRoute.model;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/6/13
 * Time: 5:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class City {
    private final int id;
    private final String name;
    private final float lat;
    private final float lng;

    public City(int id, String name, float lat, float lng) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s", this.id, this.name);
    }
}
