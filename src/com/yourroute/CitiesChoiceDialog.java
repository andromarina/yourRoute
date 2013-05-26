package com.yourroute;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.R;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/12/13
 * Time: 5:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class CitiesChoiceDialog extends DialogFragment {

    private Context context;
    private ArrayList<City> cities;
    private TextView textView;

    public CitiesChoiceDialog(Context context, ArrayList<City> cities, TextView textView) {

        this.context = context;
        this.cities = cities;
        this.textView = textView;
    }

    @Override
     public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        builder.setTitle(com.yourroute.R.string.select_city_list_title);
        CityListAdapter adapter = new CityListAdapter(context, com.yourroute.R.id.city_name, this.cities);
        CitiesListListener listener = new CitiesListListener(cities, textView);
        builder.setSingleChoiceItems(adapter, -1,listener);

      return builder.create();
     }

}
