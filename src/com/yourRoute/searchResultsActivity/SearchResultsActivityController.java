package com.yourRoute.searchResultsActivity;

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

import java.util.ArrayList;

public class SearchResultsActivityController {

    private final String LOG_TAG = this.getClass().getSimpleName();
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

        switch (getSearchMode()) {
            case 1:
                doSearchByStreetName();
                break;
            case 2:
                doSearchByRouteName();
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

    private void doSearchByStreetName() {
        String searchKeyMain = getStopNameKey();
        String searchKeyOptional = getOptionalStopNameKey();
        int stopCityId = getCityId();
        final ArrayList<Route> routes = this.routesRepository.getRoutesByStopName(searchKeyMain, stopCityId);
        if (searchKeyOptional != null && !searchKeyOptional.isEmpty()) {
            Log.d(LOG_TAG, "Optional street search name: " + searchKeyOptional);
            final ArrayList<Route> routesOptional = this.routesRepository.getRoutesByStopName(searchKeyOptional, stopCityId);
            ArrayList<Route> unitedArray = this.routesRepository.uniteRoutes(routes, routesOptional);
            refreshUnitedSearchResults(unitedArray);
        } else {
            refreshSearchResults(routes);
        }
    }

    private void doSearchByRouteName() {
        String searchKey = getRouteNumberKey();
        int savedCityId = Preferences.getSavedCityId();
        final ArrayList<Route> routes = this.routesRepository.getRoutesByRouteName(searchKey, savedCityId);
        refreshSearchResults(routes);
    }

    private String getStopNameKey() {
        Intent intent = this.activity.getIntent();
        String searchPhrase = intent.getStringExtra("StopName");
        return searchPhrase;
    }

    private String getOptionalStopNameKey() {
        Intent intent = this.activity.getIntent();
        String optionalSearchPhrase = intent.getStringExtra("OptionalStopName");
        return optionalSearchPhrase;
    }

    private String getRouteNumberKey() {
        Intent intent = this.activity.getIntent();
        String routeNumber = intent.getStringExtra("RouteNumber");
        return routeNumber;
    }

    private int getSearchMode() {
        Intent intent = this.activity.getIntent();
        int searchMode = intent.getIntExtra("SearchMode", 1);
        return searchMode;
    }

    private int getCityId() {
        Intent intent = this.activity.getIntent();
        int savedCityId = Preferences.getSavedCityId();
        int searchMode = intent.getIntExtra("CityId", savedCityId);
        return searchMode;
    }

}
