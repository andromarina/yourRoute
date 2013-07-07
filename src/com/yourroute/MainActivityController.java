package com.yourroute;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.yourroute.model.*;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/26/13
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainActivityController implements SearchView.OnQueryTextListener, SearchView.OnSuggestionListener {

    private final String LOG_TAG = "MainActivityController";
    private Context context;
    private MainActivity activity;
    private CitiesRepository citiesRepository;
    private RoutesRepository routesRepository;
    private StopsRepository stopsRepository;
    private ArrayList<City> cities;
    RouteListAdapter adapter;

    public MainActivityController(Context context, MainActivity activity, CitiesRepository citiesRepository, RoutesRepository routesRepository, StopsRepository stopsRepository) {
        this.context = context;
        this.activity = activity;
        this.citiesRepository = citiesRepository;
        this.routesRepository = routesRepository;
        this.stopsRepository = stopsRepository;
    }

    public void initialize() {

        initializeSearch();
        Preferences.initialize(this.context, this.activity);
        int savedCityId = Preferences.getSavedCityId();

        initializeCityNameButton(savedCityId);
        final ArrayList<Route> routes = this.routesRepository.getRoutesByCityID(savedCityId);
        refreshRouteListView(routes);
    }

    public void restoreActions() {
        Editable savedFilterValue = this.activity.getRouteFilterEdit().getEditableText();
        this.adapter.getFilter().filter(savedFilterValue);
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


    private void doSearch(String queryStr) {
//        int savedCityId = Preferences.getSavedCityId();
//        final ArrayList<Route> routes = this.routesRepository.getRoutesByStopName(queryStr, savedCityId);
//        refreshRouteListView(routes);
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

    private void initializeSearch() {
        SearchManager searchManager = (SearchManager) this.activity.getSystemService(Context.SEARCH_SERVICE);
        this.activity.getSearchView1().setSearchableInfo(searchManager.getSearchableInfo(this.activity.getComponentName()));
        this.activity.getSearchView1().setOnQueryTextListener(this);
        this.activity.getSearchView1().setOnSuggestionListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        int savedCityId = Preferences.getSavedCityId();
        Cursor cursor = this.stopsRepository.getStopsSuggestionsCursor(newText, savedCityId);

        if (cursor.getCount() != 0) {
            Log.d(LOG_TAG, "suggestions cursor size: " + cursor.getCount());
            String[] columns = new String[]{StopsRepository.STOP_NAME_COLUMN_NAME};
            int[] columnTextId = new int[]{android.R.id.text1};
            SimpleCursorAdapter simple = new SimpleCursorAdapter(activity.getBaseContext(),
                    android.R.layout.simple_list_item_1, cursor, columns, columnTextId, 0);

            activity.getSearchView1().setSuggestionsAdapter(simple);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean onSuggestionSelect(int position) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean onSuggestionClick(int position) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


}
