package com.yourroute;

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
    private final Uri CAR_TYPES_URI = Uri.parse("content://your.route.DB/CarTypes");
    private final ContentResolver contentResolver;

    public RoutesRepository(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }


    public ArrayList<Route> getRoutes() {
        ArrayList<Route> routes = new ArrayList<Route>();
        Cursor routesCursor = this.contentResolver.query(ROUTES_URI, null, null, null, null);
        routesCursor.moveToFirst();
        while (!routesCursor.isAfterLast()) {
            int id = routesCursor.getInt(11);
            String name = routesCursor.getString(9);
            String startEnd = routesCursor.getString(5);
            int carTypeId = routesCursor.getInt(8);

            String carTypeQuery = String.format("%s/%d", CAR_TYPES_URI.toString(), carTypeId) ;
            Cursor carTypesCursor = this.contentResolver.query(Uri.parse(carTypeQuery), null, null, null, null);
            carTypesCursor.moveToFirst();
            String carTypeName = carTypesCursor.getString(0);
            CarType type = new CarType(carTypeId, carTypeName);

            Route route = new Route(id, name, type, startEnd);
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
        int id = routesCursor.getInt(11);
        String name = routesCursor.getString(9);
        String startEnd = routesCursor.getString(5);
        int carTypeId = routesCursor.getInt(8);

        String carTypeQuery = String.format("%s/%d", CAR_TYPES_URI.toString(), carTypeId) ;
        Cursor carTypesCursor = this.contentResolver.query(Uri.parse(carTypeQuery), null, null, null, null);
        carTypesCursor.moveToFirst();
        String carTypeName = carTypesCursor.getString(0);
        CarType type = new CarType(carTypeId, carTypeName);

        Route route = new Route(id, name, type, startEnd);
        carTypesCursor.close();
        routesCursor.close();
        return route;
    }

}


