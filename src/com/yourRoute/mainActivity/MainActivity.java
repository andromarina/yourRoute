package com.yourRoute.mainActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import com.yourRoute.R;
import com.yourRoute.controls.CustomSearchField;
import com.yourRoute.model.CitiesRepository;

public class MainActivity extends FragmentActivity {

    private Button cityNameButton;
    private TabHost mainTabhost;
    private CustomSearchField stopSearchMain;
    private CustomSearchField stopSearchOptional;
    private CustomSearchField routeNumberSearch;
    private Button stopNameSearchButton;
    private Button routeNumberSearchButton;
    private MainActivityController mainActivityController;
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
        if (savedInstanceState != null) {
            int currentTab = savedInstanceState.getInt("CurrentTab", 0);
            mainTabhost.setCurrentTab(currentTab);
        }

        configureActionBar();
        configureSearch();

        this.cityNameButton = (Button) findViewById(R.id.city_name_button);
        this.mainTabhost = (TabHost) findViewById(R.id.main_tabhost);
        this.noFavorites = (TextView) findViewById(R.id.no_favorites);
        this.favoritesListView = (ListView) findViewById(R.id.favorites_list);

        CitiesRepository citiesRepository = new CitiesRepository(getContentResolver());
        this.mainActivityController = new MainActivityController(this, this, citiesRepository);
        this.mainActivityController.initialize();

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

    public Button getCityNameButton() {
        return this.cityNameButton;
    }

    public CustomSearchField getStopSearchMain() {
        return this.stopSearchMain;
    }

    public CustomSearchField getStopSearchOptional() {
        return this.stopSearchOptional;
    }

    public Button getStopNameSearchButton() {
        return this.stopNameSearchButton;
    }

    public TabHost getMainTabhost() {
        return this.mainTabhost;
    }

    public CustomSearchField getRouteNumberSearch() {
        return routeNumberSearch;
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
}
