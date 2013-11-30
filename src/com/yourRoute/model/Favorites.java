package com.yourRoute.model;

import android.util.Log;
import com.yourRoute.Preferences;
import com.yourRoute.YourRouteApp;
import com.yourRoute.mainActivity.IFavoritesChangedListener;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 10/20/13
 * Time: 11:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class Favorites {

    private IFavoritesChangedListener listener;
    private final String LOG_TAG = getClass().getSimpleName();


    public void setListener(IFavoritesChangedListener favoritesChangedListener) {
        this.listener = favoritesChangedListener;
    }

    public ArrayList<Integer> getAllFavoritesId() {

       return Preferences.getAllFavorites();
    }

    public boolean isFavorite(int routeId) {

        if (getAllFavoritesId().contains(routeId)) {
            return true;
        } else return false;
    }

    //if removed from favorites(not favorite yet) - return false

    public boolean toggle(int routeId) {
        if (isFavorite(routeId)) {
            Preferences.deleteFavoriteRouteId(routeId);
            listener.onFavoritesChanged();
            return false;
        } else {
            Preferences.saveFavoriteRouteId(routeId);
            listener.onFavoritesChanged();
            return true;
        }
    }

    public ArrayList<Route> getAllFavoriteRoutes() {
        ArrayList<Integer> favoriteIds = getAllFavoritesId();
        ArrayList<Route> routesArray = new ArrayList<>();
        if (favoriteIds.isEmpty()) {
            Log.d(LOG_TAG, "getAllFavoriteRoutes: favotiteIds is empty");
            return routesArray;
        }

        for (int favoriteId : favoriteIds) {
            RoutesHolder routesHolder = YourRouteApp.getRoutesHolder();
            Route route = routesHolder.findRouteById(favoriteId);
            if (route != null) {
                routesArray.add(route);
                Log.d(LOG_TAG, "getAllFavoriteRoutes: route added");
            } else {
                Preferences.deleteFavoriteRouteId(favoriteId);
                Log.d(LOG_TAG, "getAllFavoriteRoutes: route " + favoriteId + "deleted");
            }
        }
        Collections.sort(routesArray);
        Log.d(LOG_TAG, "getAllFavorites: routes array size: " + routesArray.size());
        return routesArray;
    }

}
