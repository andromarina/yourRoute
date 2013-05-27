package com.yourroute;

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
        ArrayList<Route> temp = (ArrayList<Route>) toReturn.values;

        adapter.notifyDataSetChanged();
        adapter.clear();
        adapter.addAll(temp);
        adapter.notifyDataSetInvalidated();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults toReturn = new FilterResults();
        synchronized (lockObj) {
            if (constraint != null && constraint.toString().length() > 0) {
                ArrayList<Route> filteredItems = new ArrayList<Route>();

                for (int i = 0, l = this.allRoutes.size(); i < l; i++) {
                    Route r = this.allRoutes.get(i);
                    if (r.getName().toLowerCase().contains(constraint))
                        filteredItems.add(r);
                }
                toReturn.count = filteredItems.size();
                toReturn.values = filteredItems;
            } else {

                toReturn.values = this.allRoutes;
                toReturn.count = this.allRoutes.size();
            }
        }
        return toReturn;
    }
}