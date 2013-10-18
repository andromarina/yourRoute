package com.yourRoute.mainActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 9/22/13
 * Time: 4:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class FavoritesDataManager {

    private static final String FAVORITES = "Favorites";
    private static SharedPreferences sPref;
    private static Context context;
    private static FavoritesChangedListener listener;
    private static String LOG_TAG = "FavoritesDataManager";

    public static void initialize(Context context) {
        FavoritesDataManager.context = context;
    }

    public static void setListener(FavoritesChangedListener favoritesChangedListener) {
        listener = favoritesChangedListener;
    }

    public static void setStringArrayPref(Context context, String key, ArrayList<String> values) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();
        for (int i = 0; i < values.size(); i++) {
            a.put(values.get(i));
        }
        if (!values.isEmpty()) {
            editor.putString(key, a.toString());
        } else {
            editor.putString(key, null);
        }
        editor.commit();
    }

    public static ArrayList<String> getStringArrayPref(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = prefs.getString(key, null);
        ArrayList<String> urls = new ArrayList<String>();
        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);
                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    urls.add(url);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return urls;
    }

    public static void saveFavoriteRouteId(int routeId) {

        sPref = context.getSharedPreferences(FAVORITES, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ArrayList<String> favoritesSet = getAllFavoritesSet();
        favoritesSet.add(String.valueOf(routeId));
        setStringArrayPref(context, FAVORITES, favoritesSet);
        listener.onFavoritesChanged();
        Log.d(LOG_TAG, "Route id " + routeId);
    }

    public static void deleteFavoriteRouteId(int routeId) {

        sPref = context.getSharedPreferences(FAVORITES, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ArrayList<String> favoritesSet = getAllFavoritesSet();
        favoritesSet.remove(String.valueOf(routeId));
        setStringArrayPref(context, FAVORITES, favoritesSet);

        listener.onFavoritesChanged();
        Log.d(LOG_TAG, "Route id " + routeId + " was deleted from Preferences");
    }

    public static boolean isRouteIdPresentInPreferences(int routeId) {

        if (getAllFavoritesSet().contains(String.valueOf(routeId))) {
            return true;
        } else return false;
    }

    private static ArrayList<String> getAllFavoritesSet() {
        sPref = context.getSharedPreferences(FAVORITES, Context.MODE_PRIVATE);
        ArrayList<String> favoritesSet = getStringArrayPref(context, FAVORITES);
        return favoritesSet;
//        // TODO: android bug: https://code.google.com/p/android/issues/detail?id=27801
//        Set<String> result = new HashSet<>();
//        for (String favorite : favoritesSet) {
//            result.add(favorite);
//        }

      //  return result;
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
