package com.yourroute;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/26/13
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class FilterTextWatcher implements TextWatcher {
    public RouteListAdapter adapter;

    public FilterTextWatcher(RouteListAdapter adapter) {
        this.adapter = adapter;
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
       // adapter.getFilter().filter(s);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        adapter.getFilter().filter(s);
    }

}

