package com.yourRoute.routeDetailsActivity;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.yourRoute.R;
import com.yourRoute.model.Selections;
import com.yourRoute.model.Stop;

import java.util.ArrayList;

public class StopsListAdapter extends ArrayAdapter<Stop> {
    private Selections selections;

    public StopsListAdapter(Context context, int textViewResourceId, ArrayList<Stop> objects, Selections selections) {
        super(context, textViewResourceId, objects);
        this.selections = selections;
    }

    @Override
    public View getView(int position, View item, ViewGroup parent) {

        if (item == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(R.layout.stop_item, null);
        }
        Stop stop = super.getItem(position);
        TextView name = (TextView) item.findViewById(R.id.stop_name);

        String searchKey = this.selections.getSelectedStop().toLowerCase();
        String optionalSearchKey = this.selections.getOptionalSelectedStop().toLowerCase();
        String stopName = stop.getName();
        name.setText(stopName);
        if (stopName.toLowerCase().contains(searchKey) && !searchKey.isEmpty()) {
            name.setTextColor(Color.parseColor("#33b5e5"));
        } else if (stopName.toLowerCase().contains(optionalSearchKey) && !optionalSearchKey.isEmpty()) {
            name.setTextColor(Color.parseColor("#33b5e5"));
        } else {
            name.setTextColor(Color.BLACK);
        }

        return item;
    }

}


