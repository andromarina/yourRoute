package com.yourRoute;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.yourRoute.mainActivity.FavoritesChangedListener;

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
    private static FavoritesChangedListener listener;
    private static SharedPreferences sPref;
    private static Context context;
    private static String LOG_TAG = "Preferences";

    public static void initialize(Context context) {
        Preferences.context = context;
    }

    public static void setListener(FavoritesChangedListener favoritesChangedListener) {
        listener = favoritesChangedListener;
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
        int savedCityId = sPref.getInt(CITY_ID, 24);
        Log.i(LOG_TAG, savedCityId + "City id loaded");
        return savedCityId;
    }

    public static void saveFavoriteRouteId(int routeId) {

        sPref = context.getSharedPreferences(FAVORITES, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        Set<String> favoritesSet = getAllFavoritesSet();
        favoritesSet.add(String.valueOf(routeId));
        ed.putStringSet(FAVORITES, favoritesSet);
        boolean result = ed.commit();
        listener.onChange();
        Log.d(LOG_TAG, "Route id " + routeId + " was saved to Preferences (" + result + ")");
    }

    public static void deleteFavoriteRouteId(int routeId) {

        sPref = context.getSharedPreferences(FAVORITES, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        Set<String> favoritesSet = getAllFavoritesSet();
        favoritesSet.remove(String.valueOf(routeId));
        ed.putStringSet(FAVORITES, favoritesSet);
        ed.commit();
        listener.onChange();
        Log.d(LOG_TAG, "Route id " + routeId + " was deleted from Preferences");
    }

    public static boolean isRouteIdPresentInPreferences(int routeId) {

        if (getAllFavoritesSet().contains(String.valueOf(routeId))) {
            return true;
        } else return false;
    }

    private static Set<String> getAllFavoritesSet() {
        sPref = context.getSharedPreferences(FAVORITES, Context.MODE_PRIVATE);
        Set<String> favoritesSet = sPref.getStringSet(FAVORITES, new HashSet<String>());

        // TODO: android bug: https://code.google.com/p/android/issues/detail?id=27801
        Set<String> result = new HashSet<>();
        for (String favorite : favoritesSet) {
            result.add(favorite);
        }

        return result;
    }

    public static ArrayList<Integer> getAllFavoritesId() {
        ArrayList<Integer> favoritesId = new ArrayList<>();
        for (String favorite : getAllFavoritesSet()) {
            int id = Integer.parseInt(favorite);
            favoritesId.add(id);
        }
        return favoritesId;
    }

}
