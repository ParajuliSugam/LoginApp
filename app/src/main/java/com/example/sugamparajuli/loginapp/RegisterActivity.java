package com.example.sugamparajuli.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sugamparajuli.loginapp.utils.ShowToast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private EditText edtUserName, edtPassword, edtAddress, edtPhone, edtWebsite, edtEmail;
    private RadioGroup rgGender;
    private RadioButton rgGenderType;
    private String str, spSelected;
    private String etUsername, etPassword, etAddress, etPhone, etWebsite, etEmail;
    int selectedId;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Spinner spDistrict;
        edtWebsite = findViewById(R.id.et_website);
        edtEmail = findViewById(R.id.et_email_register);
        edtUserName = findViewById(R.id.et_username_register);
        edtAddress = findViewById(R.id.et_address_register);
        edtPhone = findViewById(R.id.et_phone_register);
        edtPassword = findViewById(R.id.et_password_register);
        btnRegister = findViewById(R.id.bt_register_button);
        rgGender = findViewById(R.id.rg_gender_group_register);
//        btnRegister.setEnabled(false);
//        edtUserName.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (charSequence.toString().equals("")) {
//                    btnRegister.setEnabled(false);
//                } else {
//                    btnRegister.setEnabled(true);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });

        spDistrict = findViewById(R.id.sp_dropdown_register);
        adapter = ArrayAdapter.createFromResource(this, R.array.sp_dropdown_list, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spDistrict.setAdapter(adapter);
        spDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Toast.makeText(getBaseContext(), "Please Select an Option.", Toast.LENGTH_LONG).show();
                    btnRegister.setEnabled(false);
                } else {
                    spSelected = adapterView.getItemAtPosition(i).toString();
                    btnRegister.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etWebsite = edtWebsite.getText().toString().trim();
                etEmail = edtEmail.getText().toString().trim();
                etUsername = edtUserName.getText().toString().trim();
                etPassword = edtPassword.getText().toString().trim();
                etAddress = edtAddress.getText().toString().trim();
                etPhone = edtPhone.getText().toString().trim();
                selectedId = rgGender.getCheckedRadioButtonId();
                rgGenderType = findViewById(selectedId);

                final boolean Check = validEmail(etEmail);

                if (Check) {

                    if (rgGenderType != null) {
                        str = rgGenderType.getText().toString();
                    }
                    if (TextUtils.isEmpty(etUsername)) {
//                    btnRegister.setEnabled(false);
                        ShowToast.showToast(RegisterActivity.this, "Username cannot be empty.", true);
                    } else if (TextUtils.isEmpty(etPassword)) {
                        ShowToast.showToast(RegisterActivity.this, "Password cannot be empty.", true);
                    } else if (TextUtils.isEmpty(etPhone)) {
                        ShowToast.showToast(RegisterActivity.this, "Phone Number cannot be empty.", true);
                    } else if (TextUtils.isEmpty(etAddress)) {
                        ShowToast.showToast(RegisterActivity.this, "Address cannot be empty.", true);
                    } else if (TextUtils.isEmpty(etEmail)) {
                        ShowToast.showToast(RegisterActivity.this, "E-mail address cannot be empty.", true);
                    } else if (TextUtils.isEmpty(etWebsite)) {
                        ShowToast.showToast(RegisterActivity.this, "Please enter Website.", true);
                    } else if (TextUtils.isEmpty(str)) {
                        ShowToast.showToast(RegisterActivity.this, "Please select Gender.", true);
                    } else {
                        getFormValue();
                    }
                }else{
                    ShowToast.showToast(RegisterActivity.this,"Please enter a valid email address",true);
                }
            }

            private boolean validEmail(String etEmail) {

                String regExpn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
                CharSequence inputStr = etEmail;
                Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(inputStr);
                if (matcher.matches())
                    return true;
                else
                    return false;
            }

            private void getFormValue() {
                Intent registerIntent = new Intent(RegisterActivity.this, RegisteredActivity.class);
                registerIntent.putExtra("RegisterUsernameFinal", edtUserName.getText().toString());
                registerIntent.putExtra("RegisterPasswordFinal", edtPassword.getText().toString());
                registerIntent.putExtra("RegisterAddressFinal", edtAddress.getText().toString());
                registerIntent.putExtra("RegisterPhoneFinal", edtPhone.getText().toString());
                registerIntent.putExtra("RegisterWebsiteFinal", edtWebsite.getText().toString());
                registerIntent.putExtra("RegisterEmailFinal", edtEmail.getText().toString());

                registerIntent.putExtra("RegisterRadioFinal", str);
                registerIntent.putExtra("spDistrict", spSelected);
                startActivity(registerIntent);
                finish();
            }

        });

    }
}