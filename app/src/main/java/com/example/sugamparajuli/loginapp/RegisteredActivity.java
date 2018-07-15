package com.example.sugamparajuli.loginapp;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.service.chooser.ChooserTarget;
import android.service.chooser.ChooserTargetService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegisteredActivity extends AppCompatActivity {

    private String tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        TextView userNameViewTop = findViewById(R.id.tv_view_username_top);
        TextView tvUsernameView = findViewById(R.id.tv_view_username);
        TextView tvPasswordView = findViewById(R.id.tv_view_password);
        TextView tvPhoneView = findViewById(R.id.tv_view_phone);
        TextView tvAddressView = findViewById(R.id.tv_view_address);
        TextView tvEmailView = findViewById(R.id.tv_view_email);
        TextView tvGenderView = findViewById(R.id.tv_view_gender);
        TextView spDistrict = findViewById(R.id.sp_dropdown_view);
        TextView tvWebsite = findViewById(R.id.tv_view_website);
        tvEmail = tvEmailView.getText().toString().trim();
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            Gender = extras.getString("radioChosen");
//
//        }
        userNameViewTop.setText((getIntent().getExtras().getString("RegisterUsernameFinal")));
        tvUsernameView.setText((getIntent().getExtras()).getString("RegisterUsernameFinal"));
        tvPasswordView.setText(getIntent().getExtras().getString("RegisterPasswordFinal"));
        tvPhoneView.setText(getIntent().getExtras().getString("RegisterPhoneFinal"));
        tvAddressView.setText(getIntent().getExtras().getString("RegisterAddressFinal"));
        tvGenderView.setText(getIntent().getExtras().getString("RegisterRadioFinal"));
        spDistrict.setText(getIntent().getExtras().getString("spDistrict"));
        tvWebsite.setText(getIntent().getExtras().getString("RegisterWebsiteFinal"));
        tvEmailView.setText(getIntent().getExtras().getString("RegisterEmailFinal"));
    }
    public void ImplicitIntent(View view){
        Intent chooser = null;
        Intent intent= null;
        if (view.getId()==R.id.tv_view_address){
            intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:27.700769,85.300140"));
            chooser =Intent.createChooser(intent,"Launch Maps");
            startActivity(intent);
        }
        else if (view.getId()==R.id.tv_view_phone){
            intent=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+getIntent().getExtras().getString("RegisterPhoneFinal")));
            startActivity(intent);
        }
        else if (view.getId()==R.id.tv_view_email){
            intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL,tvEmail);
            intent.setType("message/rfc822");
            chooser = Intent.createChooser(intent,"Send Email");
            startActivity(chooser);

        }
    }
}
