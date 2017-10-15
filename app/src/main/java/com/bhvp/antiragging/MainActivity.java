package com.bhvp.antiragging;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {



    private Toolbar toolbar;
    private EditText inputName, inputBranch, inputID;
    private TextInputLayout inputLayoutName, inputLayoutBranch, inputLayoutID;
    private Button btnSave;
    public String name;
    public String branch;
    public String id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutBranch = (TextInputLayout) findViewById(R.id.input_layout_branch);
        inputLayoutID = (TextInputLayout) findViewById(R.id.input_layout_id);
        inputName = (EditText) findViewById(R.id.input_name);
        inputBranch = (EditText) findViewById(R.id.input_branch);
        inputID = (EditText) findViewById(R.id.input_id);
        btnSave = (Button) findViewById(R.id.btn_save);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=inputName.getText().toString();
                branch=inputBranch.getText().toString();
                id=inputID.getText().toString();
                inputName.addTextChangedListener(new MyTextWatcher(inputName));
                inputBranch.addTextChangedListener(new MyTextWatcher(inputBranch));
                inputID.addTextChangedListener(new MyTextWatcher(inputID));
                submitForm(name,branch,id);
            }
        });
    }


    private void submitForm(String id1,String id2,String id3) {
        if (!validateName()) {
            return;
        }

        if (!validateBranch()) {
            return;
        }

        if (!validateID()) {
            return;
        }
        SharedPreferences settings = getSharedPreferences("mypreferences", MODE_PRIVATE);


        SharedPreferences.Editor editor = settings.edit();
        editor.putString("namekey", id1);
        editor.putString("branchkey", id2);
        editor.putString("Idkey", id3);
        editor.putString("skipkey", "NOTOK");
        editor.apply();




        if(settings.getString("key","OK").equalsIgnoreCase("OK")) {

            Intent ourIntent =new Intent(MainActivity.this, AutoComp.class);
            startActivity(ourIntent);
        }
        else
        {
            Intent i = new Intent(MainActivity.this, MainAct.class);
            startActivity(i);
        }



    }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateBranch() {

        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutBranch.setError(getString(R.string.err_msg_branch));
            requestFocus(inputBranch);
            return false;
        } else {
            inputLayoutBranch.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateID() {
        if (inputID.getText().toString().trim().isEmpty()) {
            inputLayoutID.setError(getString(R.string.err_msg_id));
            requestFocus(inputID);
            return false;
        }
        else if (inputID.getText().toString().trim().length()!=10) {
            inputLayoutID.setError(getString(R.string.err_msg_id1));
            requestFocus(inputID);
            return false;
        }
        else {
            inputLayoutID.setErrorEnabled(false);
        }

        return true;
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_branch:
                    validateBranch();
                    break;
                case R.id.input_id:
                    validateID();
                    break;
            }
        }
    }
    protected void onDestroy() {

        super.onDestroy();

    }
}
