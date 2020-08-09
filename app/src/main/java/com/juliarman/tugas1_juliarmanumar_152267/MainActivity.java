package com.juliarman.tugas1_juliarmanumar_152267;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("152267 - Form Login");
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin    = findViewById(R.id.btn_login);
        edtUsername.setText("juliarman");
        edtPassword.setText("152267");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user1 = edtUsername.getText().toString();
                String pass1 = edtPassword.getText().toString();

                edtUsername.getText();
                edtPassword.getText();

                if (user1.matches("juliarman") || pass1.matches("152267")){

                    Toast.makeText(getApplicationContext(), "Selamat anda berhasil login", Toast.LENGTH_SHORT).show();
                    Intent moveActivity = new Intent(MainActivity.this, FormPendaftaran.class);
                    startActivity(moveActivity);

                } else {

                    Toast.makeText(getApplicationContext(),"Username atau Password tidak Sesuai", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}