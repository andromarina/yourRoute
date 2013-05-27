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

    private final RouteListAdapter adapter;
    private String LOG_TAG = "RoutesFilter";
    private final static Object lockObj = new Object();
    private ArrayList<Route> allRoutes;

    public RoutesFilter(RouteListAdapter adapter, ArrayList<Route> routes) {
        this.allRoutes = new ArrayList<Route>();
        this.allRoutes.addAll(routes);
        this.adapter = adapter;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void publishResults(CharSequence constraint, FilterResults toReturn) {

        adapter.filteredRoutesArray = (ArrayList<Route>) toReturn.values;

        adapter.notifyDataSetChanged();
        adapter.clear();
        for (int i = 0, l = adapter.filteredRoutesArray.size(); i < l; i++) {
            adapter.add(adapter.filteredRoutesArray.get(i));
        }
        adapter.notifyDataSetInvalidated();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        Log.i(LOG_TAG, "routes before filtering " + this.adapter.getCount());
        FilterResults toReturn = new FilterResults();
        synchronized (lockObj) {
            if (constraint != null && constraint.toString().length() > 0) {
                Log.i(LOG_TAG, "constraint is " + constraint);
                ArrayList<Route> filteredItems = new ArrayList<Route>();

                for (int i = 0, l = this.allRoutes.size(); i < l; i++) {
                    Route r = this.allRoutes.get(i);
                    if (r.getName().toLowerCase().contains(constraint))
                        filteredItems.add(r);
                }
                toReturn.count = filteredItems.size();
                Log.i(LOG_TAG, "to return matched " + filteredItems.size());
                toReturn.values = filteredItems;
            } else {

                toReturn.values = this.allRoutes;
                toReturn.count = this.allRoutes.size();
                Log.i(LOG_TAG, "to return string empty " + toReturn.count);
            }
        }
        return toReturn;
    }
}