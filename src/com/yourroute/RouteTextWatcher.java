package com.yourroute;

import android.text.Editable;


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

