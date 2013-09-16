package com.yourRoute.routeDetailsActivity;

import android.app.Activity;
import android.content.Intent;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 9/15/13
 * Time: 5:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Report {
    private Activity activity;

    public Report (Activity activity) {
         this.activity = activity;
    }

    public void sendReport() {

        Intent Email = new Intent(Intent.ACTION_SEND);
        Email.setType("text/email");
        Email.putExtra(Intent.EXTRA_EMAIL, new String[] { "m.chuguy@gmail.com" });
        Email.putExtra(Intent.EXTRA_SUBJECT, "Неточности в маршруте");
        Email.putExtra(Intent.EXTRA_TEXT, "Номер маршрута:" +
                System.getProperty("line.separator") +
                "Город: " +
                System.getProperty("line.separator") +
                "Уточнение: ");
        activity.startActivity(Intent.createChooser(Email, "Send Feedback:"));
    }
}
