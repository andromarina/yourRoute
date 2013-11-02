package com.yourRoute.model;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import contentProvider.Contracts.Cities;
import contentProvider.RoutesContentProvider;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/6/13
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class CitiesRepository {

    private final Uri CITIES_URI = Uri.parse("content://" + RoutesContentProvider.AUTHORITY + "/" + Cities.TABLE_NAME);
    private final ContentResolver contentResolver;
    private final String ID_COLUMN_NAME = Cities._ID;
    private final String NAME_COLUMN_NAME = Cities.NAME;
    private final String LAT_COLUMN_NAME = Cities.LAT;
    private final String LNG_COLUMN_NAME = Cities.LNG;

    public CitiesRepository(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }


    public ArrayList<City> getCities() {
        ArrayList<City> cities = new ArrayList<City>();
        Cursor cursor = this.contentResolver.query(CITIES_URI, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int idColumnIndex = cursor.getColumnIndex(ID_COLUMN_NAME);
            int id = cursor.getInt(idColumnIndex);

            int nameColumnIndex = cursor.getColumnIndex(NAME_COLUMN_NAME);
            String name = cursor.getString(nameColumnIndex);

            int latColumnIndex = cursor.getColumnIndex(LAT_COLUMN_NAME);
            float lat = cursor.getFloat(latColumnIndex);

            int lngColumnIndex = cursor.getColumnIndex(LNG_COLUMN_NAME);
            float lng = cursor.getFloat(lngColumnIndex);

            City city = new City(id, name, lat, lng);
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
        int nameColumnIndex = cursor.getColumnIndex(NAME_COLUMN_NAME);
        String name = cursor.getString(nameColumnIndex);

        int latColumnIndex = cursor.getColumnIndex(LAT_COLUMN_NAME);
        float lat = cursor.getFloat(latColumnIndex);

        int lngColumnIndex = cursor.getColumnIndex(LNG_COLUMN_NAME);
        float lng = cursor.getFloat(lngColumnIndex);
        City city = new City(cityId, name, lat, lng);
        cursor.close();
        return city;
    }
}
