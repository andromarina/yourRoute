package com.yourroute;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.yourroute.model.CitiesRepository;
import com.yourroute.model.City;
import com.yourroute.model.Route;
import com.yourroute.model.RoutesRepository;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/26/13
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainActivityController {

    private final String LOG_TAG = "MainActivityController";
    private Context context;
    private MainActivity activity;
    private CitiesRepository citiesRepository;
    private RoutesRepository routesRepository;
    private ArrayList<City> cities;
    RouteListAdapter adapter;

    public MainActivityController(Context context, MainActivity activity, CitiesRepository citiesRepository, RoutesRepository routesRepository) {
        this.context = context;
        this.activity = activity;
        this.citiesRepository = citiesRepository;
        this.routesRepository = routesRepository;
    }

    public void initialize() {

        initializeSearchManager();
        Preferences.initialize(this.context, this.activity);
        int savedCityId = Preferences.getSavedCityId();

        initializeCityNameButton(savedCityId);
        final ArrayList<Route> routes = this.routesRepository.getRoutesByCityID(savedCityId);
        refreshRouteListView(routes);
    }

    public void restoreActions() {
        Editable savedFilterValue = this.activity.getRouteFilterEdit().getEditableText();
        this.adapter.getFilter().filter(savedFilterValue);
        handleIntent(this.activity.getIntent());
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

        Log.i("Test", this.cities.get(which).getName() + " was selected");
        String name = this.cities.get(which).getName();
        Log.i("Test", "chosen city " + name);
        activity.getCityNameButton().setText(name);
        int cityId = this.cities.get(which).getId();
        Preferences.saveCityId(cityId);
        final ArrayList<Route> routes = this.routesRepository.getRoutesByCityID(cityId);
        this.refreshRouteListView(routes);
        this.activity.getRouteFilterEdit().setText("");
        this.activity.getSearchText1().setText("");
    }

    private void refreshRouteListView(final ArrayList<Route> routes) {

        ListView listViewMain = this.activity.getRouteListView();
        this.adapter = new RouteListAdapter(this.context, R.layout.route_list_item, routes);
        RouteTextWatcher routeTextWatcher = new RouteTextWatcher(this.adapter);
        this.activity.getRouteFilterEdit().addTextChangedListener(routeTextWatcher);
        listViewMain.setAdapter(this.adapter);

        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, RouteDetailsActivity.class);
                int routeID = routes.get(position).getId();
                intent.putExtra("routeID", routeID);
                activity.startActivity(intent);
            }
        });
    }

    public void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doSearch(query);
        } else if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            this.doView(intent);
        } else {
            Log.i(LOG_TAG, "Create intent NOT from search");
        }

    }

    private void doSearch(String queryStr) {
        int savedCityId = Preferences.getSavedCityId();
        final ArrayList<Route> routes = this.routesRepository.getRoutesByStopName(queryStr, savedCityId);
        refreshRouteListView(routes);
    }

    private void doView(final Intent queryIntent) {
        Uri uri = queryIntent.getData();
        String action = queryIntent.getAction();
        Intent intent = new Intent(action);
        intent.setData(uri);
        this.activity.startActivity(intent);
        this.activity.finish();
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

    private void initializeSearchManager() {
        SearchManager searchManager = (SearchManager) this.activity.getSystemService(Context.SEARCH_SERVICE);
        this.activity.getSearchView1().setSearchableInfo(searchManager.getSearchableInfo(this.activity.getComponentName()));
    }

}
