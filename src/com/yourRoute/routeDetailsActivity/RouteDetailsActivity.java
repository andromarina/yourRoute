package com.yourRoute.routeDetailsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.yourRoute.R;
import com.yourRoute.YourRouteApp;
import com.yourRoute.model.*;
import com.yourRoute.utils.Report;
import maps.item.RouteLineGraphicItem;
import maps.item.StopsCollectionGraphicItem;
import maps.Map;
import maps.RouteOnMapActivity;
import android.app.FragmentManager;

import java.util.ArrayList;


public class RouteDetailsActivity extends FragmentActivity {
    private RoutesHolder routesHolder;
    private Favorites favorites;
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
    private TextView priceView;
    private ListView forwardStopsList;
    private ListView backwardStopsList;
    private MenuItem favoriteButton;
    private MenuItem mapButton;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.route_details_menu, menu);
        this.favoriteButton = menu.getItem(0);
        this.mapButton = menu.getItem(1);
        initializeFavoriteButton();
        initializeMapButton();
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
        this.favorites = YourRouteApp.getFavorites();
        initializeTabHost();
        initializeRoute();
        setCarTypeIcon();
        setStartEnd();
        setOperationHours();
        setInterval();
        setLength();
        setPrice();
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
        this.priceView = (TextView) findViewById(R.id.price);
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
        YourRouteApp.getSelections().saveSelectedRoute(this.route);
        ArrayList<Stop> stops = this.routesHolder.findStopsByRouteId(this.routeId);
        this.route.initializeStops(stops);
    }

    private String getRouteName() {

        String routeName = this.route.getName();
        return routeName;
    }

    private void initializeFavoriteButton() {
        MenuItem favoriteButton = this.favoriteButton;

        if (this.favorites.isFavorite(this.routeId)) {
            favoriteButton.setIcon(R.drawable.ic_star_filled_big);
        } else {
            favoriteButton.setIcon(R.drawable.ic_star_empty_big);
        }

        favoriteButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
               return onFavoriteClick(item);
            }
        });
    }

    public boolean onFavoriteClick(MenuItem item) {
        if (this.favorites.toggle(routeId)) {
            item.setIcon(R.drawable.ic_star_filled_big);
        } else {
            item.setIcon(R.drawable.ic_star_empty_big);
        }
        return true;
    }


    private void initializeMapButton() {
        MenuItem mapButton = this.mapButton;
        mapButton.setIcon(R.drawable.ic_action_location_map);
        mapButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return onMapClick();
            }
        });
    }

    public boolean onMapClick() {
        Map map = YourRouteApp.getMap();
        FragmentManager fragmentManager = getFragmentManager();
        if(map.prepareMap(this, fragmentManager)) {
            addGraphicItems(map);
            Intent intent = new Intent(this, RouteOnMapActivity.class);
            this.startActivity(intent);
        }
        return true;
    }

    private void addGraphicItems(Map map) {
        StopsCollectionGraphicItem graphicItemForward = new StopsCollectionGraphicItem(this.route.getForwardStops(), 0);
        map.getGraphicItems().add(graphicItemForward);

        StopsCollectionGraphicItem graphicItemBackward = new StopsCollectionGraphicItem(this.route.getBackwardStops(), 1);
        map.getGraphicItems().add(graphicItemBackward);

        RouteLineGraphicItem routeForwardLineGraphicItem = new RouteLineGraphicItem(this.route.getForwardRoutePoints(), 0);
        map.getGraphicItems().add(routeForwardLineGraphicItem);

        RouteLineGraphicItem routeBackwardLineGraphicItem = new RouteLineGraphicItem(this.route.getBackwardRoutePoints(), 1);
        map.getGraphicItems().add(routeBackwardLineGraphicItem);
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

    private void setPrice() {

       this.priceView.setText(this.route.getPrice().toString());

    }

    private void setStopsList() {

        final ArrayList<Stop> forwardStops = this.route.getForwardStops();
        Selections selections = YourRouteApp.getSelections();

        StopsListAdapter adapterForward = new StopsListAdapter(this, R.layout.stop_item, forwardStops, selections);
        this.forwardStopsList.setAdapter(adapterForward);
        StopsListListener forwardStopsListListener = new StopsListListener(this, forwardStops, selections);
        this.forwardStopsList.setOnItemClickListener(forwardStopsListListener);

        final ArrayList<Stop> backwardStops = this.route.getBackwardStops();
        StopsListListener backwardStopsListListener = new StopsListListener(this, backwardStops, selections);
        this.backwardStopsList.setOnItemClickListener(backwardStopsListListener);
        StopsListAdapter adapterBackward = new StopsListAdapter(this, R.layout.stop_item, backwardStops, selections);
        this.backwardStopsList.setAdapter(adapterBackward);
    }

}


