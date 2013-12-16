package com.yourRoute.splashActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import com.yourRoute.R;
import com.yourRoute.mainActivity.MainActivity;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 12/10/13
 * Time: 9:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class SplashActivity extends Activity implements LoadingTask.LoadingTaskFinishedListener {
    private final String LOG_TAG = getClass().getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.activity_splash_progress_bar);
        new LoadingTask(progressBar, this).execute();
    }

    // This is the callback for when your async task has finished
    @Override
    public void onTaskFinished() {
        completeSplash();
        Log.d(LOG_TAG, "onTaskFinished called " + Thread.currentThread().getName());
    }

    private void completeSplash(){
        startApp();
        Log.d(LOG_TAG, "completeSplash startApp called " + Thread.currentThread().getName());
        finish();
        Log.d(LOG_TAG, "completeSplash finish called " + Thread.currentThread().getName());
    }

    private void startApp() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        Log.d(LOG_TAG, "startApp called " + Thread.currentThread().getName());
    }
}
