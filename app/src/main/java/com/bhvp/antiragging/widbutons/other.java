package com.bhvp.antiragging.widbutons;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bhvp.antiragging.myclass;


public class other extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences("mypreferences", MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();
        editor.putString("place","N/A - View on Map");
        editor.apply();

        Intent i = new Intent(other.this, myclass.class);
        startActivity(i);
        this.finish();
    }
}