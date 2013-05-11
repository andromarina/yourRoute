package com.yourroute;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/6/13
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class Repository {

    private final Uri CITIES_URI = Uri.parse("content://your.route.CitiesDB/Cities");
    private final ContentResolver contentResolver;

    public Repository(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }


    public ArrayList<City> getCities() {
        ArrayList<City> cities = new ArrayList<City>();
        Cursor cursor = this.contentResolver.query(CITIES_URI, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            City city = new City(id, name);
            cities.add(city);
            cursor.moveToNext();
        }
        return cities;
    }
}
