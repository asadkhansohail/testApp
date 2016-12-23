package com.example.silenthunter.mylogin;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

/**
 * Created by SilentHunter on 12/15/2016.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, LoginActivity.class);
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(1));
        startActivity(intent);

        finish();
    }
}
