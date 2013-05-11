package com.yourroute;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 4/24/13
 * Time: 8:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpinnerAdapter extends ArrayAdapter<City> {


    int savedCityId = Preferences.loadCityId();

    public SpinnerAdapter(Context context, int textViewResourceId, ArrayList<City> cities) {
        super(context, textViewResourceId, cities);
    }

    @Override
    public View getView(int position, View spinnerItem, ViewGroup parent) {

        if (spinnerItem == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            spinnerItem = inflater.inflate(R.layout.spinner_item, null);
        }
        String cityName = super.getItem(position).getName();
        TextView spinnerItemText = (TextView) spinnerItem.findViewById(R.id.spinnerItem);
        spinnerItemText.setText(cityName);
        return spinnerItemText;
    }

    @Override
    public View getDropDownView(int position, View spinnerItem,
                                ViewGroup parent) {


        if (spinnerItem == null) {

            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            spinnerItem = inflater.inflate(R.layout.spinner_item, null);

        }

        String cityName = super.getItem(position).getName();
        TextView spinnerItemText = (TextView) spinnerItem.findViewById(R.id.spinnerItem);
        spinnerItemText.setText(cityName);
        return spinnerItemText;

    }
}
