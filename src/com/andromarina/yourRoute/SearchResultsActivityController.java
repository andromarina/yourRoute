package com.andromarina.yourRoute;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.andromarina.R;
import com.andromarina.yourRoute.model.Route;
import com.andromarina.yourRoute.model.RoutesRepository;

import java.util.ArrayList;

public class SearchResultsActivityController {

    private final String LOG_TAG = "SearchResultsActivityController";
    private SearchResultsActivity activity;
    private Context context;
    private RouteListAdapter adapter;
    private RoutesRepository routesRepository;

    public SearchResultsActivityController(Context context, SearchResultsActivity activity, RoutesRepository routesRepository) {
        this.activity = activity;
        this.context = context;
        this.routesRepository = routesRepository;
    }

    public void initialize() {
        String searchPhrase = getSearchPhrase();
        String optionalSearchPhrase = getOptionalSearchPhrase();
        switch (getSearchMode()) {
            case 1:
                doSearchByStreetName(searchPhrase, optionalSearchPhrase);
                break;
            case 2:
                doSearchByRouteName(searchPhrase);
        }

    }

    private void refreshSearchResults(final ArrayList<Route> routes) {

        if (routes.isEmpty()) {
            Log.d(LOG_TAG, "routes array is empty");
            TextView noResults = activity.getNoSearchResultsTextView();
            noResults.setVisibility(View.VISIBLE);
            return;
        }
        ListView searchResultsListView = this.activity.getSearchResultsListView();
        this.adapter = new RouteListAdapter(this.context, R.layout.route_list_item, routes);
        searchResultsListView.setAdapter(this.adapter);

        searchResultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, RouteDetailsActivity.class);
                int routeID = routes.get(position).getId();
                intent.putExtra("routeID", routeID);
                activity.startActivity(intent);
            }
        });
    }

    private void refreshUnitedSearchResults(final ArrayList<Route> unitedArray) {

        if (unitedArray.isEmpty()) {
            Log.d(LOG_TAG, "united routes array is empty");
            TextView noResults = activity.getNoSearchResultsTextView();
            noResults.setVisibility(View.VISIBLE);
            return;
        }
        ListView searchResultsListView = this.activity.getSearchResultsListView();
        this.adapter = new RouteListAdapter(this.context, R.layout.route_list_item, unitedArray);
        searchResultsListView.setAdapter(this.adapter);

        searchResultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, RouteDetailsActivity.class);
                int routeID = unitedArray.get(position).getId();
                intent.putExtra("routeID", routeID);
                activity.startActivity(intent);
            }
        });
    }

    private void doSearchByStreetName(String stopName, String optionalName) {
        int savedCityId = Preferences.getSavedCityId();
        final ArrayList<Route> routes = this.routesRepository.getRoutesByStopName(stopName, savedCityId);
        if (!optionalName.isEmpty()) {
            Log.d(LOG_TAG, "Optional street search name: " + optionalName);
            final ArrayList<Route> routesOptional = this.routesRepository.getRoutesByStopName(optionalName, savedCityId);
            ArrayList<Route> unitedArray = this.routesRepository.uniteRoutes(routes, routesOptional);
            refreshUnitedSearchResults(unitedArray);
        } else {
            refreshSearchResults(routes);
        }
    }

    private void doSearchByRouteName(String queryStr) {
        int savedCityId = Preferences.getSavedCityId();
        final ArrayList<Route> routes = this.routesRepository.getRoutesByRouteName(queryStr, savedCityId);
        refreshSearchResults(routes);
    }

    private String getSearchPhrase() {
        Intent intent = this.activity.getIntent();
        String searchPhrase = intent.getStringExtra("SearchPhrase");
        return searchPhrase;
    }

    private String getOptionalSearchPhrase() {
        Intent intent = this.activity.getIntent();
        String optionalSearchPhrase = intent.getStringExtra("OptionalSearchPhrase");
        return optionalSearchPhrase;
    }

    private int getSearchMode() {
        Intent intent = this.activity.getIntent();
        int searchMode = intent.getIntExtra("SearchMode", 1);
        return searchMode;
    }

}
