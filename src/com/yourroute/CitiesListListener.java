package com.yourroute;

import android.app.Activity;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/12/13
 * Time: 5:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class CitiesListListener implements DialogInterface.OnClickListener {

    private ArrayList<City> cities;
    private TextView textView;

    public CitiesListListener(ArrayList<City> cities, TextView textView) {
        this.cities = cities;
        this.textView = textView;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Log.i("Test",  cities.get(which).getName() + " was selected");
        String name = cities.get(which).getName();
        Log.i("Test", "chosen city " + name);
        textView.setText(name);
        int cityId = cities.get(which).getId();
        Preferences.saveCityId(cityId);
        dialog.dismiss();
    }
}
