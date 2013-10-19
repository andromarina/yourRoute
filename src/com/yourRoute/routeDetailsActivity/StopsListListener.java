package com.yourRoute.routeDetailsActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.yourRoute.YourRouteApp;
import com.yourRoute.model.Stop;
import com.yourRoute.searchResultsActivity.SearchResultsActivity;

import java.util.ArrayList;

public class StopsListListener implements ListView.OnItemClickListener {
    private Context context;
    private RouteDetailsActivity activity;
    private ArrayList<Stop> stops;

    public StopsListListener(RouteDetailsActivity activity, ArrayList<Stop> stops) {
        this.context = activity;
        this.activity = activity;
        this.stops = stops;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(context, SearchResultsActivity.class);
        Stop stop = stops.get(position);

        String searchKeyMain = stop.getName();
        intent.putExtra("StopName", searchKeyMain);
        YourRouteApp.saveSearchPhrase(searchKeyMain);

        int cityId = stop.getCityId();
        intent.putExtra("CityId", cityId);

        intent.putExtra("SearchMode", 1);
        activity.startActivity(intent);
    }
}
