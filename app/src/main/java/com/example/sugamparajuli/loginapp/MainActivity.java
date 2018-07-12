package com.example.sugamparajuli.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sugamparajuli.loginapp.utils.ShowToast;

public class MainActivity extends AppCompatActivity {

    private EditText Username;
    private Button Register;
    private EditText Password;
    private Button Login;
    private TextView Info;
    private int Counter = 5;
    public String StrUsername, StrPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username =(EditText)findViewById(R.id.et_username);
        Password = (EditText)findViewById(R.id.et_password);
        Login = (Button)findViewById(R.id.bt_Login);
        Register = (Button)findViewById(R.id.bt_Register);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StrUsername = Username.getText().toString().trim();
                StrPassword = Password.getText().toString().trim();
                if (TextUtils.isEmpty(StrUsername)) {
                    ShowToast.showToast(MainActivity.this, "Username cannot be Empty.", true);
                }
                    else if(TextUtils.isEmpty(StrPassword)) {
                        ShowToast.showToast(MainActivity.this, "Password cannot be Empty.", true);
                }else{
                    validate(Username.getText().toString(), Password.getText().toString());
                }
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });
    }

    private void validate(String UsernameCheck, String PasswordCheck){
        if ((UsernameCheck.equals("Sugam")) && (PasswordCheck.equals("12345"))){
            Intent i = new Intent(this,SecondActivity.class);
            i.putExtra("UserNamePass",Username.getText().toString());
            i.putExtra("PasswordPass",Password.getText().toString());
            startActivity(i);
        }
        else{
            Counter--;
            if (Counter!=0) {
                ShowToast.showToast(MainActivity.this, "Wrong Password. " + Counter + " attemps left.", true);
            }
            else if (Counter==0){
                Login.setEnabled(false);
                ShowToast.showToast(MainActivity.this,"Maximum wrong Attemps. Login Button Disabled.",true);
            }
        }
    }
}
