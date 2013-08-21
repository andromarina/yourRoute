package com.yourRoute;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;


public class MainActivityFragment extends Fragment {

    private TabHost main_tabhost;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.main, container, false);

        if (container == null) {
            this.main_tabhost = (TabHost) rootView.findViewById(R.id.main_tabhost);
            main_tabhost.setup();
            main_tabhost.setCurrentTab(0);

        }

        return rootView;
    }
}


