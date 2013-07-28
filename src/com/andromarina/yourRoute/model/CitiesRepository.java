package com.andromarina.yourRoute.model;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/6/13
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class CitiesRepository {

    private final Uri CITIES_URI = Uri.parse("content://your.route.DB/Cities");
    private final ContentResolver contentResolver;

    public CitiesRepository(ContentResolver contentResolver) {
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
        cursor.close();
        return cities;
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
