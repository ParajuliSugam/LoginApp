package com.example.sugamparajuli.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private Button RegisterButton;
    private EditText RegisterUsername, RegisterPassword, RegisterAddress, RegisterPhone;
    private RadioGroup rgGender;
    private RadioButton rgGenderType;
    public String str, selected;
    public boolean checked;
    private Spinner spDistrict;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RegisterUsername = (EditText) findViewById(R.id.et_UsernameRegister);
        RegisterAddress = (EditText) findViewById(R.id.et_AddressRegister);
        RegisterPhone = (EditText) findViewById(R.id.et_PhoneRegister);
        RegisterPassword = (EditText) findViewById(R.id.et_PasswordRegister);
        RegisterButton = (Button) findViewById(R.id.bt_RegisterButton);
        rgGender = findViewById(R.id.rg_Gender);

        spDistrict = (Spinner) findViewById(R.id.sp_dropdown);
        adapter = ArrayAdapter.createFromResource(this, R.array.sp_dropdown_list, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spDistrict.setAdapter(adapter);
        spDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Toast.makeText(getBaseContext(), "Please Select an Option.", Toast.LENGTH_LONG).show();
                    RegisterButton.setEnabled(false);
                } else {
                    selected = adapterView.getItemAtPosition(i).toString();
                    RegisterButton.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(RegisterActivity.this, RegisteredActivity.class);
                registerIntent.putExtra("RegisterUsernameFinal", RegisterUsername.getText().toString());
                registerIntent.putExtra("RegisterPasswordFinal", RegisterPassword.getText().toString());
                registerIntent.putExtra("RegisterAddressFinal", RegisterAddress.getText().toString());
                registerIntent.putExtra("RegisterPhoneFinal", RegisterPhone.getText().toString());
                int selectedId = rgGender.getCheckedRadioButtonId();
                rgGenderType = findViewById(selectedId);
                if (rgGenderType != null) {
                    str = rgGenderType.getText().toString();
                }

                registerIntent.putExtra("RegisterRadioFinal", str);
                registerIntent.putExtra("spDistrict", selected);
                startActivity(registerIntent);
            }

        });

    }
}