package com.example.sugamparajuli.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Objects;

public class RegisteredActivity extends AppCompatActivity {

    private TextView UserNameView, PasswordView, Addressview, PhoneView, GenderView, spDistrict;
    private String Gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        UserNameView = (TextView)findViewById(R.id.tv_ViewUsername);
        PasswordView = (TextView)findViewById(R.id.tv_ViewPassword);
        PhoneView = (TextView)findViewById(R.id.tv_ViewPhone);
        Addressview = (TextView)findViewById(R.id.tv_ViewAddress);
        GenderView = (TextView)findViewById(R.id.tv_ViewGender);
        spDistrict = (TextView)findViewById(R.id.sp_dropdown_view);
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            Gender = extras.getString("radioChosen");
//
//        }
        UserNameView.setText(Objects.requireNonNull(getIntent().getExtras()).getString("RegisterUsernameFinal"));
        PasswordView.setText(getIntent().getExtras().getString("RegisterPasswordFinal"));
        PhoneView.setText(getIntent().getExtras().getString("RegisterPhoneFinal"));
        Addressview.setText(getIntent().getExtras().getString("RegisterAddressFinal"));
        GenderView.setText(getIntent().getExtras().getString("RegisterRadioFinal"));
        spDistrict.setText(getIntent().getExtras().getString("spDistrict"));
    }
}
