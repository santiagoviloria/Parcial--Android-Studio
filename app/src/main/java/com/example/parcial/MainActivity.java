package com.example.parcial;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;
    CheckBox checkBoxTerms;
    TextView textViewRemember;
    Button buttonLogin, buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        checkBoxTerms = findViewById(R.id.checkBoxTerms);
        textViewRemember = findViewById(R.id.textViewRemember);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);

        TextWatcher inputWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateForm();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        };

        editTextEmail.addTextChangedListener(inputWatcher);
        editTextPassword.addTextChangedListener(inputWatcher);
        checkBoxTerms.setOnCheckedChangeListener((buttonView, isChecked) -> validateForm());

        textViewRemember.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Recordar Contraseña", Toast.LENGTH_SHORT).show()
        );

        buttonLogin.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Ingresando al sistema", Toast.LENGTH_SHORT).show()
        );

        buttonRegister.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Proceso de Registro", Toast.LENGTH_SHORT).show()
        );
    }

    private void validateForm() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        boolean isTermsChecked = checkBoxTerms.isChecked();

        boolean isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        boolean isPasswordValid = password.length() >= 8;

        buttonLogin.setEnabled(isEmailValid && isPasswordValid && isTermsChecked);
    }
}
