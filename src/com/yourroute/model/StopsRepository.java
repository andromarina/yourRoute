package com.yourroute.model;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 6/4/13
 * Time: 9:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class StopsRepository {
    private final ContentResolver contentResolver;
    private final String LOG_TAG = "StopsRepository";
    private final Uri STOPS_URI = Uri.parse("content://your.route.DB/Stops");
    private final static String STOP_ID_COLUMN_NAME = "_id";
    private final static String STOP_NAME_COLUMN_NAME = "StopName";
    private final static String STOP_INDEX_COLUMN_NAME = "StopIndex";
    private final static String ROUTE_ID_COLUMN_NAME = "RouteId";


    public StopsRepository(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    public ArrayList<Stop> getStopsByRouteId(int routeId) {
        ArrayList<Stop> stops = new ArrayList<Stop>();
        String selection = ROUTE_ID_COLUMN_NAME + "=" + routeId;
        Cursor stopsCursor = this.contentResolver.query(STOPS_URI, null, selection, null, null);
        stopsCursor.moveToFirst();
        while (!stopsCursor.isAfterLast()) {
            Stop stop = createStop(stopsCursor);
            stops.add(stop);
            stopsCursor.moveToNext();
        }
        stopsCursor.close();
        return stops;
    }

    private Stop createStop(Cursor stopsCursor) {

        int stopIdColumnIndex = stopsCursor.getColumnIndex(STOP_ID_COLUMN_NAME);
        int id = stopsCursor.getInt(stopIdColumnIndex);

        int stopNameColumnIndex = stopsCursor.getColumnIndex(STOP_NAME_COLUMN_NAME);
        String name = stopsCursor.getString(stopNameColumnIndex);

        int stopIndexColumnIndex = stopsCursor.getColumnIndex(STOP_INDEX_COLUMN_NAME);
        int stopIndex = stopsCursor.getInt(stopIndexColumnIndex);
        Log.i(LOG_TAG, "Stop index = " + stopIndex + " Stop name " + name);
        Stop stop = new Stop(id, name, stopIndex);
        return stop;
    }


}
