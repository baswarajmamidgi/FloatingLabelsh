package com.bhvp.antiragging;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class AutoComp extends AppCompatActivity implements  OnItemClickListener, OnItemSelectedListener  {



    AutoCompleteTextView textView=null;
    AutoCompleteTextView textView1=null;
    AutoCompleteTextView textView2=null;
    AutoCompleteTextView textView3=null;
    AutoCompleteTextView textView4=null;
    AutoCompleteTextView textView5=null;

    private ArrayAdapter<String> adapter;
    int i=3;


    public static ArrayList<String> phoneValueArr = new ArrayList<String>();
    public static ArrayList<String> nameValueArr = new ArrayList<String>();


    String toNumberValue="";
    public String pno[]=new String[6];

    int j=0;



    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.contacts);


        for(int i=0;i<6;i++)
            pno[i]="NULL";

        new AsyncTask<Void, Integer, List<String>>() {
            private ProgressDialog dialog = new ProgressDialog(AutoComp.this);

            @Override
            protected void onPreExecute() {

                this.dialog.setMessage("Please wait");
                this.dialog.show();


            }
            @Override
            protected List<String> doInBackground(Void... pVoids) {
                List<String> contacts = new ArrayList<String>();

                //--read contacts---
                readContactData();

                return contacts;
            }

            @Override
            protected void onPostExecute(List<String> result) {

                if (dialog.isShowing()) {
                    dialog.dismiss();
                }


                // findViewById(R.id.progressBar).setVisibility(View.GONE);

            }
        }.execute();





        textView = (AutoCompleteTextView) findViewById(R.id.ed1);
        textView1= (AutoCompleteTextView) findViewById(R.id.ed2);
        textView2 = (AutoCompleteTextView) findViewById(R.id.ed3);
        textView3 = (AutoCompleteTextView) findViewById(R.id.ed4);
        textView4 = (AutoCompleteTextView) findViewById(R.id.ed5);
        textView5 = (AutoCompleteTextView) findViewById(R.id.ed6);




        adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
        textView.setThreshold(1);
        textView1.setThreshold(1);
        textView2.setThreshold(1);
        textView3.setThreshold(1);
        textView4.setThreshold(1);
        textView5.setThreshold(1);


        textView.setAdapter(adapter);
        textView.setOnItemSelectedListener(this);
        textView.setOnItemClickListener(this);
        textView1.setAdapter(adapter);
        textView1.setOnItemSelectedListener(this);
        textView1.setOnItemClickListener(this);
        textView2.setAdapter(adapter);
        textView2.setOnItemSelectedListener(this);
        textView2.setOnItemClickListener(this);
        textView3.setAdapter(adapter);
        textView3.setOnItemSelectedListener(this);
        textView3.setOnItemClickListener(this);
        textView4.setAdapter(adapter);
        textView4.setOnItemSelectedListener(this);
        textView4.setOnItemClickListener(this);
        textView5.setAdapter(adapter);
        textView5.setOnItemSelectedListener(this);
        textView5.setOnItemClickListener(this);





    }

    public void onClickAddContact(View v) {

        if (i == 3)
            findViewById(R.id.ed3).setVisibility(View.VISIBLE);
        if (i == 4)
            findViewById(R.id.ed4).setVisibility(View.VISIBLE);
        if (i == 5)
            findViewById(R.id.ed5).setVisibility(View.VISIBLE);
        if (i == 6)
            findViewById(R.id.ed6).setVisibility(View.VISIBLE);
        i++;


    }

    public void onClickSave(View v) {

        SharedPreferences settings = getSharedPreferences("mypreferences", MODE_PRIVATE);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);


        SharedPreferences.Editor editor = settings.edit();

        editor.putString("number0", pno[0]);
        editor.putString("number1", pno[1]);
        editor.putString("number2", pno[2]);
        editor.putString("number3", pno[3]);
        editor.putString("number4", pno[4]);
        editor.putString("number5", pno[5]);
        editor.putString("key1","NOTOK");
        editor.apply();


        // Setting Dialog Title
        alertDialog.setTitle("NOTE!");

        // Setting Dialog Message
        alertDialog.setMessage("The Personal Details and Contacts will be saved for furthur usage! You can make modifications in Settings");

        // On pressing Settings button
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent ourIntent = new Intent(AutoComp.this, MainAct.class);
                startActivity(ourIntent);


            }
        });
        alertDialog.show();
    }


            private void readContactData() {

                try {


                    String phoneNumber = "";
                    ContentResolver cr = getBaseContext()
                            .getContentResolver();


                    Cursor cur = cr
                            .query(ContactsContract.Contacts.CONTENT_URI,
                                    null,
                                    null,
                                    null,
                                    null);


                    if (cur.getCount() > 0) {

                        Log.i("AutocompleteContacts", "Reading   contacts........");

                        int k = 0;
                        String name = "";


                        while (cur.moveToNext()) {

                            String id = cur
                                    .getString(cur
                                            .getColumnIndex(ContactsContract.Contacts._ID));
                            name = cur
                                    .getString(cur
                                            .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));


                            if (Integer
                                    .parseInt(cur
                                            .getString(cur
                                                    .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {


                                Cursor pCur = cr
                                        .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                                null,
                                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                                        + " = ?",
                                                new String[]{id},
                                                null);
                                int j = 0;

                                while (pCur
                                        .moveToNext()) {

                                    if (j == 0) {

                                        phoneNumber = "" + pCur.getString(pCur
                                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


                                        adapter.add(name);


                                        phoneValueArr.add(phoneNumber.toString());
                                        nameValueArr.add(name.toString());

                                        j++;
                                        k++;
                                    }
                                }
                                pCur.close();
                            }

                        }

                    }
                    cur.close();


                } catch (Exception e) {
                    Log.i("AutocompleteContacts", "Exception : " + e);
                }


            }

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
                                       long arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

                InputMethodManager imm = (InputMethodManager) getSystemService(
                        INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            }

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub


                int i = nameValueArr.indexOf("" + arg0.getItemAtPosition(arg2));


                if (i >= 0) {


                    toNumberValue = phoneValueArr.get(i);

                    InputMethodManager imm = (InputMethodManager) getSystemService(
                            INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);


                    Toast.makeText(getBaseContext(),
                            "Position:" + arg2 + " Name:" + arg0.getItemAtPosition(arg2) + " Number:" + toNumberValue,
                            Toast.LENGTH_LONG).show();
                    pno[j] = toNumberValue;
                    j++;

                    Log.d("AutocompleteContacts",
                            "Position:" + arg2 + " Name:" + arg0.getItemAtPosition(arg2) + " Number:" + toNumberValue);

                }

            }

            protected void onResume() {
                super.onResume();
            }

            protected void onDestroy() {
                super.onDestroy();
            }


        }