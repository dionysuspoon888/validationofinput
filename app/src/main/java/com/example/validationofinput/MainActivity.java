package com.example.validationofinput;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //phone must be the one sending SMS
    //country code to asert the length
    //HK : length = 8
    //China : length = 11
    //Macau : length = 8

    String pass;
    EditText et_pass;
    EditText et_email;
    EditText et_test;
    TextView tv_result;
    Button checkingForPassword;
    Button checkingForEmail;
    Button b_test;
    Spinner sp_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_pass = findViewById(R.id.et_pass);
        tv_result = findViewById(R.id.tv_result);
        checkingForPassword = findViewById(R.id.b_checkingForPassword);
        checkingForEmail = findViewById(R.id.b_checkingForEmail);
        et_email = findViewById(R.id.et_email);
        sp_phone = findViewById(R.id.sp_phone);
        et_test = findViewById(R.id.et_test);
        b_test = findViewById(R.id.b_test);



        checkingForPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass();
            }
        });

        checkingForEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isValidEmail();

            }
        });

        b_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();

            }
        });



        spinnerCountryCode();




    }

    //password

    public void pass(){
        pass = et_pass.getText().toString().trim();
        //(?=.*[@#$%^&+=])
        if (!pass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")){
            tv_result.setText("Result: "+"密碼格式錯誤");

        }else{
            tv_result.setText("Result: SS");
        }
    }

    //email
        public void isValidEmail() {
        CharSequence email  = et_email.getText();
        if (email == null) {
            tv_result.setText("Result: "+"Null");

        } else {

           if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
               tv_result.setText("Result: "+"電郵式錯誤");
           }else{
               tv_result.setText("Result: "+"SS");
           }
        }
    }


    public void spinnerCountryCode(){
        String[] phoneCountryCode = getResources().getStringArray(R.array.safequestion1);
        ArrayAdapter<String> phoneContryCodeadapter = new ArrayAdapter<String>(
                // getActivity(),R.array.phoneCountryCode,R.layout.membercard_spinner_item) {
                this,R.layout.spinner_item,phoneCountryCode) {
            @Override
            public boolean isEnabled(int position) {
//                if (position == 0) {
//                    // Disable the first item from Spinner
//                    // First item will be use for hint
//                    return false;
//                } else {
//                    return true;
//                }
                return true;
            };
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textview = (TextView) view;
                if (position == 0) {
                    textview.setTextColor(Color.GRAY);
                } else {
                    textview.setTextColor(Color.BLACK);
                }
                return view;
            }

        };

//        ArrayAdapter<CharSequence> safeQuestion3adapter = ArrayAdapter.createFromResource(getActivity(),
//                R.array.safequestion3, R.layout.membercard_spinner_item);
        //zh
        phoneContryCodeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_phone.setAdapter(phoneContryCodeadapter);


    }

    //Filter the last character e.g. ?
    public void test(){

        String tmp = et_test.getText().toString().trim();
        //substring(starting point , #char displayed)
        //e.g. "12345".substring(1,3) = 234
        tv_result.setText(tmp.substring(0,tmp.length()-1));

    }
}
