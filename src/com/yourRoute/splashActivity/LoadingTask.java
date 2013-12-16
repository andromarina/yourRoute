package com.yourRoute.splashActivity;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;
import contentProvider.RoutesContentProvider;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 12/10/13
 * Time: 9:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoadingTask extends AsyncTask<String, Integer, Boolean> {
    private final String LOG_TAG = getClass().getSimpleName();

    public interface LoadingTaskFinishedListener {
        void onTaskFinished();

    }

    private final ProgressBar progressBar;
    private final LoadingTaskFinishedListener finishedListener;

    /**
     * A Loading task that will load some resources that are necessary for the app to start
     * @param progressBar - the progress bar you want to update while the task is in progress
     * @param finishedListener - the listener that will be told when this task is finished
     */
    public LoadingTask(ProgressBar progressBar, LoadingTaskFinishedListener finishedListener) {
        this.progressBar = progressBar;
        this.finishedListener = finishedListener;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        Log.d(LOG_TAG, "Starting do in background" + Thread.currentThread().getName());

        if(RoutesContentProvider.instance().initializeDB()){
            Log.d(LOG_TAG, "do in background true");
            return true;
        }
        Log.d(LOG_TAG, "do in background false");
        return false;
    }

//
//    @Override
//    protected void onProgressUpdate(Integer... values) {
//        super.onProgressUpdate(values);
//        progressBar.setProgress(values[0]); // This is ran on the UI thread so it is ok to update our progress bar ( a UI view ) here
//    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        Log.d(LOG_TAG, "onPostExecute called " + Thread.currentThread().getName());
        finishedListener.onTaskFinished();
    }
}
