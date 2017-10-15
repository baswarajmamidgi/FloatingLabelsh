

package com.bhvp.antiragging;

import android.support.v7.app.AppCompatActivity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

public class myclass extends AppCompatActivity {
    GPSTracker gps;
    double latitude,longitude;
    public String pno[]=new String[6];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gps = new GPSTracker(myclass.this);
        SharedPreferences settings = getSharedPreferences("mypreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        if(gps.canGetLocation()){


            editor.putString("key2", "OK");
            editor.apply();
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings




            editor.putString("key2", "NOTOK");
            editor.apply();
            gps.showSettingsAlert();

        }


        // Writing data to SharedPreferences

        pno[0]=settings.getString("number0","");
        pno[1]=settings.getString("number1","");
        pno[2]=settings.getString("number2","");
        pno[3]=settings.getString("number3","");
        pno[4]=settings.getString("number4","");
        pno[5]= settings.getString("number5","");
        int c=0;
        String message = "I am "+settings.getString("namekey","")+" from "+settings.getString("branchkey","")+
                " of ID "+settings.getString("Idkey","")+"\n"+
                "Please Help Me!! I might be ragged at:"+settings.getString("place","")+"\n\nMap Location: http://maps.google.com/?q="+latitude+","+longitude;
        if(settings.getString("key1","OK").equalsIgnoreCase("NOTOK")&&settings.getString("key2","OK").equalsIgnoreCase("OK")){
            for(int i=0;i<6;i++) {


                if (!(pno[i].equalsIgnoreCase("NULL"))) {

                    try {
                        SmsManager sms = SmsManager.getDefault();
                        PendingIntent sentPI;
                        String SENT = "SMS_SENT";

                        sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);

                        sms.sendTextMessage(pno[i], null, message, sentPI, null);

                        Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
                        c++;
                        this.finish();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "SMS failed, No Contacts selected.", Toast.LENGTH_SHORT).show();
                        c++;
                        e.printStackTrace();
                    }


                }

            }
            if(c==0) {
                Toast.makeText(getApplicationContext(), "SMS Failed, No Contacts Selected.", Toast.LENGTH_SHORT).show();
                this.finish();
            }
        }
        else {


            if (settings.getString("key2", "OK").equalsIgnoreCase("OK")) {
                Toast.makeText(getApplicationContext(), "SMS Failed, No Contacts Selected.", Toast.LENGTH_SHORT).show();
                this.finish();

            }
            editor.putString("key2", "OK");
            editor.apply();
        }
    }
}

