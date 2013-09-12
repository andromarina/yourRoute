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
import com.yourRoute.mainActivity.SearchProvider;

public class CustomSearchField extends RelativeLayout implements TextWatcher {

    private AutoCompleteTextView autoCompleteTextView;
    private ImageButton clearButton;
    private OnClickListener clearText;
    private Context context;
    private AttributeSet attr;
    private SearchProvider searchProvider;

    public CustomSearchField(Context context, AttributeSet attr) {
        super(context, attr);
        this.context = context;
        this.attr = attr;

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout layout = (RelativeLayout) mInflater.inflate(R.layout.custom_search_field, this);

        autoCompleteTextView = (AutoCompleteTextView) layout.getChildAt(0).findViewById(R.id.autocompete_text_view);
        autoCompleteTextView.addTextChangedListener(this);
        clearButton = (ImageButton) layout.findViewById(R.id.clear_button);

        initClearButton();

        applyAttributes();

        initAutoCompleteTextView();
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
        String searchKeyValidated = validateString(searchKey);
        setSearchProvider(searchProvider, searchKeyValidated);
    }

    @Override
    public void afterTextChanged(Editable s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public AutoCompleteTextView getAutoCompleteTextView() {
        return this.autoCompleteTextView;
    }

    public void initialize(SearchProvider searchProvider) {
        this.searchProvider = searchProvider;
    }

    private void initClearButton() {

        clearText = new OnClickListener() {

            @Override
            public void onClick(View v) {

                autoCompleteTextView.setText("");
            }
        };
        clearButton.setOnClickListener(clearText);
    }

    private void applyAttributes() {

        TypedArray typedArray = context.obtainStyledAttributes(attr, R.styleable.CustomSearchField);

        //hint attribute
        String hint = typedArray.getString(R.styleable.CustomSearchField_hint);
        if (hint != null && !hint.isEmpty()) {
            autoCompleteTextView.setHint(hint);
        }

        //threshold
        int threshold = typedArray.getInt(R.styleable.CustomSearchField_threshold, 0);
        if (threshold != 0) {
            autoCompleteTextView.setThreshold(threshold);
        }
    }

    private void initAutoCompleteTextView() {

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedSuggestion = (String) ((TextView) view).getText();
                autoCompleteTextView.setText(selectedSuggestion);
            }
        });
    }

    private void setSearchProvider(SearchProvider searchProvider, String searchKey) {
        SimpleCursorAdapter adapter = searchProvider.getSuggestions(context, searchKey);
        this.autoCompleteTextView.setAdapter(adapter);
    }

    private String validateString(String string) {
        String stringValidated = string.replace("'", "''");
        return stringValidated;
    }

    public String getValidatedString() {
        String autocompleteTextViewContent = this.autoCompleteTextView.getText().toString();
        String result = validateString(autocompleteTextViewContent);
        return result;
    }
}

