package com.yourRoute;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StopSearchButtonListener implements Button.OnClickListener {

    private Context context;
    private MainActivity activity;

    public StopSearchButtonListener(MainActivity activity, Context context) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(context, SearchResultsActivity.class);
        String stopSearchMain = activity.getStopSearchMain().getAutoCompleteTextView().getText().toString();
        if (stopSearchMain.isEmpty()) {
            String needTofillMainField = activity.getResources().getString(R.string.need_to_fill_main_search_field);
            Toast.makeText(context, needTofillMainField, 10).show();
            return;
        }
        intent.putExtra("SearchMode", 1);
        intent.putExtra("StopName", stopSearchMain);
        YourRouteApp.saveSearchPhrase(stopSearchMain);
        String stopNameOptional = activity.getStopSearchOptional().getAutoCompleteTextView().getText().toString();
        intent.putExtra("OptionalStopName", stopNameOptional);
        YourRouteApp.saveOptionalSearchPhrase(stopNameOptional);
        activity.startActivity(intent);
    }
}
