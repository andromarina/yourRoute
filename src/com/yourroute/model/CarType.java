package com.yourroute.model;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/20/13
 * Time: 8:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class CarType {
    private final String name;
    private final int id;

    public CarType (int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
      return this.name;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.format("%s", this.name);
    }
}
