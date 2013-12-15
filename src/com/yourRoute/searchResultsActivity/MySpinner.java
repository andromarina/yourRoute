package com.yourRoute.searchResultsActivity;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;


/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 12/15/13
 * Time: 3:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class MySpinner extends DialogFragment {

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {

        ProgressDialog dialog = new ProgressDialog(getActivity());
        this.setStyle(STYLE_NO_FRAME, getTheme());
        dialog.setCancelable(true);
        return dialog;
    }

}
