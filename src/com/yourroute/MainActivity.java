package com.yourroute;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private Button cityNameButton;
    private ListView listViewMain;
    private MainActivityController mainActivityController;


    @Override
    protected void onStart() {
        super.onStart();
        this.mainActivityController.initialize();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setCustomView(R.layout.city_name_indicator);
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);

        this.cityNameButton = (Button) findViewById(R.id.city_name_button);
        this.listViewMain = (ListView) findViewById(R.id.listViewMain);

        CitiesRepository citiesRepository = new CitiesRepository(getContentResolver());
        RoutesRepository routesRepository = new RoutesRepository(getContentResolver());
        this.mainActivityController = new MainActivityController(this, this, citiesRepository, routesRepository);
    }

    public Button getCityNameButton() {
        return this.cityNameButton;
    }

    public ListView getRoutesListView() {
        return this.listViewMain;
    }


}
