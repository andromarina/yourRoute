package com.yourRoute.model;

import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 10/19/13
 * Time: 8:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchPhraseSelection {
    private String searchPhrase = "";
    private String optionalSearchPhrase = "";
    private final String LOG_TAG = this.getClass().getSimpleName();

    public void saveSearchPhrase(String string) {
        this.searchPhrase = string;
        Log.d(LOG_TAG, "search phrase: " + this.searchPhrase);
    }

    public void saveOptionalSearchPhrase(String string) {
        this.optionalSearchPhrase = string;
        Log.d(LOG_TAG, "search phrase optional: " + this.optionalSearchPhrase);
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public String getOptionalSearchPhrase() {
        return optionalSearchPhrase;
    }
}
