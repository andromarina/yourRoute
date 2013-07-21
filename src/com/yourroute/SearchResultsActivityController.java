package com.yourroute;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.yourroute.model.Route;
import com.yourroute.model.RoutesRepository;

import java.util.ArrayList;

public class SearchResultsActivityController {
    SearchResultsActivity activity;
    Context context;
    RouteListAdapter adapter;
    RoutesRepository routesRepository;

    public SearchResultsActivityController(Context context, SearchResultsActivity activity, RoutesRepository routesRepository) {
        this.activity = activity;
        this.context = context;
        this.routesRepository = routesRepository;
    }

    public void initialize() {
        String searchPhrase = getSearchPhrase();
        doSearch(searchPhrase);
    }

    private void refreshSearchResults(final ArrayList<Route> routes) {

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

    private void doSearch(String queryStr) {
        int savedCityId = Preferences.getSavedCityId();
        final ArrayList<Route> routes = this.routesRepository.getRoutesByStopName(queryStr, savedCityId);
        refreshSearchResults(routes);
    }

    private String getSearchPhrase() {
        Intent intent = this.activity.getIntent();
        String searchPhrase = intent.getStringExtra("SearchPhrase");
        return searchPhrase;
    }

}
