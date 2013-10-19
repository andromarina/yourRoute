package com.yourRoute.mainActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TabHost;
import com.yourRoute.Preferences;
import com.yourRoute.R;
import com.yourRoute.YourRouteApp;
import com.yourRoute.model.CitiesRepository;
import com.yourRoute.model.City;
import com.yourRoute.model.RoutesHolder;
import com.yourRoute.model.RoutesRepository;

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
    private RoutesHolder routesHolder;
    private ArrayList<City> cities;
    private SearchController searchController;
    private TabHost.TabSpec tspec;

    public MainActivityController(MainActivity activity) {
        this.activity = activity;
        this.context = activity;
        this.routesHolder = YourRouteApp.getRoutesHolder();
    }

    public void initialize() {

        initializeTabHost();
        this.searchController = new SearchController(activity);
        searchController.initialize();
        int savedCityId = Preferences.getSavedCityId();
        initializeCityNameButton(savedCityId);
        FavoritesController favoritesController = new FavoritesController(activity);
        favoritesController.refreshFavoritesList();
        Preferences.setListener(favoritesController);
    }

    private void showCityChoiceDialog() {

        FragmentManager fm = this.activity.getSupportFragmentManager();
        this.cities = this.routesHolder.getAllCities();

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
        this.searchController.clearAllSearchFields();
    }

    private void initializeCityNameButton(int savedCityId) {

        String savedCityName = routesHolder.findCityById(savedCityId).getName();
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

    private void initializeTabHost() {

        final TabHost main_tabhost = activity.getMainTabhost();
        main_tabhost.setup();
        Resources res = activity.getResources();

        //Tab 1
        this.tspec = main_tabhost.newTabSpec("Tab 1");
        tspec.setIndicator("", res.getDrawable(R.drawable.ic_directions_light));
        tspec.setContent(R.id.stop_name_search);
        main_tabhost.addTab(tspec);

        //Tab 2
        this.tspec = main_tabhost.newTabSpec("Tab 2");
        tspec.setIndicator("", res.getDrawable(R.drawable.ic_number_light));
        tspec.setContent(R.id.route_number_search);
        main_tabhost.addTab(tspec);

        //Tab 3
        this.tspec = main_tabhost.newTabSpec("Tab 3");
        tspec.setIndicator("", res.getDrawable(R.drawable.ic_favorites_light));
        tspec.setContent(R.id.favorites);
        main_tabhost.addTab(tspec);

        main_tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equals("Tab 3")) {
                    activity.getStopSearchMain().clearFocus();
                    hideKeyboard();
                }
            }
        });
    }

    private void hideKeyboard() {

        InputMethodManager imm = (InputMethodManager)  context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getStopSearchMain().getWindowToken(), 0);
    }

}
