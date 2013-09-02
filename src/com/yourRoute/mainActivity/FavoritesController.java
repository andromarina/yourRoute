package com.yourRoute.mainActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.yourRoute.R;
import com.yourRoute.model.Route;
import com.yourRoute.routeDetailsActivity.RouteDetailsActivity;
import com.yourRoute.searchResultsActivity.RouteListAdapter;

import java.util.ArrayList;

public class FavoritesController {

    private final String LOG_TAG = this.getClass().getSimpleName();
    private MainActivity activity;
    private Context context;
    private RouteListAdapter adapter;

    public FavoritesController(Context context, MainActivity activity) {
        this.activity = activity;
        this.context = context;
    }

    private void refreshFavoritesList(final ArrayList<Route> routes) {

        if (routes.isEmpty()) {
            Log.d(LOG_TAG, "favorites array is empty");
            TextView noResults = activity.getNoFavorites();
            noResults.setVisibility(View.VISIBLE);
            return;
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
}
