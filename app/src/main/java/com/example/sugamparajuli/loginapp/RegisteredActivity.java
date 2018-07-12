package com.example.sugamparajuli.loginapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class RegisteredActivity extends AppCompatActivity {

    private TextView userNameView, passwordView, addressview, phoneView, genderView, spDistrict, userNameViewTop;
    private String Gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        userNameViewTop = (TextView)findViewById(R.id.tv_view_username_top);
        userNameView = (TextView)findViewById(R.id.tv_view_username);
        passwordView = (TextView)findViewById(R.id.tv_view_password);
        phoneView = (TextView)findViewById(R.id.tv_view_phone);
        addressview = (TextView)findViewById(R.id.tv_view_address);
        genderView = (TextView)findViewById(R.id.tv_view_gender);
        spDistrict = (TextView)findViewById(R.id.sp_dropdown_view);
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            Gender = extras.getString("radioChosen");
//
//        }
        userNameViewTop.setText((getIntent().getExtras().getString("RegisterUsernameFinal")));
        userNameView.setText((getIntent().getExtras()).getString("RegisterUsernameFinal"));
        passwordView.setText(getIntent().getExtras().getString("RegisterPasswordFinal"));
        phoneView.setText(getIntent().getExtras().getString("RegisterPhoneFinal"));
        addressview.setText(getIntent().getExtras().getString("RegisterAddressFinal"));
        genderView.setText(getIntent().getExtras().getString("RegisterRadioFinal"));
        spDistrict.setText(getIntent().getExtras().getString("spDistrict"));
    }
}
