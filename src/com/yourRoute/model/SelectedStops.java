package com.yourRoute.model;

import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 10/19/13
 * Time: 8:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class SelectedStops {
    private String selectedStop = "";
    private String optionalSelectedStop = "";
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
}
