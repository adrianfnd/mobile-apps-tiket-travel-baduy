package com.example.uasmobiletikettravelbaduy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uasmobiletikettravelbaduy.R;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin;

    private static final Map<String, String> VALID_CREDENTIALS = new HashMap<>();
    static {
        VALID_CREDENTIALS.put("putriadinda73778@gmail.com", "12345678");
        VALID_CREDENTIALS.put("devitaeviliana25@gmail.com", "12345678");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (isValidCredentials(email, password)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Email atau password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidCredentials(String email, String password) {
        return VALID_CREDENTIALS.containsKey(email) &&
                VALID_CREDENTIALS.get(email).equals(password);
    }
}
