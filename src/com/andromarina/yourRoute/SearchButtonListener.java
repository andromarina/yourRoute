package com.andromarina.yourRoute;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.andromarina.R;

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
        String searchKeyOptional = activity.getStreetSearchOptional().getText().toString();
        intent.putExtra("OptionalSearchPhrase", searchKeyOptional);
        int searchMode = activity.getCurrentSearchMode();
        intent.putExtra("SearchMode", searchMode);
        activity.startActivity(intent);
    }
}
