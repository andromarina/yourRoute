package com.yourRoute.mainActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.yourRoute.R;
import com.yourRoute.searchResultsActivity.IBackgroundMaker;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 12/16/13
 * Time: 11:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class SpinnerBGTask extends AsyncTask{
    ProgressDialog progressDialog;
    Context context;
    IBackgroundMaker backgroundMaker;

    public SpinnerBGTask(Context context, IBackgroundMaker backgroundMaker){
        this.context = context;
        this.backgroundMaker = backgroundMaker;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.progressDialog = ProgressDialog.show(context, "test", "wait");
        progressDialog.setCancelable(false);
        progressDialog.setContentView(R.layout.loading_spinner);
    }

    @Override
    protected Boolean doInBackground(Object... params) {
       if(backgroundMaker.doInBackground()) {
           return true;
       }
       return false;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        backgroundMaker.onFinished();
        this.progressDialog.dismiss();
    }
}
