package com.yourRoute.mainActivity;

import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import com.yourRoute.R;
import com.yourRoute.YourRouteApp;
import com.yourRoute.controls.CustomSearchField;
import com.yourRoute.model.City;
import com.yourRoute.model.RoutesHolder;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private final String LOG_TAG = this.getClass().getSimpleName();
    private Button cityNameButton;
    private TabHost mainTabhost;
    private RoutesHolder routesHolder;
    private SearchController searchController;
    private ArrayList<City> cities;
    private CustomSearchField stopSearchMain;
    private TabHost.TabSpec tspec;
    private CustomSearchField stopSearchOptional;
    private CustomSearchField routeNumberSearch;
    private Button stopNameSearchButton;
    private Button routeNumberSearchButton;
    private TextView noFavorites;
    private ListView favoritesListView;


    @Override
    public void onSaveInstanceState(Bundle outState) {
        int currentTabIndex = mainTabhost.getCurrentTab();
        outState.putInt("CurrentTab", currentTabIndex);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        if (savedInstanceState != null && mainTabhost != null) {
            int currentTab = savedInstanceState.getInt("CurrentTab", 0);
            mainTabhost.setCurrentTab(currentTab);
        }

        configureActionBar();
        configureSearch();
        findViews();
        initialize();

    }

    @SuppressWarnings("deprecation")
    private void configureActionBar() {
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setCustomView(R.layout.city_name_button);
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
    }

    private void configureSearch() {

        this.stopSearchMain = (CustomSearchField) findViewById(R.id.stop_search_field);
        this.stopSearchOptional = (CustomSearchField) findViewById(R.id.search_by_stop_optional);
        this.stopNameSearchButton = (Button) findViewById(R.id.stop_name_search_button);
        this.routeNumberSearch = (CustomSearchField) findViewById(R.id.route_number_search_field);
        this.routeNumberSearchButton = (Button) findViewById(R.id.route_number_search_button);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    private void initialize() {

        this.routesHolder = YourRouteApp.getRoutesHolder();
        initializeTabHost();
        this.searchController = new SearchController(this);
        searchController.initialize();
        int savedCityId = this.routesHolder.getSavedCityId();
        initializeCityNameButton(savedCityId);
        FavoritesController favoritesController = new FavoritesController(this);
    }

    private void findViews() {

        this.cityNameButton = (Button) findViewById(R.id.city_name_button);
        this.mainTabhost = (TabHost) findViewById(R.id.main_tabhost);
        this.noFavorites = (TextView) findViewById(R.id.no_favorites);
        this.favoritesListView = (ListView) findViewById(R.id.favorites_list);
    }

    private void showCityChoiceDialog() {

        FragmentManager fm = getSupportFragmentManager();
        this.cities = this.routesHolder.getAllCities();

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onCityChanged(which);
                dialog.dismiss();
            }
        };
        CitiesChoiceDialog cityChoiceDialog = new CitiesChoiceDialog(this, this.cities, listener);
        cityChoiceDialog.show(fm, "CityChoiceDialog");
    }

    private void onCityChanged(int which) {

        Log.i(LOG_TAG, this.cities.get(which).getName() + " was selected");
        String name = this.cities.get(which).getName();
        Log.i(LOG_TAG, "chosen city " + name);
        this.cityNameButton.setText(name);
        int cityId = this.cities.get(which).getId();
        this.routesHolder.saveCityId(cityId);
        this.searchController.clearAllSearchFields();
    }

    private void initializeCityNameButton(int savedCityId) {

        String savedCityName = routesHolder.findCityById(savedCityId).getName();
        this.cityNameButton.setText(savedCityName);
        View.OnClickListener oclCityNameBtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCityChoiceDialog();
            }
        };
        cityNameButton.setOnClickListener(oclCityNameBtn);
    }

    private void initializeTabHost() {

        this.mainTabhost.setup();
        Resources res = getResources();

        //Tab 1
        this.tspec = this.mainTabhost.newTabSpec("Tab 1");
        tspec.setIndicator("", res.getDrawable(R.drawable.ic_directions_light));
        tspec.setContent(R.id.stop_name_search);
        this.mainTabhost.addTab(tspec);

        //Tab 2
        this.tspec = this.mainTabhost.newTabSpec("Tab 2");
        tspec.setIndicator("", res.getDrawable(R.drawable.ic_number_light));
        tspec.setContent(R.id.route_number_search);
        this.mainTabhost.addTab(tspec);

        //Tab 3
        this.tspec = this.mainTabhost.newTabSpec("Tab 3");
        tspec.setIndicator("", res.getDrawable(R.drawable.ic_favorites_light));
        tspec.setContent(R.id.favorites);
        this.mainTabhost.addTab(tspec);

        this.mainTabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                  mOnTabChanged(tabId);
            }
        });

    }

    private void mOnTabChanged(String tabId) {
        if (tabId.equals("Tab 3")) {
            stopSearchMain.clearFocus();
            hideKeyboard();
        }
    }

    private void hideKeyboard() {

        InputMethodManager imm = (InputMethodManager)  getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.stopSearchMain.getWindowToken(), 0);
    }


    public CustomSearchField getStopSearchOptional() {
        return stopSearchOptional;
    }

    public CustomSearchField getRouteNumberSearch() {
        return routeNumberSearch;
    }

    public Button getStopNameSearchButton() {
        return stopNameSearchButton;
    }

    public Button getRouteNumberSearchButton() {
        return routeNumberSearchButton;
    }

    public TextView getNoFavorites() {
        return noFavorites;
    }

    public ListView getFavoritesListView() {
        return favoritesListView;
    }

    public CustomSearchField getStopSearchMain() {
        return stopSearchMain;
    }
}
