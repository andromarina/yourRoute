package com.yourroute;

import android.app.ActionBar;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/9/13
 * Time: 7:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpinnerListener implements ActionBar.OnNavigationListener {

    private ArrayList<City> cities;

    public SpinnerListener(ArrayList<City> cities) {
      this.cities = cities;
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        Log.i("Test",  cities.get(itemPosition).getName() + " was selected");
        int cityId = cities.get(itemPosition).getId();
        Preferences.saveCityId(cityId);
        return true;
    }

}
