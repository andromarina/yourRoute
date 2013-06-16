package com.yourroute;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;


public class RouteDetailsFragment extends Fragment {

    private TabHost direction_tabhost;

    public RouteDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.route_card, container, false);

        if (container == null) {
            this.direction_tabhost = (TabHost) rootView.findViewById(R.id.direction_tabhost);
            direction_tabhost.setup();
            direction_tabhost.addTab(direction_tabhost.newTabSpec("ForwardTab")
                    .setIndicator(getResources().getString(R.string.forward))
                    .setContent(R.id.forward_stops_list));
            direction_tabhost.addTab(direction_tabhost.newTabSpec("BackwardTab")
                    .setIndicator(getResources().getString(R.string.backward))
                    .setContent(R.id.backward_stops_list));
            direction_tabhost.setCurrentTab(0);
            direction_tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                @Override
                public void onTabChanged(String tabId) {
                    for (int i = 0; i < direction_tabhost.getChildCount(); ++i) {
                        TextView textView = (TextView) direction_tabhost.findViewById(android.R.id.title);
                        textView.setTextColor(Color.WHITE);
                    }
                }
            });

        }

        return rootView;
    }
}


