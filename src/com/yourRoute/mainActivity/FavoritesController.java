package com.yourRoute.mainActivity;

import android.content.Context;
import com.yourRoute.model.RoutesRepository;
import com.yourRoute.searchResultsActivity.RouteListAdapter;

public class FavoritesController {

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

//    public void refreshFavoritesList() {
//
//        final ArrayList<Route> routes = getAllFavoriteRoutes();
//        if (routes.isEmpty()) {
//            Log.d(LOG_TAG, "favorites array is empty");
//            TextView noResults = activity.getNoFavorites();
//            noResults.setVisibility(View.VISIBLE);
//            return;
//        }
//
//        ListView favoritesListView = this.activity.getFavoritesListView();
//        this.adapter = new RouteListAdapter(this.context, R.layout.route_list_item, routes);
//        favoritesListView.setAdapter(this.adapter);
//
//        favoritesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(context, RouteDetailsActivity.class);
//                int routeID = routes.get(position).getId();
//                intent.putExtra("routeID", routeID);
//                activity.startActivity(intent);
//            }
//        });
//    }

//    private ArrayList<Route> getAllFavoriteRoutes() {
//        Integer [] favoriteIds = Preferences.getAllFavoriteRouteIds();
//        ArrayList<Route> routesArray = new ArrayList<>();
//        if (favoriteIds.length == 0) {
//            return routesArray;
//        }
//
//        for (int favoriteId : favoriteIds) {
//            Route route = routesRepository.getRoute(favoriteId);
//            routesArray.add(route);
//        }
//        return routesArray;
//    }


}
