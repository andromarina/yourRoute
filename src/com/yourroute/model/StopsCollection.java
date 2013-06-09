package com.yourroute.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StopsCollection extends ArrayList<Stop> implements Comparator<Stop> {

    public StopsCollection(ArrayList<Stop> stops, boolean isForward) {
        for (Stop stop : stops) {
            if ((stop.getStopIndex() > 0) == isForward) {
                super.add(stop);
            }
        }
        Collections.sort(this, this);
    }

    @Override
    public int compare(Stop stop1, Stop stop2) {
        return stop1.getStopIndex() - stop2.getStopIndex();
    }
}
