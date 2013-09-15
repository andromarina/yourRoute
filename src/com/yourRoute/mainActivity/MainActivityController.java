package com.yourRoute.mainActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import com.yourRoute.Preferences;
import com.yourRoute.R;
import com.yourRoute.model.CitiesRepository;
import com.yourRoute.model.City;
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
    private CitiesRepository citiesRepository;
    private RoutesRepository routesRepository;
    private ArrayList<City> cities;
    private SearchController searchController;
    private TabHost.TabSpec tspec;

    public MainActivityController(Context context, MainActivity activity, CitiesRepository citiesRepository,
                                  RoutesRepository routesRepository) {
        this.context = context;
        this.activity = activity;
        this.citiesRepository = citiesRepository;
        this.routesRepository = routesRepository;
    }

    public void initialize() {

        initializeTabHost();
        this.searchController = new SearchController(context, activity);
        searchController.initialize();
        int savedCityId = Preferences.getSavedCityId();
        initializeCityNameButton(savedCityId);
        FavoritesController favoritesController = new FavoritesController(context, activity, routesRepository);
        favoritesController.refreshFavoritesList();
        Preferences.setListener(favoritesController);
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
        this.searchController.clearAllSearchFields();
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

    private void initializeTabHost() {

        TabHost main_tabhost = activity.getMainTabhost();
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

//        TabWidget tabs = (TabWidget)main_tabhost.getTabWidget();
//        for (int i = 0; i<tabs.getChildCount(); i++) {
//            LinearLayout tab = (LinearLayout) tabs.getChildAt(i);
//            tab.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.tab_indicator));
//        }
    }
}
