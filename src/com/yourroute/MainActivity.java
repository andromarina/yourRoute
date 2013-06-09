package com.yourroute;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.yourroute.model.CitiesRepository;
import com.yourroute.model.RoutesRepository;

public class MainActivity extends FragmentActivity {

    private Button cityNameButton;
    private ListView listViewMain;
    private EditText routeFilterEdit;
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
        getActionBar().setCustomView(R.layout.city_name_button);
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);

        this.cityNameButton = (Button) findViewById(R.id.city_name_button);
        this.listViewMain = (ListView) findViewById(R.id.listViewMain);
        this.routeFilterEdit = (EditText) findViewById(R.id.route_number);

        CitiesRepository citiesRepository = new CitiesRepository(getContentResolver());
        RoutesRepository routesRepository = new RoutesRepository(getContentResolver());
        this.mainActivityController = new MainActivityController(this, this, citiesRepository, routesRepository);

    }

    public Button getCityNameButton() {
        return this.cityNameButton;
    }

    public ListView getRouteListView() {
        return this.listViewMain;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainActivityController.restoreActions();
    }

    public EditText getRouteFilterEdit() {
        return this.routeFilterEdit;
    }


}
