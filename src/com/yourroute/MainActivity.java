package com.yourroute;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.*;
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
    public boolean onSearchRequested() {
        Log.i("Test", "onSearchRequested");
        return super.onSearchRequested();
    }

    @Override
    public void startSearch(String initialQuery, boolean selectInitialQuery, Bundle appSearchData, boolean globalSearch) {
        super.startSearch(initialQuery, selectInitialQuery, appSearchData, globalSearch);
        Log.i("Test", "startSearch");
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

        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        AutoCompleteTextView search_text = (AutoCompleteTextView) searchView.findViewById(searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null));
        search_text.setTextColor(Color.WHITE);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
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

    @Override
    protected void onResume() {
        super.onResume();
        mainActivityController.restoreActions();
    }

    public EditText getRouteFilterEdit() {
        return this.routeFilterEdit;
    }


}
