package com.yourRoute.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.yourRoute.R;

public class CustomSearchField extends RelativeLayout implements TextWatcher {

    AutoCompleteTextView autoCompleteTextView;
    ImageButton clearButton;
    OnClickListener cleartext;

    public CustomSearchField(Context context, AttributeSet attr) {
        super(context);

        String inflater = context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) getContext().getSystemService(inflater);
        layoutInflater.inflate((R.layout.search_field), this, true);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.street_search);
        autoCompleteTextView.addTextChangedListener(this);
        clearButton = (ImageButton) findViewById(R.id.clear_button);

        TypedArray typedArray = context.obtainStyledAttributes(attr, R.styleable.CustomSearchField);
        String hint = typedArray.getString(R.styleable.CustomSearchField_hint);
        if (!hint.isEmpty()) {
            autoCompleteTextView.setHint(hint);
        }

        cleartext = new OnClickListener() {

            @Override
            public void onClick(View v) {

                autoCompleteTextView.setText("");
            }
        };
        clearButton.setOnClickListener(cleartext);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedSuggestion = (String) ((TextView) view).getText();
                autoCompleteTextView.setText(selectedSuggestion);
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String searchKey = s.toString();
        if (searchKey.isEmpty()) {
            clearButton.setVisibility(View.INVISIBLE);
            return;
        }

        clearButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void afterTextChanged(Editable s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

