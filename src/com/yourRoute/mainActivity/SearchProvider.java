package com.yourRoute.mainActivity;

import android.content.Context;
import android.widget.SimpleCursorAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 8/18/13
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SearchProvider {

    public SimpleCursorAdapter getSuggestions(Context context, String searchKey);
}
