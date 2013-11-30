package com.yourRoute.mainActivity;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.yourRoute.R;
import com.yourRoute.YourRouteApp;
import com.yourRoute.mainActivity.IFavoritesChangedListener;
import com.yourRoute.mainActivity.FavoritesListAdapter;
import com.yourRoute.model.Favorites;
import com.yourRoute.model.Route;
import com.yourRoute.model.RoutesHolder;
import com.yourRoute.routeDetailsActivity.RouteDetailsActivity;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 10/20/13
 * Time: 5:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class FavoritesControl extends RelativeLayout implements IFavoritesChangedListener {
    private Context context;
    private final String LOG_TAG = this.getClass().getSimpleName();
    private Favorites favorites;
    private RoutesHolder routesHolder;
    private FavoritesListAdapter adapter;
    private RelativeLayout layout;

    public FavoritesControl(Context context, AttributeSet attr){
        super(context, attr);
        Log.d(LOG_TAG, "enter constructor");
        this.context = context;
        this.favorites = YourRouteApp.getFavorites();
        this.routesHolder = YourRouteApp.getRoutesHolder();
        YourRouteApp.getFavorites().setListener(this);
        Log.d(LOG_TAG, "setled favorites listener");
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = (RelativeLayout) inflater.inflate(R.layout.favorites_tab, this);
        Log.d(LOG_TAG, "inflated");
        refreshFavoritesList();

    }


    private void refreshFavoritesList() {

        final ArrayList<Route> routes = this.favorites.getAllFavoriteRoutes();
        Log.d(LOG_TAG, "Refresh favorites: routes array size: " + routes.size());
        TextView noResults = (TextView) layout.findViewById(R.id.no_favorites);

        if (routes.isEmpty()) {
            Log.d(LOG_TAG, "favorites array is empty");
            noResults.setVisibility(View.VISIBLE);
        } else {
            noResults.setVisibility(View.GONE);
        }

        ListView favoritesListView = (ListView) layout.findViewById(R.id.favorites_list);
        this.adapter = new FavoritesListAdapter(this.context, R.layout.route_list_item, routes, this.routesHolder);

        favoritesListView.setAdapter(this.adapter);

        favoritesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 onRouteClickListener(position, routes);
            }
        });
    }


    @Override
    public void onFavoritesChanged() {
        refreshFavoritesList();
    }

    private void onRouteClickListener(int position, ArrayList<Route> routes) {
        Intent intent = new Intent(context, RouteDetailsActivity.class);
        int routeID = routes.get(position).getId();
        intent.putExtra("routeID", routeID);
        context.startActivity(intent);
    }
}
