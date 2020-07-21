package com.example.addnumbers;
import com.example.addnumbers.ConnectionDb;
import android.Manifest;
import android.content.pm.PackageManager;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //creating object
    TextView tvResult;
    EditText etNum1, etNum2;
    Button btnAdd,btnSub,btnMul,btnDiv,btnClear;
    TextToSpeech t1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);




        // initializing object

        tvResult = (TextView) findViewById(R.id.tvResult);
        etNum1 = (EditText) findViewById(R.id.etNum1);
        etNum2 = (EditText) findViewById(R.id.etNum2);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMul= (Button) findViewById(R.id.btnMul);
        btnDiv=(Button) findViewById(R.id.btnDiv);
        btnClear=(Button) findViewById(R.id.btnClr);
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ConnectionDb connectionDb = new ConnectionDb();
                Connection connect = connectionDb.CONN();
                if (connect == null) {
                    getText2Voice("Not Connected to db");
                }
                else{
                    try {
                        String query = "select id,username,password from tbl_login";
                        Statement stmt = connect.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            tvResult.setText(rs.getString("username"));
                            Log.e("", rs.getString("id") + "/" + rs.getString("username") + "/" + rs.getString("password"));
                        }
                    }
                    catch (Exception ex)
                    {
                        Log.e("Exception",ex.getMessage());
                    }
                }

            }

        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(etNum1.getText().toString().equals("") &&  !etNum2.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, getString(R.string.ErrorFirstET), Toast.LENGTH_LONG).show();
                    getText2Voice(getString(R.string.ErrorFirstET));
                }
                else if(etNum2.getText().toString().equals("") &&  !etNum1.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, getString(R.string.ErrorSecondET), Toast.LENGTH_LONG).show();
                    getText2Voice(getString(R.string.ErrorSecondET));
                }
                else if(etNum2.getText().toString().equals("") && etNum1.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, getString(R.string.ErrorBoth), Toast.LENGTH_LONG).show();
                    getText2Voice(getString(R.string.ErrorBoth));
                }
                else{

                    // Taking input from editText to String variable

                    String name = etNum1.getText().toString();
                    String name2 = etNum1.getText().toString();
                    //Converting String type to Double which support decimal value
                    Double num1 = Double.parseDouble(name);
                    Double num2 = Double.parseDouble(name2);
                    // Adding both user input and assign value into variable named as res
                    Double res = num1 / num2;
                    //Converting calculated res from double to string again
                    String s = Double.toString(res);
                    //Setting string s into textView as textView only accept string
                    tvResult.setText(s);
                    String toSpeak = tvResult.getText().toString();
                    //Passing value s in same activity in toast message
                    Toast.makeText(MainActivity.this,
                            s, Toast.LENGTH_LONG).show();
                    getText2Voice(toSpeak);
                    ClearText();
                }}
        });





        btnMul.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(etNum1.getText().toString().equals("") &&  !etNum2.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, getString(R.string.ErrorFirstET), Toast.LENGTH_LONG).show();
                    getText2Voice(getString(R.string.ErrorFirstET));
                }
                else if(etNum2.getText().toString().equals("") &&  !etNum1.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, getString(R.string.ErrorSecondET), Toast.LENGTH_LONG).show();
                    getText2Voice(getString(R.string.ErrorSecondET));
                }
                else if(etNum2.getText().toString().equals("") && etNum1.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, getString(R.string.ErrorBoth), Toast.LENGTH_LONG).show();
                    getText2Voice(getString(R.string.ErrorBoth));
                }
                else{

                    // Taking input from editText to String variable

                    String name = etNum1.getText().toString();
                    String name2 = etNum1.getText().toString();
                    //Converting String type to Double which support decimal value
                    Double num1 = Double.parseDouble(name);
                    Double num2 = Double.parseDouble(name2);
                    // Adding both user input and assign value into variable named as res
                    Double res = num1 * num2;
                    //Converting calculated res from double to string again
                    String s = Double.toString(res);
                    //Setting string s into textView as textView only accept string
                    tvResult.setText(s);
                    String toSpeak = tvResult.getText().toString();
                    //Passing value s in same activity in toast message
                    Toast.makeText(MainActivity.this,
                            s, Toast.LENGTH_LONG).show();
                    getText2Voice(toSpeak);
                    ClearText();
                }}
        });




        btnSub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(etNum1.getText().toString().equals("") &&  !etNum2.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, getString(R.string.ErrorFirstET), Toast.LENGTH_LONG).show();
                    getText2Voice(getString(R.string.ErrorFirstET));
                }
                else if(etNum2.getText().toString().equals("") &&  !etNum1.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, getString(R.string.ErrorSecondET), Toast.LENGTH_LONG).show();
                    getText2Voice(getString(R.string.ErrorSecondET));
                }
                else if(etNum2.getText().toString().equals("") && etNum1.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, getString(R.string.ErrorBoth), Toast.LENGTH_LONG).show();
                    getText2Voice(getString(R.string.ErrorBoth));
                }
                else{

                    // Taking input from editText to String variable

                    String name = etNum1.getText().toString();
                    String name2 = etNum1.getText().toString();
                    //Converting String type to Double which support decimal value
                    Double num1 = Double.parseDouble(name);
                    Double num2 = Double.parseDouble(name2);
                    // Adding both user input and assign value into variable named as res
                    Double res = num1 - num2;
                    //Converting calculated res from double to string again
                    String s = Double.toString(res);
                    //Setting string s into textView as textView only accept string
                    tvResult.setText(s);
                    String toSpeak = tvResult.getText().toString();
                    //Passing value s in same activity in toast message
                    Toast.makeText(MainActivity.this,
                            s, Toast.LENGTH_LONG).show();
                    getText2Voice(toSpeak);
                    ClearText();
                }}
        });

            // button click method
            btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
            if(etNum1.getText().toString().equals("") &&  !etNum2.getText().toString().equals(""))
            {
                Toast.makeText(MainActivity.this, getString(R.string.ErrorFirstET), Toast.LENGTH_LONG).show();
                getText2Voice(getString(R.string.ErrorFirstET));
            }
            else if(etNum2.getText().toString().equals("") &&  !etNum1.getText().toString().equals(""))
            {
                Toast.makeText(MainActivity.this, getString(R.string.ErrorSecondET), Toast.LENGTH_LONG).show();
                getText2Voice(getString(R.string.ErrorSecondET));
            }
            else if(etNum2.getText().toString().equals("") && etNum1.getText().toString().equals(""))
            {
                Toast.makeText(MainActivity.this, getString(R.string.ErrorBoth), Toast.LENGTH_LONG).show();
                getText2Voice(getString(R.string.ErrorBoth));
            }
            else{

                // Taking input from editText to String variable

                String name = etNum1.getText().toString();
                String name2 = etNum1.getText().toString();
                //Converting String type to Double which support decimal value
                Double num1 = Double.parseDouble(name);
                Double num2 = Double.parseDouble(name2);
                // Adding both user input and assign value into variable named as res
                Double res = num1 + num2;
                //Converting calculated res from double to string again
                String s = Double.toString(res);
                //Setting string s into textView as textView only accept string
                tvResult.setText(s);
                String toSpeak = tvResult.getText().toString();
                //Passing value s in same activity in toast message
                Toast.makeText(MainActivity.this,
                        s, Toast.LENGTH_LONG).show();
                getText2Voice(toSpeak);
                ClearText();
            }}
        });
    }

        public void onPause()
    {
            if(t1 !=null){
                t1.stop();
            }
            super.onPause();
    }

    public void onResume()
    {
     super.onResume();
     getText2Voice("Welcome Back to "+getString(R.string.app_name));
    }
        //Method for Text2Voice
        public String getText2Voice(String s)
        {
            t1.speak(s, TextToSpeech.QUEUE_FLUSH, null);
            return s;
        }
    //Method for Clear
    public void ClearText()
    {
        etNum1.setText("");
        etNum2.setText("");
    }

    public String TimeUpdate()
    {
        String Key;
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, h:mm a");
        String date = df.format(Calendar.getInstance().getTime());
        String[] parts = date.split(",");
        String Time = parts[2];
        if (Time.contains("PM")){
             Key=getString(R.string.KeyEve);
        }
        else{
            Key=getString(R.string.KeyMorning);
        }
        return Key;
    }





}