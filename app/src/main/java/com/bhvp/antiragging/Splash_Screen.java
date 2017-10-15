package com.bhvp.antiragging;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Splash_Screen extends AppCompatActivity{


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);



        Thread background = new Thread() {
            public void run() {
                SharedPreferences settings = getSharedPreferences("mypreferences", MODE_PRIVATE);

                // Writing data to SharedPreferences
                final SharedPreferences.Editor editor = settings.edit();


              try {
                    // Thread will sleep for 5 seconds
                    sleep(3 * 1000);

                    if(settings.getString("skipkey","OK").equalsIgnoreCase("OK")) {
                        // After 5 seconds redirect to another intent
                        Intent i = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(i);
                    }
                  else
                    {
                        Intent i = new Intent(getBaseContext(), MainAct.class);
                        startActivity(i);
                    }
                    //Remove activity
                    finish();

                } catch (Exception e) {

                }
            }
        };

        // start thread
        background.start();
    }
    protected void onDestroy() {

        super.onDestroy();

    }
}
