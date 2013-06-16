package com.yourroute.model;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/19/13
 * Time: 7:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class RoutesRepository {

    private final Uri ROUTES_URI = Uri.parse("content://your.route.DB/Routes");
    private final Uri ROUTES_BY_STOP_NAME_URI = Uri.parse("content://your.route.DB/RouteByStopName");
    private final Uri CAR_TYPES_URI = Uri.parse("content://your.route.DB/CarTypes");
    private final ContentResolver contentResolver;
    private final static String ROUTE_DURATION_COLUMN_NAME = "Duration";
    private final static String ROUTE_LENGTH_COLUMN_NAME = "Length";
    private final static String ROUTE_INTERVAL_COLUMN_NAME = "Interval";
    private final static String ROUTE_END_TIME_COLUMN_NAME = "EndTime";
    private final static String ROUTE_START_TIME_COLUMN_NAME = "StartTime";
    private final static String START_END_COLUMN_NAME = "StartEnd";
    private final static String CAR_TYPE_ID_COLUMN_NAME = "CarTypeID";
    private final static String ROUTE_NAME_COLUMN_NAME = "RouteName";
    private final static String STOP_NAME_COLUMN_NAME = "StopName";
    private final static String ROUTE_ID_COLUMN_NAME = "_id";

    private final static int CAR_TYPE_NAME_COLUMN_INDEX = 0;
    private final static String CITY_ID_COLUMN_NAME = "Routes.CityId";

    public RoutesRepository(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    public Route getRoute(int routeId) {
        String query = String.format("%s/%d", ROUTES_URI.toString(), routeId);
        Log.i("Test", "getRoute query" + query);
        Cursor routesCursor = this.contentResolver.query(Uri.parse(query), null, null, null, null);
        routesCursor.moveToFirst();
        int carTypeIdColumnIndex = routesCursor.getColumnIndex(CAR_TYPE_ID_COLUMN_NAME);
        int carTypeId = routesCursor.getInt(carTypeIdColumnIndex);

        String carTypeQuery = String.format("%s/%d", CAR_TYPES_URI.toString(), carTypeId);
        Cursor carTypesCursor = this.contentResolver.query(Uri.parse(carTypeQuery), null, null, null, null);
        carTypesCursor.moveToFirst();
        Route route = createRoute(routesCursor, carTypesCursor);
        carTypesCursor.close();
        routesCursor.close();
        return route;
    }

    public ArrayList<Route> getRoutesByCityID(int cityId) {
        ArrayList<Route> routes = new ArrayList<Route>();
        String selection = CITY_ID_COLUMN_NAME + "=" + cityId;
        Cursor routesCursor = this.contentResolver.query(ROUTES_URI, null, selection, null, null);
        routesCursor.moveToFirst();
        while (!routesCursor.isAfterLast()) {
            int carTypeColumnIndex = routesCursor.getColumnIndex(CAR_TYPE_ID_COLUMN_NAME);
            int carTypeId = routesCursor.getInt(carTypeColumnIndex);
            String carTypeQuery = String.format("%s/%d", CAR_TYPES_URI.toString(), carTypeId);
            Cursor carTypesCursor = this.contentResolver.query(Uri.parse(carTypeQuery), null, null, null, null);
            carTypesCursor.moveToFirst();
            Route route = createRoute(routesCursor, carTypesCursor);
            routes.add(route);
            routesCursor.moveToNext();
            carTypesCursor.close();
        }
        routesCursor.close();
        return routes;
    }

    public ArrayList<Route> getRoutesByStopName(String query, int cityId) {
        ArrayList<Route> routes = new ArrayList<Route>();
        Cursor routesCursor;
        String selection;

        if (query.isEmpty()) {
            selection = CITY_ID_COLUMN_NAME + "=" + cityId;
            routesCursor = this.contentResolver.query(ROUTES_BY_STOP_NAME_URI, null, selection, null, null);
        } else {
            selection = STOP_NAME_COLUMN_NAME + " LIKE " + "'%" + query + "%'" + " AND Routes.CityId=" + cityId;
            routesCursor = this.contentResolver.query(ROUTES_BY_STOP_NAME_URI, null, selection, null, null);
        }
        routesCursor.moveToFirst();
        while (!routesCursor.isAfterLast()) {
            int carTypeColumnIndex = routesCursor.getColumnIndex(CAR_TYPE_ID_COLUMN_NAME);
            int carTypeId = routesCursor.getInt(carTypeColumnIndex);
            String carTypeQuery = String.format("%s/%d", CAR_TYPES_URI.toString(), carTypeId);
            Cursor carTypesCursor = this.contentResolver.query(Uri.parse(carTypeQuery), null, null, null, null);
            carTypesCursor.moveToFirst();
            Route route = createRoute(routesCursor, carTypesCursor);
            routes.add(route);
            routesCursor.moveToNext();
            carTypesCursor.close();
        }
        routesCursor.close();
        return routes;
    }


    private Route createRoute(Cursor routesCursor, Cursor carTypesCursor) {

        int routeIdColumnIndex = routesCursor.getColumnIndex(ROUTE_ID_COLUMN_NAME);

        int id = routesCursor.getInt(routeIdColumnIndex);
        int routeNameColumnIndex = routesCursor.getColumnIndex(ROUTE_NAME_COLUMN_NAME);
        String name = routesCursor.getString(routeNameColumnIndex);

        int startEndColumnIndex = routesCursor.getColumnIndex(START_END_COLUMN_NAME);
        String startEnd = routesCursor.getString(startEndColumnIndex);

        int carTypeIdColumnName = routesCursor.getColumnIndex(CAR_TYPE_ID_COLUMN_NAME);
        int carTypeId = routesCursor.getInt(carTypeIdColumnName);

        int durationColumnIndex = routesCursor.getColumnIndex(ROUTE_DURATION_COLUMN_NAME);
        String duration = routesCursor.getString(durationColumnIndex);

        int lengthColumnIndex = routesCursor.getColumnIndex(ROUTE_LENGTH_COLUMN_NAME);
        String length = routesCursor.getString(lengthColumnIndex);

        int intervalColumnIndex = routesCursor.getColumnIndex(ROUTE_INTERVAL_COLUMN_NAME);
        String interval = routesCursor.getString(intervalColumnIndex);

        int startTimeColumnIndex = routesCursor.getColumnIndex(ROUTE_START_TIME_COLUMN_NAME);
        String startTime = routesCursor.getString(startTimeColumnIndex);

        int endTimeColumnIndex = routesCursor.getColumnIndex(ROUTE_END_TIME_COLUMN_NAME);
        String endTime = routesCursor.getString(endTimeColumnIndex);

        String carTypeName = carTypesCursor.getString(CAR_TYPE_NAME_COLUMN_INDEX);
        CarType type = new CarType(carTypeId, carTypeName);

        Route route = new Route(id, name, type, startEnd, duration, length, interval, startTime, endTime);
        return route;
    }

}


