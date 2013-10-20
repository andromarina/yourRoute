package com.yourRoute.mainActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.yourRoute.R;
import com.yourRoute.YourRouteApp;
import com.yourRoute.model.Favorites;
import com.yourRoute.model.Route;
import com.yourRoute.model.RoutesHolder;
import com.yourRoute.routeDetailsActivity.RouteDetailsActivity;

import java.util.ArrayList;
import java.util.Collections;

public class FavoritesController implements FavoritesChangedListener {

    private final String LOG_TAG = this.getClass().getSimpleName();
    private MainActivity activity;
    private Context context;
    private FavoritesListAdapter adapter;
    private RoutesHolder routesHolder;
    private Favorites favorites;

    public FavoritesController(MainActivity activity) {
        this.activity = activity;
        this.context = activity;
        this.routesHolder = YourRouteApp.getRoutesHolder();
        this.favorites = YourRouteApp.getFavorites();
        refreshFavoritesList();
        YourRouteApp.getFavorites().setListener(this);
    }

    public void refreshFavoritesList() {

        final ArrayList<Route> routes = getAllFavoriteRoutes();
        TextView noResults = activity.getNoFavorites();
        if (routes.isEmpty()) {
            Log.d(LOG_TAG, "favorites array is empty");
            noResults.setVisibility(View.VISIBLE);
        } else {
           noResults.setVisibility(View.GONE);
        }

        ListView favoritesListView = this.activity.getFavoritesListView();
        this.adapter = new FavoritesListAdapter(this.context, R.layout.route_list_item, routes);

        favoritesListView.setAdapter(this.adapter);

        favoritesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, RouteDetailsActivity.class);
                int routeID = routes.get(position).getId();
                intent.putExtra("routeID", routeID);
                activity.startActivity(intent);
            }
        });
    }

    private ArrayList<Route> getAllFavoriteRoutes() {
        ArrayList<Integer> favoriteIds = this.favorites.getAllFavoritesId();
        ArrayList<Route> routesArray = new ArrayList<>();
        if (favoriteIds.isEmpty()) {
            return routesArray;
        }

        for (int favoriteId : favoriteIds) {
            Route route = this.routesHolder.findRouteById(favoriteId);
            routesArray.add(route);
        }
        Collections.sort(routesArray);
        return routesArray;
    }

    @Override
    public void onFavoritesChanged() {
        refreshFavoritesList();
    }
}
