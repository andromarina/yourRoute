package com.yourroute.model;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.yourroute.model.CarType;
import com.yourroute.model.Route;

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
    private final Uri CAR_TYPES_URI = Uri.parse("content://your.route.DB/CarTypes");
    private final ContentResolver contentResolver;
    private final static int CAR_TYPE_ID_COLUMN_INDEX = 8;
    private final static int ROUTE_ID_COLUMN_INDEX = 11;
    private final static int ROUTE_NAME_COLUMN_INDEX = 9;
    private final static int START_END_COLUMN_NAME = 5;
    private final static int CAR_TYPE_NAME_COLUMN_INDEX = 0;
    private final static String CITY_ID_COLUMN_NAME = "CityId";

    public RoutesRepository(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }


    public ArrayList<Route> getRoutes() {
        ArrayList<Route> routes = new ArrayList<Route>();
        Cursor routesCursor = this.contentResolver.query(ROUTES_URI, null, null, null, null);
        routesCursor.moveToFirst();
        while (!routesCursor.isAfterLast()) {
            int carTypeId = routesCursor.getInt(CAR_TYPE_ID_COLUMN_INDEX);
            String carTypeQuery = String.format("%s/%d", CAR_TYPES_URI.toString(), carTypeId) ;
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

    public Route getRoute(int routeId) {
        String query = String.format("%s/%d", ROUTES_URI.toString(), routeId);
        Log.i("Test", "getRoute query" + query);
        Cursor routesCursor = this.contentResolver.query(Uri.parse(query), null, null, null, null);
        routesCursor.moveToFirst();
        int carTypeId = routesCursor.getInt(CAR_TYPE_ID_COLUMN_INDEX);

        String carTypeQuery = String.format("%s/%d", CAR_TYPES_URI.toString(), carTypeId) ;
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
            int carTypeId = routesCursor.getInt(CAR_TYPE_ID_COLUMN_INDEX);
            String carTypeQuery = String.format("%s/%d", CAR_TYPES_URI.toString(), carTypeId) ;
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

        int id = routesCursor.getInt(ROUTE_ID_COLUMN_INDEX);
        String name = routesCursor.getString(ROUTE_NAME_COLUMN_INDEX);
        String startEnd = routesCursor.getString(START_END_COLUMN_NAME);
        int carTypeId = routesCursor.getInt(CAR_TYPE_ID_COLUMN_INDEX);

        String carTypeName = carTypesCursor.getString(CAR_TYPE_NAME_COLUMN_INDEX);
        CarType type = new CarType(carTypeId, carTypeName);

        Route route = new Route(id, name, type, startEnd);
        return route;
    }

}


