package com.yourRoute.searchResultsActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.yourRoute.R;
import com.yourRoute.YourRouteApp;
import com.yourRoute.model.Route;
import com.yourRoute.model.RoutesHolder;
import com.yourRoute.routeDetailsActivity.RouteDetailsActivity;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 7/15/13
 * Time: 9:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchResultsActivity extends Activity {

    private final String LOG_TAG = this.getClass().getSimpleName();
    ListView searchResultsListView;
    TextView noSearchResults;
    private RoutesHolder routesHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results_activity);
        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setTitle(R.string.search_results);

        findViews();
        initialize();

    }

    private void initialize() {

        this.routesHolder = YourRouteApp.getRoutesHolder();
        switch (getSearchMode()) {
            case 1:
                doSearchByStreetName();
                break;
            case 2:
                doSearchByRouteName();
        }

    }

    private void findViews() {
        this.searchResultsListView = (ListView) findViewById(R.id.search_results);
        this.noSearchResults = (TextView) findViewById(R.id.no_search_results);
    }

    private void refreshSearchResults(final ArrayList<Route> routes) {

        if (routes.isEmpty()) {
            Log.d(LOG_TAG, "routes array is empty");
            this.noSearchResults.setVisibility(View.VISIBLE);
            return;
        }

        RouteListAdapter adapter = new RouteListAdapter(this, R.layout.route_list_item, routes);
        this.searchResultsListView.setAdapter(adapter);

        this.searchResultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   mOnItemClick(routes, position);
            }
        });
    }

    private void mOnItemClick(ArrayList<Route> routes, int position) {
        Intent intent = new Intent(this, RouteDetailsActivity.class);
        int routeID = routes.get(position).getId();
        intent.putExtra("routeID", routeID);
        startActivity(intent);
    }


    private void doSearchByStreetName() {
        String searchKeyMain = getStopNameKey();
        String searchKeyOptional = getOptionalStopNameKey();
        Log.d(LOG_TAG, "Optional street search name: " + searchKeyOptional);
        int stopCityId = getCityId();
        final ArrayList<Route> routes = this.routesHolder.findRoutesByStopName(searchKeyMain, searchKeyOptional, stopCityId);
        refreshSearchResults(routes);
    }

    private void doSearchByRouteName() {
        String searchKey = getRouteNumberKey();
        int savedCityId = this.routesHolder.getSavedCityId();
        final ArrayList<Route> routes = this.routesHolder.findRoutesByRouteName(searchKey, savedCityId);
        refreshSearchResults(routes);
    }

    private String getStopNameKey() {
        String searchPhrase = getIntent().getStringExtra("StopName");
        return searchPhrase;
    }

    private String getOptionalStopNameKey() {
        String optionalSearchPhrase = getIntent().getStringExtra("OptionalStopName");
        return optionalSearchPhrase;
    }

    private String getRouteNumberKey() {
        String routeNumber = getIntent().getStringExtra("RouteNumber");
        return routeNumber;
    }

    private int getSearchMode() {

        int searchMode = getIntent().getIntExtra("SearchMode", 1);
        return searchMode;
    }

    private int getCityId() {

        int savedCityId = this.routesHolder.getSavedCityId();
        int searchMode = getIntent().getIntExtra("CityId", savedCityId);
        return searchMode;
    }

}
