package com.yourroute;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.*;
import com.yourroute.model.CitiesRepository;
import com.yourroute.model.RoutesRepository;

public class MainActivity extends FragmentActivity {

    private Button cityNameButton;
    private ListView listViewMain;
    private EditText routeFilterEdit;
    private SearchView searchView1;
    private AutoCompleteTextView searchText1;
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

        configureSearch();

        this.cityNameButton = (Button) findViewById(R.id.city_name_button);
        this.listViewMain = (ListView) findViewById(R.id.listViewMain);
        this.routeFilterEdit = (EditText) findViewById(R.id.route_number);

        CitiesRepository citiesRepository = new CitiesRepository(getContentResolver());
        RoutesRepository routesRepository = new RoutesRepository(getContentResolver());
        this.mainActivityController = new MainActivityController(this, this, citiesRepository, routesRepository);
        this.mainActivityController.handleIntent(getIntent());

    }

    private void configureSearch() {

        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setCustomView(R.layout.city_name_button);
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);

        this.searchView1 = (SearchView) findViewById(R.id.searchView1);
        this.searchText1 = (AutoCompleteTextView) searchView1.findViewById(searchView1.getContext().getResources().getIdentifier("android:id/search_src_text", null, null));

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.mainActivityController.handleIntent(intent);
    }

    public Button getCityNameButton() {
        return this.cityNameButton;
    }

    public ListView getRouteListView() {
        return this.listViewMain;
    }

    public SearchView getSearchView1() {
        return this.searchView1;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainActivityController.restoreActions();
    }

    public EditText getRouteFilterEdit() {
        return this.routeFilterEdit;
    }

    public AutoCompleteTextView getSearchText1() {
        return this.searchText1;
    }


}
