package com.yourRoute;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import com.yourRoute.controls.CustomSearchField;
import com.yourRoute.model.CitiesRepository;
import com.yourRoute.model.City;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/26/13
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainActivityController {

    private final String LOG_TAG = this.getClass().getSimpleName();
    private Context context;
    private MainActivity activity;
    private CitiesRepository citiesRepository;
    private CustomSearchField stopSearchMain;
    private CustomSearchField stopSearchOptional;
    private CustomSearchField routeNumbersSearch;
    private ArrayList<City> cities;

    public MainActivityController(Context context, MainActivity activity, CitiesRepository citiesRepository) {
        this.context = context;
        this.activity = activity;
        this.citiesRepository = citiesRepository;
    }

    public void initialize() {

        initializeMainActivityFragment();
        initializeSearchByStopName();
        initializeRouteNumbersSearch();
        int savedCityId = Preferences.getSavedCityId();
        initializeCityNameButton(savedCityId);
    }

    private void showCityChoiceDialog() {

        FragmentManager fm = this.activity.getSupportFragmentManager();
        this.cities = this.citiesRepository.getCities();

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onCityChanged(which);
                dialog.dismiss();
            }
        };
        CitiesChoiceDialog cityChoiceDialog = new CitiesChoiceDialog(this.context, this.cities, listener);
        cityChoiceDialog.show(fm, "CityChoiceDialog");
    }

    private void onCityChanged(int which) {

        Log.i(LOG_TAG, this.cities.get(which).getName() + " was selected");
        String name = this.cities.get(which).getName();
        Log.i(LOG_TAG, "chosen city " + name);
        activity.getCityNameButton().setText(name);
        int cityId = this.cities.get(which).getId();
        Preferences.saveCityId(cityId);
        stopSearchMain.getAutoCompleteTextView().setText("");
        stopSearchOptional.getAutoCompleteTextView().setText("");
        routeNumbersSearch.getAutoCompleteTextView().setText("");
    }

    private void initializeCityNameButton(int savedCityId) {

        String savedCityName = this.citiesRepository.getCity(savedCityId).getName();

        Button cityNameButton = this.activity.getCityNameButton();
        cityNameButton.setText(savedCityName);
        View.OnClickListener oclCityNameBtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCityChoiceDialog();
            }
        };
        cityNameButton.setOnClickListener(oclCityNameBtn);
    }

    private void initializeSearchByStopName() {

        StopSuggestionsProvider stopSuggestionsProvider = new StopSuggestionsProvider();
        this.stopSearchMain = this.activity.getStopSearchMain();
        this.stopSearchMain.initialize(stopSuggestionsProvider);
        this.stopSearchOptional = this.activity.getStopSearchOptional();
        this.stopSearchOptional.initialize(stopSuggestionsProvider);
        Button stopNameSearchButton = this.activity.getStopNameSearchButton();
        StopSearchButtonListener searchButtonListener = new StopSearchButtonListener(activity, context);
        stopNameSearchButton.setOnClickListener(searchButtonListener);
    }

    private void initializeRouteNumbersSearch() {

        RouteNumberSuggestionsProvider routeNumberSuggestionsProvider = new RouteNumberSuggestionsProvider();
        this.routeNumbersSearch = this.activity.getRouteNumberSearch();
        this.routeNumbersSearch.initialize(routeNumberSuggestionsProvider);
        Button routeNumberSearchButton = this.activity.getRouteNumberSearchButton();
        RouteNumberSearchButtonListener listener = new RouteNumberSearchButtonListener(activity, context);
        routeNumberSearchButton.setOnClickListener(listener);
    }

    private void initializeMainActivityFragment() {
        FragmentManager fm = this.activity.getSupportFragmentManager();
        MainActivityFragment fragment = new MainActivityFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(fragment, "MainActivityFragment");
        initializeTabHost();
        ft.commit();
    }

    private void initializeTabHost() {

        TabHost main_tabhost = activity.getMainTabhost();
        main_tabhost.setup();
        String stops = activity.getResources().getString(R.string.stop_name);
        String route_number = activity.getResources().getString(R.string.route_number);
        String favorites = activity.getResources().getString(R.string.favorites);
        main_tabhost.addTab(main_tabhost.newTabSpec("StopsSearchTab").setIndicator(stops).setContent(R.id.stops_search_panel));
        main_tabhost.addTab(main_tabhost.newTabSpec("RoutesSearch").setIndicator(route_number).setContent(R.id.route_number_search));
        main_tabhost.addTab(main_tabhost.newTabSpec("Favorites").setIndicator(favorites).setContent(R.id.favorites_list));
    }

}
