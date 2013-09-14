package com.yourRoute.routeDetailsActivity;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.yourRoute.Preferences;
import com.yourRoute.R;
import com.yourRoute.model.Route;
import com.yourRoute.model.RoutesRepository;
import com.yourRoute.model.Stop;
import com.yourRoute.model.StopsRepository;

import java.util.ArrayList;


public class RouteDetailsActivityController {
    private RouteDetailsActivity activity;
    private RoutesRepository routesRepository;
    private StopsRepository stopsRepository;
    private Route route;
    private int routeId;

    public RouteDetailsActivityController(RouteDetailsActivity activity, RoutesRepository routesRepository,
                                          StopsRepository stopsRepository) {
        this.activity = activity;
        this.routesRepository = routesRepository;
        this.stopsRepository = stopsRepository;
    }

    public void initialize() {
        initializeTabHost();
        initializeRoute();
        setCarTypeIcon();
        setStartEnd();
        setOperationHours();
        setInterval();
        setLength();
        setStopsList();
    }

    private void initializeTabHost() {

        TabHost direction_tabhost = activity.getDirection_tabhost();
        direction_tabhost.setup();

        //Forward tab
        String forward = activity.getResources().getString(R.string.forward);
        TabHost.TabSpec tspec = direction_tabhost.newTabSpec("ForwardTab");
        tspec.setIndicator(forward);
        tspec.setContent(R.id.forward_stops_list);
        direction_tabhost.addTab(tspec);

        //Backward tab
        String backward = activity.getResources().getString(R.string.backward);
        tspec = direction_tabhost.newTabSpec("BackwardTab");
        tspec.setIndicator(backward);
        tspec.setContent(R.id.backward_stops_list);
        direction_tabhost.addTab(tspec);

        TabWidget tabs = direction_tabhost.getTabWidget();

        for (int i = 0; i < tabs.getChildCount(); i++) {
            View tab = tabs.getChildAt(i);
            TextView tv = (TextView) tab.findViewById(android.R.id.title);
            tv.setTextColor(activity.getResources().getColorStateList(R.color.tab_text));
        }
    }

    private void initializeRoute() {
        Intent intent = this.activity.getIntent();
        this.routeId = intent.getIntExtra("routeID", 1);
        this.route = this.routesRepository.getRoute(this.routeId);
        ArrayList<Stop> stops = this.stopsRepository.getStopsByRouteId(this.routeId);
        this.route.initializeStops(stops);
    }

    public String getRouteName() {

        String routeName = this.route.getName();
        return routeName;
    }

    public void initializeFavoriteButton() {
        MenuItem favoriteButton = activity.getFavoriteButton();

        if (Preferences.isRouteIdPresentInPreferences(this.routeId)) {
            favoriteButton.setIcon(R.drawable.ic_star_filled_big);
        }

        favoriteButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (Preferences.isRouteIdPresentInPreferences(routeId)) {
                    Preferences.deleteFavoriteRouteId(routeId);
                    item.setIcon(R.drawable.ic_star_empty_big);
                    return true;
                } else {
                    Preferences.saveFavoriteRouteId(routeId);
                    item.setIcon(R.drawable.ic_star_filled_big);
                    return true;
                }
            }
        });
    }

    private void setCarTypeIcon() {
        ImageView carType = activity.getCarTypeIcon();
        int carTypeId = this.route.getCarType();
        int resourceId = this.route.getIconResource(carTypeId);
        carType.setImageResource(resourceId);

    }

    private void setStartEnd() {
        TextView startEndView = this.activity.getStartEndView();
        String startEnd = this.route.getStartEnd();
        startEndView.setText(startEnd);
    }

    private void setOperationHours() {

        TextView operationHoursView = this.activity.getOperationHoursView();
        LinearLayout operationalHoursLayout = this.activity.getOperationHoursLayout();
        if (this.route.getWorkTime().isEmpty()) {
            operationalHoursLayout.setVisibility(View.GONE);
        } else {
            operationHoursView.setText(route.getWorkTime());
        }
    }

    private void setInterval() {
        TextView intervalView = this.activity.getIntervalView();
        LinearLayout intervalLayout = this.activity.getIntervalLayout();
        String interval = this.route.getInterval();
        if (interval.isEmpty()) {
            intervalLayout.setVisibility(View.GONE);
        } else {
            intervalView.setText(interval);
        }
    }

    private void setLength() {
        TextView lengthView = this.activity.getLengthView();
        LinearLayout lengthLayout = this.activity.getLengthLayout();

        if (route.getLength().isEmpty()) {
            lengthLayout.setVisibility(View.GONE);
        } else {
            lengthView.setText(this.route.getLength());
        }
    }

    private void setStopsList() {

        final ArrayList<Stop> forwardStops = this.route.getForwardStops();
        ListView forwardStopsList = this.activity.getForwardStopsList();
        StopsListAdapter adapterForward = new StopsListAdapter(activity.getBaseContext(), R.layout.stop_item, forwardStops);
        forwardStopsList.setAdapter(adapterForward);
        StopsListListener forwardStopsListListener = new StopsListListener(activity.getBaseContext(), activity, forwardStops);
        forwardStopsList.setOnItemClickListener(forwardStopsListListener);

        final ArrayList<Stop> backwardStops = this.route.getBackwardStops();
        ListView backwardStopsList = this.activity.getBackwardStopsList();
        StopsListListener backwardStopsListListener = new StopsListListener(activity.getBaseContext(), activity, backwardStops);
        backwardStopsList.setOnItemClickListener(backwardStopsListListener);
        StopsListAdapter adapterBackward = new StopsListAdapter(activity.getBaseContext(), R.layout.stop_item, backwardStops);
        backwardStopsList.setAdapter(adapterBackward);
    }

}
