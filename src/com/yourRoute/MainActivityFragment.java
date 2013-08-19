package com.yourRoute;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;


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
            String stops = this.getResources().getString(R.string.stop_name);
            String route_number = this.getResources().getString(R.string.route_number);
            String favorites = this.getResources().getString(R.string.favorites);
            main_tabhost.addTab(main_tabhost.newTabSpec("StopsSearchTab").setIndicator(stops).setContent(R.id.stops_search_panel));
            main_tabhost.addTab(main_tabhost.newTabSpec("RoutesSearch").setIndicator(route_number).setContent(R.id.route_number_search));
            main_tabhost.addTab(main_tabhost.newTabSpec("Favorites").setIndicator(favorites).setContent(R.id.favorites_list));
            main_tabhost.setCurrentTab(0);
            main_tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                @Override
                public void onTabChanged(String tabId) {
                    for (int i = 0; i < main_tabhost.getChildCount(); ++i) {
                        TextView textView = (TextView) main_tabhost.findViewById(android.R.id.title);
                        textView.setTextColor(Color.WHITE);
                    }
                }
            });

        }

        return rootView;
    }
}


