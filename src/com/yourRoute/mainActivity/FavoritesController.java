package com.yourRoute.mainActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.yourRoute.Preferences;
import com.yourRoute.R;
import com.yourRoute.model.Route;
import com.yourRoute.model.RoutesRepository;
import com.yourRoute.routeDetailsActivity.RouteDetailsActivity;
import com.yourRoute.searchResultsActivity.RouteListAdapter;

import java.util.ArrayList;

public class FavoritesController implements FavoritesChangedListener {

    private final String LOG_TAG = this.getClass().getSimpleName();
    private MainActivity activity;
    private Context context;
    private RouteListAdapter adapter;
    private RoutesRepository routesRepository;

    public FavoritesController(Context context, MainActivity activity, RoutesRepository routesRepository) {
        this.activity = activity;
        this.context = context;
        this.routesRepository = routesRepository;
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
        this.adapter = new RouteListAdapter(this.context, R.layout.route_list_item, routes);
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
        ArrayList<Integer> favoriteIds = Preferences.getAllFavoritesId();
        ArrayList<Route> routesArray = new ArrayList<>();
        if (favoriteIds.size() == 0) {
            return routesArray;
        }

        for (int favoriteId : favoriteIds) {
            Route route = routesRepository.getRoute(favoriteId);
            routesArray.add(route);
        }
        return routesArray;
    }

    @Override
    public void onChange() {
        refreshFavoritesList();
    }
}
