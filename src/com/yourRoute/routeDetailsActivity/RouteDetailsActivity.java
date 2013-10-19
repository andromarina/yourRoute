package com.yourRoute.routeDetailsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.yourRoute.Preferences;
import com.yourRoute.R;
import com.yourRoute.YourRouteApp;
import com.yourRoute.model.*;

import java.util.ArrayList;


public class RouteDetailsActivity extends FragmentActivity {
    private RoutesHolder routesHolder;
    private TabHost direction_tabhost;
    private int routeId;
    private Route route;
    private TextView startEndView;
    private ImageView carTypeIcon;
    private TextView operationHoursView;
    private LinearLayout operationHoursLayout;
    private TextView intervalView;
    private LinearLayout intervalLayout;
    private TextView lengthView;
    private LinearLayout lengthLayout;
    private ListView forwardStopsList;
    private ListView backwardStopsList;
    private MenuItem favoriteButton;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.route_details_menu, menu);
        this.favoriteButton = menu.getItem(0);
        initializeFavoriteButton();
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_card);
        findViews();
        initialize();
        configureActionBar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.report:
                Report report = new Report(this);
                report.sendReport();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void configureActionBar() {

        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle(getRouteName());
    }

    private void initialize() {
        this.routesHolder = YourRouteApp.getRoutesHolder();
        initializeTabHost();
        initializeRoute();
        setCarTypeIcon();
        setStartEnd();
        setOperationHours();
        setInterval();
        setLength();
        setStopsList();
    }

    private void findViews() {
        this.direction_tabhost = (TabHost) findViewById(R.id.direction_tabhost);
        this.startEndView = (TextView) findViewById(R.id.start_end);
        this.carTypeIcon = (ImageView) findViewById(R.id.ic_car_type);
        this.operationHoursView = (TextView) findViewById(R.id.operation_time);
        this.operationHoursLayout = (LinearLayout) findViewById(R.id.operation_time_layout);
        this.intervalView = (TextView) findViewById(R.id.interval);
        this.intervalLayout = (LinearLayout) findViewById(R.id.interval_layout);
        this.lengthView = (TextView) findViewById(R.id.length);
        this.lengthLayout = (LinearLayout) findViewById(R.id.length_layout);
        this.forwardStopsList = (ListView) findViewById(R.id.forward_stops_list);
        this.backwardStopsList = (ListView) findViewById(R.id.backward_stops_list);
    }

    private void initializeTabHost() {

        this.direction_tabhost.setup();

        //Forward tab
        String forward = getResources().getString(R.string.forward);
        TabHost.TabSpec tspec = this.direction_tabhost.newTabSpec("ForwardTab");
        tspec.setIndicator(forward);
        tspec.setContent(R.id.forward_stops_list);
        this.direction_tabhost.addTab(tspec);

        //Backward tab
        String backward = getResources().getString(R.string.backward);
        tspec = this.direction_tabhost.newTabSpec("BackwardTab");
        tspec.setIndicator(backward);
        tspec.setContent(R.id.backward_stops_list);
        this.direction_tabhost.addTab(tspec);

        TabWidget tabs = this.direction_tabhost.getTabWidget();

        for (int i = 0; i < tabs.getChildCount(); i++) {
            View tab = tabs.getChildAt(i);
            TextView tv = (TextView) tab.findViewById(android.R.id.title);
            tv.setTextColor(getResources().getColorStateList(R.color.tab_text));
        }
    }

    private void initializeRoute() {
        Intent intent = getIntent();
        this.routeId = intent.getIntExtra("routeID", 1);
        this.route = this.routesHolder.findRouteById(this.routeId);
        ArrayList<Stop> stops = this.routesHolder.findStopsByRouteId(this.routeId);
        this.route.initializeStops(stops);
    }

    private String getRouteName() {

        String routeName = this.route.getName();
        return routeName;
    }

    private void initializeFavoriteButton() {
        MenuItem favoriteButton = this.favoriteButton;

        if (Preferences.isRouteIdPresentInPreferences(this.routeId)) {
            favoriteButton.setIcon(R.drawable.ic_star_filled_big);
        }

        favoriteButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) { return onRouteDetailsMenuItemClick(item); }
        });
    }

    private boolean onRouteDetailsMenuItemClick(MenuItem item) {
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

    private void setCarTypeIcon() {

        int carTypeId = this.route.getCarType();
        int resourceId = this.route.getIconResource(carTypeId);
        this.carTypeIcon.setImageResource(resourceId);
    }

    private void setStartEnd() {

        String startEnd = this.route.getStartEnd();
        this.startEndView.setText(startEnd);
    }

    private void setOperationHours() {

        if (this.route.getWorkTime().isEmpty()) {
            this.operationHoursLayout.setVisibility(View.GONE);
        } else {
            this.operationHoursView.setText(route.getWorkTime());
        }
    }

    private void setInterval() {

        String interval = this.route.getInterval();
        if (interval.isEmpty()) {
            this.intervalLayout.setVisibility(View.GONE);
        } else {
            this.intervalView.setText(interval);
        }
    }

    private void setLength() {

        if (route.getLength().isEmpty()) {
            this.lengthLayout.setVisibility(View.GONE);
        } else {
            this.lengthView.setText(this.route.getLength());
        }
    }

    private void setStopsList() {

        final ArrayList<Stop> forwardStops = this.route.getForwardStops();

        StopsListAdapter adapterForward = new StopsListAdapter(this, R.layout.stop_item, forwardStops);
        this.forwardStopsList.setAdapter(adapterForward);
        StopsListListener forwardStopsListListener = new StopsListListener(this, forwardStops);
        this.forwardStopsList.setOnItemClickListener(forwardStopsListListener);

        final ArrayList<Stop> backwardStops = this.route.getBackwardStops();
        StopsListListener backwardStopsListListener = new StopsListListener(this, backwardStops);
        this.backwardStopsList.setOnItemClickListener(backwardStopsListListener);
        StopsListAdapter adapterBackward = new StopsListAdapter(this, R.layout.stop_item, backwardStops);
        this.backwardStopsList.setAdapter(adapterBackward);
    }
}


