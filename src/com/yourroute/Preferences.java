package com.yourroute;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/11/13
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Preferences {

    private static SharedPreferences sPref;
    private static Activity activity;
    private static Context context;

    public static void initialize(Context c, Activity a) {
        context = c;
        activity = a;
    }


    public static void saveCityId(int cityId) {

        sPref =  activity.getPreferences(context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt("CityId", cityId);
        ed.commit();
        Log.i("Test", cityId + "City id saved");
    }

    public static int getSavedCityId() {

        sPref = activity.getPreferences(context.MODE_PRIVATE);
        int savedCityId = sPref.getInt("CityId", 1);
        Log.i("Test", savedCityId + "City id loaded");
        return savedCityId;
    }
}
