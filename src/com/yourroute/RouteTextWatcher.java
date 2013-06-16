package com.yourroute;

import android.text.Editable;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/26/13
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class RouteTextWatcher implements android.text.TextWatcher {
    public RouteListAdapter adapter;

    public RouteTextWatcher(RouteListAdapter adapter) {
        this.adapter = adapter;
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        adapter.getFilter().filter(s);
    }

}

