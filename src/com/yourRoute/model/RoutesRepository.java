package com.yourRoute.model;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import contentProvider.Contracts.Routes;
import contentProvider.RoutesContentProvider;

import java.util.ArrayList;

public class RoutesRepository {

    private final Uri ROUTES_URI = Uri.parse("content://" + RoutesContentProvider.AUTHORITY + "/" + Routes.ROUTES_PATH);
    private final Uri ROUTES_BY_STOP_NAME_URI = Uri.parse("content://" + RoutesContentProvider.AUTHORITY + "/" + Routes.ROUTES_BY_STOP_NAME_PATH);
    private final Uri ROUTES_BY_ROUTE_NAME_URI = Uri.parse("content://" + RoutesContentProvider.AUTHORITY + "/" + Routes.ROUTES_BY_ROUTE_NAME_PATH);
    private final ContentResolver contentResolver;
    private final static String ROUTE_LENGTH_COLUMN_NAME = Routes.ROUTE_LENGTH_COLUMN_NAME;
    private final static String ROUTE_INTERVAL_COLUMN_NAME = Routes.ROUTE_INTERVAL_COLUMN_NAME;
    private final static String ROUTE_WORK_TIME_COLUMN_NAME = Routes.ROUTE_WORK_TIME_COLUMN_NAME;
    private final static String START_END_COLUMN_NAME = Routes.START_END_COLUMN_NAME;
    private final static String CAR_TYPE_ID_COLUMN_NAME = Routes.CAR_TYPE_ID_COLUMN_NAME;
    public final static String ROUTE_NAME_COLUMN_NAME = Routes.ROUTE_NAME_COLUMN_NAME;
    private final static String STOP_NAME_FOR_SEARCH = Routes.STOP_NAME_FOR_SEARCH;
    private String LOG_TAG = this.getClass().getSimpleName();

    public RoutesRepository(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    public Route getRoute(int routeId) {
        String query = String.format("%s/%d", ROUTES_URI.toString(), routeId);
        Log.i("Test", "getRoute query" + query);
        Cursor routesCursor = this.contentResolver.query(Uri.parse(query), null, null, null, null);
        routesCursor.moveToFirst();
        Route route = createRoute(routesCursor);
        routesCursor.close();
        return route;
    }

    public ArrayList<Route> getRoutesByStopName(String query, int cityId) {
        ArrayList<Route> routes = new ArrayList<Route>();
        Cursor routesCursor;
        String selection;

        selection = STOP_NAME_FOR_SEARCH + " LIKE " + "'%" + query.toLowerCase() + "%'" + " AND Routes.CityId=" + cityId;
        Log.d(LOG_TAG, "getRoutesByStopName: " + selection);
        routesCursor = this.contentResolver.query(ROUTES_BY_STOP_NAME_URI, null, selection, null, null);

        routesCursor.moveToFirst();
        while (!routesCursor.isAfterLast()) {
            Route route = createRoute(routesCursor);
            routes.add(route);
            routesCursor.moveToNext();
        }
        routesCursor.close();
        return routes;
    }

    public ArrayList<Route> getRoutesByRouteName(String query, int cityId) {
        ArrayList<Route> routes = new ArrayList<Route>();
        Cursor routesCursor;
        String selection;

        selection = ROUTE_NAME_COLUMN_NAME + " LIKE " + "'%" + query + "%'" + " AND Routes.CityId=" + cityId;
        Log.d(LOG_TAG, "getRoutesByRouteName: " + selection);
        routesCursor = this.contentResolver.query(ROUTES_BY_ROUTE_NAME_URI, null, selection, null, null);

        routesCursor.moveToFirst();
        while (!routesCursor.isAfterLast()) {
            Route route = createRoute(routesCursor);
            routes.add(route);
            routesCursor.moveToNext();
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
        Log.d(LOG_TAG, "getRouteSuggestionsCursor: " + selection);
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

    private Route createRoute(Cursor routesCursor) {

        // TODO refactor contracts. Column index was hardcoded

        int id = routesCursor.getInt(1);
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

        Route route = new Route(id, name, carTypeId, startEnd, length, interval, startTime);
        return route;
    }

}


