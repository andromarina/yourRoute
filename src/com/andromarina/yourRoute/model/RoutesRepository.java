package com.andromarina.yourRoute.model;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;

public class RoutesRepository {

    private final Uri ROUTES_URI = Uri.parse("content://your.route.DB/Routes");
    private final Uri ROUTES_BY_STOP_NAME_URI = Uri.parse("content://your.route.DB/RouteByStopName");
    private final Uri ROUTES_BY_ROUTE_NAME_URI = Uri.parse("content://your.route.DB/RoutesByRouteName");
    private final Uri CAR_TYPES_URI = Uri.parse("content://your.route.DB/CarTypes");
    private final ContentResolver contentResolver;
    private final static String ROUTE_LENGTH_COLUMN_NAME = "Length";
    private final static String ROUTE_INTERVAL_COLUMN_NAME = "Interval";
    private final static String ROUTE_WORK_TIME_COLUMN_NAME = "WorkTime";
    private final static String START_END_COLUMN_NAME = "StartEnd";
    private final static String CAR_TYPE_ID_COLUMN_NAME = "CarTypeID";
    private final static String CAR_TYPE_NAME_COLUMN_NAME = "CarTypeName";
    public final static String ROUTE_NAME_COLUMN_NAME = "RouteName";
    private final static String STOP_NAME_FOR_SEARCH = "StopNameForSearch";
    private final static String ROUTE_ID_COLUMN_NAME = "_id";
    private String LOG_TAG = this.getClass().getSimpleName();

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

    public ArrayList<Route> getRoutesByStopName(String query, int cityId) {
        ArrayList<Route> routes = new ArrayList<Route>();
        Cursor routesCursor;
        String selection;

        selection = STOP_NAME_FOR_SEARCH + " LIKE " + "'%" + query.toLowerCase() + "%'" + " AND Routes.CityId=" + cityId;
        routesCursor = this.contentResolver.query(ROUTES_BY_STOP_NAME_URI, null, selection, null, null);

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

    public ArrayList<Route> getRoutesByRouteName(String query, int cityId) {
        ArrayList<Route> routes = new ArrayList<Route>();
        Cursor routesCursor;
        String selection;

        selection = ROUTE_NAME_COLUMN_NAME + " LIKE " + "'%" + query.toUpperCase() + "%'" + " AND Routes.CityId=" + cityId;
        routesCursor = this.contentResolver.query(ROUTES_BY_ROUTE_NAME_URI, null, selection, null, null);

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

    public Cursor getRouteSuggestionsCursor(String text, int cityId) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ROUTE_NAME_COLUMN_NAME);
        stringBuilder.append(" LIKE ");
        stringBuilder.append("'%");
        stringBuilder.append(text.toLowerCase());
        stringBuilder.append("%'");
        stringBuilder.append(" AND CityId=");
        stringBuilder.append(cityId);
        String selection = stringBuilder.toString();
        Cursor stopsCursor = this.contentResolver.query(ROUTES_BY_ROUTE_NAME_URI, null, selection, null, null);
        return stopsCursor;
    }

    public ArrayList<Route> uniteRoutes(ArrayList<Route> mainArray, ArrayList<Route> additionalArray) {
        ArrayList<Route> result = new ArrayList<Route>();
        for (Route route : mainArray) {
            int iID = route.getId();
            for (int j = 0; j < additionalArray.size(); ++j) {
                Route jRoute = additionalArray.get(j);
                int jID = jRoute.getId();
                if (iID == jID) {
                    result.add(route);
                    break;
                }
            }
        }
        Log.d(LOG_TAG, "united array size is: " + result.size());
        return result;
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

        int lengthColumnIndex = routesCursor.getColumnIndex(ROUTE_LENGTH_COLUMN_NAME);
        String length = routesCursor.getString(lengthColumnIndex);

        int intervalColumnIndex = routesCursor.getColumnIndex(ROUTE_INTERVAL_COLUMN_NAME);
        String interval = routesCursor.getString(intervalColumnIndex);

        int workTimeColumnIndex = routesCursor.getColumnIndex(ROUTE_WORK_TIME_COLUMN_NAME);
        String startTime = routesCursor.getString(workTimeColumnIndex);

        int carTypeColumnIndex = carTypesCursor.getColumnIndex(CAR_TYPE_NAME_COLUMN_NAME);
        String carTypeName = carTypesCursor.getString(carTypeColumnIndex);
        CarType type = new CarType(carTypeId, carTypeName);

        Route route = new Route(id, name, type, startEnd, length, interval, startTime);
        return route;
    }

}


