package com.yourRoute.mainActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.yourRoute.R;
import com.yourRoute.model.City;

import java.util.ArrayList;

public class CitiesChoiceDialog extends DialogFragment {

    private Context context;
    private ArrayList<City> cities;
    private DialogInterface.OnClickListener listener;

    public CitiesChoiceDialog(Context context, ArrayList<City> cities, DialogInterface.OnClickListener listener) {

        this.context = context;
        this.cities = cities;
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        builder.setTitle(R.string.select_city_list_title);
        CityListAdapter adapter = new CityListAdapter(context, R.id.city_name, this.cities);

        builder.setSingleChoiceItems(adapter, -1, listener);

        return builder.create();
    }


}
