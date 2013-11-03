package com.yourRoute.model;

import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 10/19/13
 * Time: 8:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Selections {
    private String selectedStop = "";
    private String optionalSelectedStop = "";
    private Route selectedRoute;
    private final String LOG_TAG = this.getClass().getSimpleName();

    public void saveSelectedStop(String string) {
        this.selectedStop = string;
        Log.d(LOG_TAG, "search phrase: " + this.selectedStop);
    }

    public void saveOptionalSelectedStop(String string) {
        this.optionalSelectedStop = string;
        Log.d(LOG_TAG, "search phrase optional: " + this.optionalSelectedStop);
    }

    public String getSelectedStop() {
        return selectedStop;
    }

    public String getOptionalSelectedStop() {
        return optionalSelectedStop;
    }

    public void saveSelectedRoute(Route route) {
        this.selectedRoute = route;
    }

    public Route getSelectedRoute() {
        return selectedRoute;
    }
}
