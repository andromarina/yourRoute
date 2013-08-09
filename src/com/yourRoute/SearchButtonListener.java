package com.yourRoute;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SearchButtonListener implements Button.OnClickListener {

    private Context context;
    private MainActivity activity;

    public SearchButtonListener(MainActivity activity, Context context) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(context, SearchResultsActivity.class);
        String searchKeyMain = activity.getSearchMain().getText().toString();
        if (searchKeyMain.isEmpty()) {
            String needTofillMainField = activity.getResources().getString(R.string.need_to_fill_main_search_field);
            Toast.makeText(context, needTofillMainField, 10).show();
            return;
        }
        intent.putExtra("SearchPhrase", searchKeyMain);
        YourRouteApp.saveSearchPhrase(searchKeyMain);
        String searchKeyOptional = activity.getStreetSearchOptional().getText().toString();
        intent.putExtra("OptionalSearchPhrase", searchKeyOptional);
        YourRouteApp.saveOptionalSearchPhrase(searchKeyOptional);
        int searchMode = activity.getCurrentSearchMode();
        intent.putExtra("SearchMode", searchMode);
        activity.startActivity(intent);
    }
}
