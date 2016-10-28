package com.xaut.khalil.clickclick;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Startup_page extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_startup_page);

        new Handler().postDelayed(r, 2000);

    }

    Runnable r = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(Startup_page.this, Login.class);
            startActivity(intent);
            finish();
        }
    };
}
