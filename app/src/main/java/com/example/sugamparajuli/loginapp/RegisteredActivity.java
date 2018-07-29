package com.example.sugamparajuli.loginapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class RegisteredActivity extends AppCompatActivity {
    static final Integer CALL = 1;
    private String tvEmail, tvWebsite, tvPhone;
    private int phone;

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
        TextView tvWebsiteView = findViewById(R.id.tv_view_website);


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
        tvWebsiteView.setText(getIntent().getExtras().getString("RegisterWebsiteFinal"));
        tvEmailView.setText(getIntent().getExtras().getString("RegisterEmailFinal"));

        tvEmail = tvEmailView.getText().toString().trim();
        tvWebsite = tvWebsiteView.getText().toString().trim();
        tvPhone = tvPhoneView.getText().toString();
//        phone = Integer.valueOf(tvPhone);
    }

    public void ImplicitIntent(View view) {
        Intent chooser = null;
        Intent intent = null;
        if (view.getId() == R.id.tv_view_address) {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:27.700769,85.300140"));
            chooser = Intent.createChooser(intent, "Launch Maps");
            startActivity(chooser);
        } else if (view.getId() == R.id.tv_view_phone) {

            AskForPermission(Manifest.permission.CALL_PHONE, CALL);

//            intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getIntent().getExtras().getString("RegisterPhoneFinal")));
//            startActivity(intent);
        } else if (view.getId() == R.id.tv_view_email) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", tvEmail, null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        } else if (view.getId() == R.id.tv_view_website) {
                String url = "https://" + tvWebsite;
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                chooser = Intent.createChooser(intent, "Open Website Using..");
                startActivity(chooser);

        }
    }

    private void AskForPermission(final String permission, final Integer requestCode) {
        int i=0;
        if (ContextCompat.checkSelfPermission(RegisteredActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            i++;
            if (ActivityCompat.shouldShowRequestPermissionRationale(RegisteredActivity.this, permission)) {
                if (i==1) {

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(RegisteredActivity.this);
                    builder1.setTitle("Permission Required").setMessage("Please accept the permission to place a call.");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    ActivityCompat.requestPermissions(RegisteredActivity.this, new String[]{permission}, requestCode);
                                }
                            });

                    builder1.setNegativeButton(
                            "Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }else {
                    //This is called if user has denied the permission before
                    //In this case I am just asking the permission again
                    ActivityCompat.requestPermissions(RegisteredActivity.this, new String[]{permission}, requestCode);
                }

            } else {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(RegisteredActivity.this);
                builder1.setTitle("Permission Required").setMessage("Please accept the permission manually to place a call.");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Settings",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent();
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivity(intent);
                            }
                        });

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

//                ActivityCompat.requestPermissions(RegisteredActivity.this, new String[]{permission}, requestCode);
            }
        } else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + tvPhone));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                startActivity(callIntent);
            }
        }
    }
}