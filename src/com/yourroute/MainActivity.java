package com.yourroute;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
	private SearchView searchView;
    private ArrayList<City> cities;
    private TextView cityNameSettled;
    private CitiesRepository citiesRepository;
    private RoutesRepository routesRepository;

    @Override
    protected void onStart() {
        super.onStart();
        this.cityNameSettled = (TextView) findViewById(R.id.city_name_settled);
        int savedCityId = Preferences.getSavedCityId();
        String savedCityName = citiesRepository.getCity(savedCityId).getName();
        cityNameSettled.setText(savedCityName);
    }

    @Override
	protected void onCreate(Bundle savedInstanceState) {





		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        Preferences.initialize(getBaseContext(), this);

        this.routesRepository = new RoutesRepository(getContentResolver());
        ArrayList<Route> routes =  this.routesRepository.getRoutes();
        for (int i = 0; i < routes.size(); ++i) {
            Log.i("Test", "i = " + i + " " + routes.get(i).toString());
        }


        getActionBar().setDisplayShowTitleEnabled(false);

        this.citiesRepository = new CitiesRepository(getContentResolver());
        this.cities = citiesRepository.getCities();

		ListView listViewMain = (ListView) findViewById(R.id.listViewMain);

		ListAdapter adapterList = new ListAdapter(this, R.layout.list_item, routes);

		listViewMain.setAdapter(adapterList);
        getActionBar().setCustomView(R.layout.city_name_indicator);
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
                | ActionBar.DISPLAY_SHOW_HOME);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.action_select_city:
                showCityChoiceDialog();
                break;
            default:
                break;
        }
        return true;
    }

    protected void showCityChoiceDialog() {

        FragmentManager fm = getSupportFragmentManager();
        CitiesChoiceDialog cityChoiceDialog = new CitiesChoiceDialog(this, cities, this.cityNameSettled);
        cityChoiceDialog.show(fm, "CityChoiceDialog");
    }

//	protected ArrayList<CarData> loadData() {
//		ArrayList<CarData> toReturn = new ArrayList <CarData>();
//
//		CarData cd1 = new CarData();
//		cd1.Id = "1";
//		cd1.Name = "444";
//		cd1.CarType = "Автобус";
//		cd1.StartEnd = "ул.Космонавта Комарова - проспект Правды";
//
//		toReturn.add(cd1);
//
//		CarData cd2 = new CarData();
//		cd2.Id = "2";
//		cd2.Name = "444 (12-A)";
//		cd2.CarType = "Троллейбус";
//		toReturn.add(cd2);
//
//		CarData cd3 = new CarData();
//		cd3.Id = "3";
//		cd3.Name = "444 (12-A)";
//		cd3.CarType = "Троллейбус";
//		toReturn.add(cd3);
//
//		CarData cd4 = new CarData();
//		cd4.Id = "4";
//		cd4.Name = "444";
//		cd4.CarType = "Троллейбус";
//		toReturn.add(cd4);
//
//		CarData cd5 = new CarData();
//		cd5.Id = "5";
//		cd5.Name = "444";
//		cd5.CarType = "Троллейбус";
//		toReturn.add(cd5);
//
//		CarData cd6 = new CarData();
//		cd6.Id = "6";
//		cd6.Name = "444";
//		cd6.CarType = "Троллейбус";
//		toReturn.add(cd6);
//
//		CarData cd7 = new CarData();
//		cd7.Id = "7";
//		cd7.Name = "444";
//		cd7.CarType = "Троллейбус";
//		toReturn.add(cd7);
//
//		CarData cd8 = new CarData();
//		cd8.Id = "8";
//		cd8.Name = "444";
//		cd8.CarType = "Троллейбус";
//		toReturn.add(cd8);
//
//		CarData cd9 = new CarData();
//		cd9.Id = "9";
//		cd9.Name = "444";
//		cd9.CarType = "Троллейбус";
//		toReturn.add(cd9);
//
//		CarData cd10 = new CarData();
//		cd10.Id = "10";
//		cd10.Name = "444";
//		cd10.CarType = "Троллейбус";
//		toReturn.add(cd10);
//
//		CarData cd11 = new CarData();
//		cd11.Id = "11";
//		cd11.Name = "444";
//		cd11.CarType = "Троллейбус";
//		toReturn.add(cd11);
//
//		return toReturn;
//
//	}

}
