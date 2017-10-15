package com.bhvp.antiragging;



import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;

import android.telephony.SmsManager;
import android.app.PendingIntent;
import android.content.Intent;




import android.widget.Toast;



/**
 * Created by banda on 9/19/2015.
 */
public class MainAct extends AppCompatActivity {

    EditText inputStudentName, inputPlace, inputDescription;

    String sname, place, desc;
    private Toolbar mToolbar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.act_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(null);



        inputStudentName = (EditText) findViewById(R.id.input_name);
        inputPlace = (EditText) findViewById(R.id.input_place);
        inputDescription = (EditText) findViewById(R.id.editText);
           }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        SharedPreferences settings = getSharedPreferences("mypreferences", MODE_PRIVATE);


        SharedPreferences.Editor editor = settings.edit();
        Intent ourIntent;
        switch (item.getItemId()) {
            case R.id.personal:
                editor.putString("key", "NOTOK");
                editor.apply();
                ourIntent =new Intent(MainAct.this, MainActivity.class);
                startActivity(ourIntent);
                return true;
            case R.id.contacts:
                ourIntent =new Intent(MainAct.this, AutoComp.class);
                startActivity(ourIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void onClickSubmit(View v) {
        int c=0;

        String pno[] = new String[6];
        SharedPreferences settings = getSharedPreferences("mypreferences", MODE_PRIVATE);

        pno[0]=settings.getString("number0","");
        pno[1]=settings.getString("number1","");
        pno[2]=settings.getString("number2","");
        pno[3]=settings.getString("number3","");
        pno[4]=settings.getString("number4","");
        pno[5]= settings.getString("number5","");
        sname = inputStudentName.getText().toString();
        place = inputPlace.getText().toString();
        desc = inputDescription.getText().toString();


        // Writing data to SharedPreferences
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("pnamekey",sname);
        editor.putString("placekey",place);
        editor.putString("desckey",desc);
        editor.apply();

        String message = "I am "+settings.getString("namekey","")+" from "+settings.getString("branchkey","")+
                " of ID "+settings.getString("Idkey","")+"\n"+"I am being ragged. \n Name of Senior: "+settings.getString("pnamekey","")+"\n Place: "+settings.getString("placekey","")+"\n"+"Description : "+settings.getString("desckey","");
        for (int i = 0; i < 6; i++) {

            if (!(pno[i].equalsIgnoreCase("NULL"))) {

                try {
                    SmsManager sms = SmsManager.getDefault();
                    PendingIntent sentPI;
                    String SENT = "SMS_SENT";

                    sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);

                    sms.sendTextMessage(pno[i], null, message, sentPI, null);
                    Toast.makeText(getApplicationContext(),"SMS sent.", Toast.LENGTH_LONG).show();
                    c++;
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "SMS failed, No Contacts Selected.", Toast.LENGTH_SHORT).show();
                    c++;
                    e.printStackTrace();
                }

            }
        }
        if(c==0)
            Toast.makeText(getApplicationContext(), "SMS failed, No contacts Selected", Toast.LENGTH_SHORT).show();
    }
    public void onClickWhatsApp(View view) {
        SharedPreferences settings = getSharedPreferences("mypreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        sname = inputStudentName.getText().toString();
        place = inputPlace.getText().toString();
        desc = inputDescription.getText().toString();
        editor.putString("pnamekey",sname);
        editor.putString("placekey",place);
        editor.putString("desckey",desc);
        editor.apply();

        PackageManager pm=getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");

            String text = "I am "+settings.getString("namekey","")+" from "+settings.getString("branchkey","")+
                    " of ID "+settings.getString("Idkey","")+"\n"+"I am being ragged. \n Name of Senior: "+settings.getString("pnamekey","")+"\n Place: "+settings.getString("placekey","")+"\n"+"Description : "+settings.getString("desckey","");

            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }

    }

    public void onClickHike(View view) {
        SharedPreferences settings = getSharedPreferences("mypreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        sname = inputStudentName.getText().toString();
        place = inputPlace.getText().toString();
        desc = inputDescription.getText().toString();
        editor.putString("pnamekey",sname);
        editor.putString("placekey",place);
        editor.putString("desckey",desc);
        editor.apply();

        PackageManager pm=getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");

            String text = "I am "+settings.getString("namekey","")+" from "+settings.getString("branchkey","")+
                    " of ID "+settings.getString("Idkey","")+"\n"+"I am being ragged. \n Name of Senior: "+settings.getString("pnamekey","")+"\n Place: "+settings.getString("placekey","")+"\n"+"Description : "+settings.getString("desckey","");

            PackageInfo info=pm.getPackageInfo("com.bsb.hike", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.bsb.hike");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "Hike not Installed", Toast.LENGTH_SHORT)
                    .show();
        }

    }





}




