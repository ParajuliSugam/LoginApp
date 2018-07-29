package com.example.sugamparajuli.loginapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sugamparajuli.loginapp.utils.ShowToast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private TextInputLayout edtUserName, edtAddress, edtPhone, edtWebsite, edtEmail, edtPassword;
    private RadioGroup rgGender;
    private RadioButton rgGenderType;
    private String str, spSelected;
    private String etUsername, etPassword, etAddress, etPhone, etWebsite, etEmail;
    int selectedId;
    Spinner spDistrict;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().setBackgroundDrawableResource(R.drawable.background_image);


        edtWebsite = findViewById(R.id.et_website);
        edtEmail = findViewById(R.id.et_email_register);
        edtUserName = findViewById(R.id.et_username_register);
        edtAddress = findViewById(R.id.et_address_register);
        edtPhone = findViewById(R.id.et_phone_register);
        edtPassword = findViewById(R.id.et_password_register);
        btnRegister = findViewById(R.id.bt_register_button);
        rgGender = findViewById(R.id.rg_gender_group_register);


        spDistrict = findViewById(R.id.sp_dropdown_register);
        adapter = ArrayAdapter.createFromResource(this, R.array.sp_dropdown_list, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spDistrict.setAdapter(adapter);
        spDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
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


// sroll to top of hscrollViewMain
                ScrollView scrollView = findViewById(R.id.sv_register);
                scrollView.scrollTo(100, 100); // scroll to application top

                onPause();
                etWebsite = edtWebsite.getEditText().getText().toString().trim();
                etEmail = edtEmail.getEditText().getText().toString().trim();
                etUsername = edtUserName.getEditText().getText().toString().trim();
                etPassword = edtPassword.getEditText().getText().toString().trim();
                etAddress = edtAddress.getEditText().getText().toString().trim();
                etPhone = edtPhone.getEditText().getText().toString().trim();
                selectedId = rgGender.getCheckedRadioButtonId();
                rgGenderType = findViewById(selectedId);

                if (rgGenderType != null) {
                    str = rgGenderType.getText().toString();
                }

//                final boolean Check = validEmail(etEmail);

//                if (Check) {
                if (TextUtils.isEmpty(etUsername)) {
                    edtUserName.setError("Username is Empty.");
                } else if (TextUtils.isEmpty(etPassword)) {
                    edtPassword.setError("Password cannot be empty.");
                } else if (TextUtils.isEmpty(etPhone)) {
                    edtPhone.setError("Phone Number cannot be empty.");
                } else if (TextUtils.isEmpty(etAddress)) {
                    edtAddress.setError("Address cannot be blank.");
                } else if (TextUtils.isEmpty(etEmail)) {
                    edtEmail.setError("");
                } else if (TextUtils.isEmpty(etWebsite)) {
                    edtWebsite.setError("Website cannot be empty.");
                } else if (TextUtils.isEmpty(str)) {
                    ShowToast.showToast(RegisterActivity.this, "Please select Gender.", true);
                } else {
                    validate();
                }
            }

//            public void getFormValue() {
//                Intent registerIntent = new Intent(RegisterActivity.this, RegisteredActivity.class);
//                registerIntent.putExtra("RegisterUsernameFinal", edtUserName.getEditText().getText().toString());
//                registerIntent.putExtra("RegisterPasswordFinal", edtPassword.getEditText().getText().toString());
//                registerIntent.putExtra("RegisterAddressFinal", edtAddress.getEditText().getText().toString());
//                registerIntent.putExtra("RegisterPhoneFinal", edtPhone.getEditText().getText().toString());
//                registerIntent.putExtra("RegisterWebsiteFinal", edtWebsite.getEditText().getText().toString());
//                registerIntent.putExtra("RegisterEmailFinal", edtEmail.getEditText().getText().toString());
//
//                registerIntent.putExtra("RegisterRadioFinal", str);
//                registerIntent.putExtra("spDistrict", spSelected);
//                startActivity(registerIntent);
//                finish();
//            }

        });

    }

    private void validate() {
        if (etUsername.length() < 6) {
            edtUserName.setError("Please enter at least 6 characters.");
        } else {
            final boolean CheckUsername = UsernameValidator(etUsername);
            if (!CheckUsername) {
                edtUserName.setError("Please enter a valid username.");
            }
        }
        if (etPassword.length() < 6) {
            edtPassword.setError("Please enter at least 6 characters.");
        } else {
            final boolean CheckPassword = PasswordCheck(etPassword);
            if (!CheckPassword) {
                edtPassword.setError("Please enter at least 1 Upper case, 1 number.");
            }
        }
        final boolean CheckWebsite = WebsiteValidator(etWebsite);
        if (!CheckWebsite){
            edtWebsite.setError("Please enter a valid Website.");
        }

        final boolean CheckEmail = validEmail(etEmail);
        if (!CheckEmail) {
            edtEmail.setError("Please Enter a valid email address.");
        }
        if (edtUserName.getError()==null&&edtPassword.getError()==null&&edtEmail.getError()==null&&edtWebsite.getError()==null){
            getFormValue();
        }
    }

    private void getFormValue() {
        Intent registerIntent = new Intent(RegisterActivity.this, RegisteredActivity.class);
        registerIntent.putExtra("RegisterUsernameFinal", edtUserName.getEditText().getText().toString());
        registerIntent.putExtra("RegisterPasswordFinal", edtPassword.getEditText().getText().toString());
        registerIntent.putExtra("RegisterAddressFinal", edtAddress.getEditText().getText().toString());
        registerIntent.putExtra("RegisterPhoneFinal", edtPhone.getEditText().getText().toString());
        registerIntent.putExtra("RegisterWebsiteFinal", edtWebsite.getEditText().getText().toString());
        registerIntent.putExtra("RegisterEmailFinal", edtEmail.getEditText().getText().toString());

        registerIntent.putExtra("RegisterRadioFinal", str);
        registerIntent.putExtra("spDistrict", spSelected);
        startActivity(registerIntent);
        finish();
    }

    private boolean PasswordCheck(String etPassword) {
        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
        CharSequence password = etPassword;

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        if (matcher.matches())
            return true;
        else
            return false;
    }

    protected void onPause() {
        super.onPause();
        TextInputLayout editTextEmail = edtEmail;
        editTextEmail.setError(null);
        TextInputLayout editTextAddress = edtAddress;
        editTextAddress.setError(null);
        TextInputLayout editTextPassword = edtPassword;
        editTextPassword.setError(null);
        TextInputLayout editTextPhone = edtPhone;
        editTextPhone.setError(null);
        TextInputLayout editTextUsername = edtUserName;
        editTextUsername.setError(null);
        TextInputLayout editTextWebsite = edtWebsite;
        editTextWebsite.setError(null);
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

    public boolean UsernameValidator(String usernameCheck) {

        String regExpn = "^[a-z0-9_-]{6,20}$";
        CharSequence inputStr = usernameCheck;
        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
            return true;
        else
            return false;
    }

    public boolean WebsiteValidator(String websiteCheck) {

        String regExpn = "^((ftp|http|https):\\/\\/)?(www.)" +
                "?(?!.*(ftp|http|https|www.))[a-zA-Z0-9_-]+" +
                "(\\.[a-zA-Z]+)+((\\/)[\\w#]+)" +
                "*(\\/\\w+\\?[a-zA-Z0-9_]+=\\w+(" +
                "&[a-zA-Z0-9_]+=\\w+)*)?$";
        CharSequence inputStr = websiteCheck;
        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
            return true;
        else
            return false;
    }
}