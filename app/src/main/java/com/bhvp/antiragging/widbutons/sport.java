package com.bhvp.antiragging.widbutons;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bhvp.antiragging.myclass;


public class sport extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences("mypreferences", MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();
        editor.putString("place","Sports Block");
        editor.apply();

        Intent i = new Intent(sport.this, myclass.class);
        startActivity(i);
        this.finish();
    }
}