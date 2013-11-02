package com.yourRoute.model;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import contentProvider.Contracts.Cities;
import contentProvider.Contracts.Stops;
import contentProvider.RoutesContentProvider;

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
    private final String LOG_TAG = this.getClass().getSimpleName();
    private final Uri STOPS_URI = Uri.parse("content://" + RoutesContentProvider.AUTHORITY + "/" + Stops.STOPS_PATH);
    private final Uri STOPS_SUGGESTIONS_URI = Uri.parse("content://" + RoutesContentProvider.AUTHORITY + "/" + Stops.STOPS_SUGGESTIONS_PATH);
    public final static String STOP_ID_COLUMN_NAME = Stops._ID;
    public final static String STOP_NAME_COLUMN_NAME = Stops.NAME;
    public final static String STOP_NAME_FOR_SEARCH = Stops.STOP_NAME_FOR_SEARCH;
    private final static String STOP_INDEX_COLUMN_NAME = Stops.STOP_INDEX_COLUMN_NAME;
    private final static String ROUTE_ID_COLUMN_NAME = Stops.ROUTE_ID_COLUMN_NAME;
    private final static String CITY_ID_COLUMN_NAME = Stops.CITY_ID_COLUMN_NAME;
    private final String LAT_COLUMN_NAME = Stops.LAT;
    private final String LNG_COLUMN_NAME = Stops.LNG;


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

    public Cursor getStopsSuggestionsCursor(String text, int cityId) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(STOP_NAME_FOR_SEARCH);
        stringBuilder.append(" LIKE ");
        stringBuilder.append("'%");
        stringBuilder.append(text.toLowerCase());
        stringBuilder.append("%'");
        stringBuilder.append(" AND CityId=");
        stringBuilder.append(cityId);
        String selection = stringBuilder.toString();
        Cursor stopsCursor = this.contentResolver.query(STOPS_SUGGESTIONS_URI, null, selection, null, null);
        return stopsCursor;
    }

    private Stop createStop(Cursor stopsCursor) {

        int stopIdColumnIndex = stopsCursor.getColumnIndex(STOP_ID_COLUMN_NAME);
        int id = stopsCursor.getInt(stopIdColumnIndex);

        int stopNameColumnIndex = stopsCursor.getColumnIndex(STOP_NAME_COLUMN_NAME);
        String name = stopsCursor.getString(stopNameColumnIndex);

        int stopIndexColumnIndex = stopsCursor.getColumnIndex(STOP_INDEX_COLUMN_NAME);
        int stopIndex = stopsCursor.getInt(stopIndexColumnIndex);

        int cityIdColumnIndex = stopsCursor.getColumnIndex(CITY_ID_COLUMN_NAME);
        int cityId = stopsCursor.getInt(cityIdColumnIndex);

        int latColumnIndex = stopsCursor.getColumnIndex(LAT_COLUMN_NAME);
        float lat = stopsCursor.getFloat(latColumnIndex);

        int lngColumnIndex = stopsCursor.getColumnIndex(LNG_COLUMN_NAME);
        float lng = stopsCursor.getFloat(lngColumnIndex);

        Log.i(LOG_TAG, "Stop index = " + stopIndex + " Stop name " + name);
        Stop stop = new Stop(id, name, stopIndex, cityId, lat, lng);
        return stop;
    }


}
