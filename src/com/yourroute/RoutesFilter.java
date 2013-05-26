package com.yourroute;

import android.util.Log;
import android.widget.Filter;
import com.yourroute.model.Route;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/26/13
 * Time: 6:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class RoutesFilter extends Filter {

    private RouteListAdapter adapter;
    private String LOG_TAG = "RoutesFilter";
    private final static Object lockObj = new Object();

    public RoutesFilter (RouteListAdapter adapter) {

        this.adapter = adapter;
    }


    @SuppressWarnings("unchecked")
    @Override
    protected void publishResults(CharSequence constraint, FilterResults toReturn) {

        ArrayList<Route> filteredRoutesArray = (ArrayList<Route>) toReturn.values;

        adapter.notifyDataSetChanged();
        adapter.clear();
        for (int i = 0, l = filteredRoutesArray.size(); i < l; i++) {
            adapter.add(filteredRoutesArray.get(i));
        }
        adapter.notifyDataSetInvalidated();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        Log.i(LOG_TAG, "routes before filtering " + this.adapter.getCount());
        final FilterResults toReturn = new FilterResults();
        synchronized (lockObj) {
            if (constraint != null && constraint.toString().length() > 0) {
                Log.i(LOG_TAG, "constraint is " + constraint);
                ArrayList<Route> filteredItems = new ArrayList<Route>();

                for (int i = 0, l = this.adapter.getCount(); i < l; i++) {
                    Route r = this.adapter.getItem(i);
                    if (r.getName().toLowerCase().contains(constraint))
                        filteredItems.add(r);
                }
                toReturn.count = filteredItems.size();
                toReturn.values = filteredItems;
            } else {

                toReturn.values = this.adapter.getRoutes();
                toReturn.count = this.adapter.getCount();
            }
        }
        return toReturn;
    }
}