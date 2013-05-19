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
    private final ContentResolver contentResolver;

    public RoutesRepository(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }


    public ArrayList<Route> getRoutes() {
        ArrayList<Route> routes = new ArrayList<Route>();
        Cursor cursor = this.contentResolver.query(ROUTES_URI, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(11);
            String name = cursor.getString(9);
            String startEnd = cursor.getString(5);
            String type = cursor.getString(8);
            Route route = new Route(id, name, type, startEnd);
            routes.add(route);
            cursor.moveToNext();
        }
        cursor.close();
        return routes;
    }

    public City getCity(int cityId) {
        String query = String.format("%s/%d", CITIES_URI.toString(), cityId);
        Log.i("Test", "getCity query" + query);
        Cursor cursor = this.contentResolver.query(Uri.parse(query), null, null, null, null);
        cursor.moveToFirst();
        String name = cursor.getString(1);
        City city = new City(cityId, name);
        cursor.close();
        return city;
    }
}


