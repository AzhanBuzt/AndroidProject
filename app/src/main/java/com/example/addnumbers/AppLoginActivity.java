package com.example.addnumbers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;

public class AppLoginActivity extends AppCompatActivity {
    EditText etNum1, etNum2;
    Button btnLogin;
    public static TextView data;
    TextView timer;
    CountDownTimer countDownTimer;
    LinearLayout mainlayout;
    ArrayList<HashMap<String,String >> userList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_login);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        data = (TextView) findViewById(R.id.txtlognresponse);
        timer = (TextView) findViewById(R.id.txtTimer);
        etNum1 = (EditText) findViewById(R.id.etNum1);
        etNum2 = (EditText) findViewById(R.id.etNum2);
        mainlayout =(LinearLayout)findViewById(R.id.lnloginMain);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                data.setText("");
                // TODO Auto-generated method stub
                if(!etNum1.getText().toString().equals("")&&!etNum2.getText().toString().equals(""))
                {
                    timer();
                    FectData process= new FectData(etNum1.getText().toString());
                    process.execute();
                }
                else

                    {
                        Toast.makeText(AppLoginActivity.this, "Enter value in both fields", Toast.LENGTH_LONG).show();
                }
                InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mainlayout.getWindowToken(),0);

            }});
    }
    public void timer() {
        countDownTimer=new CountDownTimer(10000, 1000) { //display a delay of 20 seconds

            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {


                if (!data.getText().equals(""))
                {countDownTimer.onFinish();}
                else if(data.getText().equals("")) {
                 timer.setText("Connecting to Server: " + millisUntilFinished / 1000);
                }


            }

            @SuppressLint("SetTextI18n")
            public void onFinish() {
                if (!data.getText().equals(""))
                {
                Seperator obj= new Seperator(data.getText().toString().trim());
                final String FinalError=obj.ErrorMessage;
                final String FinalId=obj.Id;
                final String FinalPassword=obj.Password;
                final String FinalStatus=obj.Status;
                if(FinalError.equals("null"))
                 {
                     if(FinalStatus.equals("1"))
                     {
                         if(etNum1.getText().toString().equals(FinalId) && etNum2.getText().toString().equals(FinalPassword))
                         {
                             timer.setText("Done!");
                             cancel();
                             //Intent int =new Intent(this,MainActivity.class);
                             Intent i = new Intent(AppLoginActivity.this, MainActivity.class);
                             startActivity(i);
                         }
                         else{
                             timer.setText("Given Credentials are wrong for "+FinalId);
                             cancel();

                         }
                     }
                     else{
                         timer.setText("Logged in user status is "+FinalStatus);
                         cancel();
                     }

                 }
                 else{
                    timer.setText(FinalError);
                    cancel();
                 }
                }
                else{
                    timer.setText("No Response from API!");
                    cancel();
                }

            }
        }.start();



    }
}