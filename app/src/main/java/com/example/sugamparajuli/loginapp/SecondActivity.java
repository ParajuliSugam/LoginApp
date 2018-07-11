package com.example.sugamparajuli.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private Button Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Back = (Button)findViewById(R.id.bt_BackButton);

        TextView UserNameView = (TextView)findViewById(R.id.tv_UserNameSecond);
        UserNameView.setText(getIntent().getExtras().getString("UserNamePass"));

        TextView PasswordView = (TextView)findViewById(R.id.tv_PasswordSecond);
        PasswordView.setText(getIntent().getExtras().getString("PasswordPass"));

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(SecondActivity.this,MainActivity.class);
                startActivity(back);
            }
        });

    }
}
