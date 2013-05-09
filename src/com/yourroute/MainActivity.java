package com.yourroute;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
	private SearchView searchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		searchView = (SearchView) findViewById(R.id.searchView);
		searchView.setIconifiedByDefault(false);

        Repository repository = new Repository(getContentResolver());
        ArrayList<City> cities = repository.getCities();

        for (City city : cities) {
            Log.i("Test", city.toString());
        }

//		ListView listViewMain = (ListView) findViewById(R.id.listViewMain);
//
//		ArrayList<CarData> data = loadData();
//		ListAdapter adapterList = new ListAdapter(this, R.layout.item, data);
//
//		listViewMain.setAdapter(adapterList);
//
		
//		getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
//
//		ActionBar.OnNavigationListener navigationListener = new OnNavigationListener() {
//
//			@Override
//			public boolean onNavigationItemSelected(int itemPosition,
//					long itemId) {
//				Toast.makeText(getBaseContext(),
//						"You selected : " + cities[itemPosition],
//						Toast.LENGTH_SHORT).show();
//				return false;
//			}
//		};


//
//		ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(getBaseContext(),
//				R.layout.spinner,cities);
//
//		getActionBar().setListNavigationCallbacks(adapterSpinner, navigationListener);
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
