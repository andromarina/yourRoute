package com.yourRoute;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/11/13
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Preferences {

    private static final String CITY_ID = "CityId";
    private static final String FAVORITES = "Favorites";
    private static SharedPreferences sPref;
    private static Context context;
    private static String LOG_TAG = "Preferences";

    public static void initialize(Context context) {
        Preferences.context = context;
    }

    public static void saveCityId(int cityId) {

        sPref = context.getSharedPreferences(CITY_ID, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt(CITY_ID, cityId);
        ed.commit();
        Log.d(LOG_TAG, cityId + "City id saved");
    }

    public static int getSavedCityId() {

        sPref = context.getSharedPreferences(CITY_ID, Context.MODE_PRIVATE);
        int savedCityId = sPref.getInt(CITY_ID, 2);
        Log.i(LOG_TAG, savedCityId + "City id loaded");
        return savedCityId;
    }

    public static void saveFavoriteRouteId(Integer routeId) {

        sPref = context.getSharedPreferences(FAVORITES, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ArrayList<Integer> favoritesSetInt = getAllFavorites();
        favoritesSetInt.add(routeId);

        Set<String> favoritesSetString = convertIntToString(favoritesSetInt);
        ed.putStringSet(FAVORITES, new HashSet<>(favoritesSetString));
        boolean result = ed.commit();
        Log.d(LOG_TAG, "Route id " + routeId + " was saved to Preferences (" + result + ")");
    }

    public static void deleteFavoriteRouteId(Integer routeId) {

        sPref = context.getSharedPreferences(FAVORITES, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ArrayList<Integer> favoritesSetInt = getAllFavorites();
        favoritesSetInt.remove(routeId);
        Set<String> favoritesSetString = convertIntToString(favoritesSetInt);
        ed.putStringSet(FAVORITES, favoritesSetString);
        ed.commit();
        Log.d(LOG_TAG, "Route id " + routeId + " was deleted from Preferences");
    }

    public static ArrayList<Integer> getAllFavorites() {
        sPref = context.getSharedPreferences(FAVORITES, Context.MODE_PRIVATE);
        Set<String> favoritesSet = sPref.getStringSet(FAVORITES, new HashSet<String>());

        // TODO: android bug: https://code.google.com/p/android/issues/detail?id=27801
        ArrayList<Integer> favoritesId = convertStringSetToInt(favoritesSet);
        return favoritesId;
    }

    private static Set<String> convertIntToString(ArrayList<Integer> favoritesSetInt) {
        Set<String> favoritesSetString = new HashSet<>();

        for (Integer favorite : favoritesSetInt) {
            String id = String.valueOf(favorite);
            favoritesSetString.add(id);
        }
        return favoritesSetString;
    }

    private static ArrayList<Integer> convertStringSetToInt(Set<String> favoritesSet) {
        ArrayList<Integer> favoritesId = new ArrayList<>();
        for (String favorite : favoritesSet) {
            int id = Integer.parseInt(favorite);
            favoritesId.add(id);
        }
        return favoritesId;
    }
}
