package com.juliarman.tugas1_juliarmanumar_152267;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class FormPendaftaran extends AppCompatActivity implements View.OnClickListener {

    EditText edtNim, edtMhs, edtEmail, edtTlp;
    RadioGroup rgJkl;
    RadioButton rbMale, rbFemale;
    CheckBox cbGoogling, cbReading, cbMusic;
    Button btnSave, btnClear;

    String txtjkl;
    String  cbChoose = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pendaftaran);

        setTitle("Form Pendaftaran");

        edtNim = findViewById(R.id.edt_nim);
        edtMhs = findViewById(R.id.edt_mhs);
        edtEmail = findViewById(R.id.edt_email);
        edtTlp = findViewById(R.id.edt_phone);
        rgJkl = findViewById(R.id.rg_jkl);
        rbMale = findViewById(R.id.rb_male);
        rbFemale = findViewById(R.id.rb_female);
        cbGoogling = findViewById(R.id.cb_googling);
        cbReading = findViewById(R.id.cb_reading);
        cbMusic = findViewById(R.id.cb_music);
        btnSave = findViewById(R.id.btn_save);
        btnClear = findViewById(R.id.btn_clear);

        btnSave.setOnClickListener(this);
        btnClear.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_save:

                String txtNim = edtNim.getText().toString();
                String txtMhs = edtMhs.getText().toString();
                String txtEmail = edtEmail.getText().toString();
                String txtTelp = edtTlp.getText().toString();

               int rgChooseJkl = rgJkl.getCheckedRadioButtonId();

               if (rgChooseJkl == R.id.rb_male){

                   txtjkl = rbMale.getText().toString();

               } else if (rgChooseJkl == R.id.rb_female){

                   txtjkl = rbFemale.getText().toString();
               }

             if (cbGoogling.isChecked()){
                 cbChoose += cbGoogling.getText().toString();
             } else{

             }

             if (cbReading.isChecked()){
                 cbChoose += cbReading.getText().toString();
             }
             else {

             }

             if (cbMusic.isChecked()){
                 cbChoose += cbMusic.getText().toString();
             } else {

             }
                Toast.makeText(this, "NIM: "+txtNim+ "\nNama: "+txtMhs+"\nEmail: "+txtEmail+"\nTelpon: "+txtTelp+"\nJenis Kelamin: "+txtjkl+"\nHobby: "+cbChoose,  Toast.LENGTH_SHORT).show();

             break;


            case R.id.btn_clear:
                clear();
                break;
        }
    }

    public void clear(){
        edtNim.setText("");
        edtMhs.setText("");
        edtEmail.setText("");
        edtTlp.setText("");
        rgJkl.clearCheck();
        cbGoogling.setChecked(false);
        cbReading.setChecked(false);
        cbMusic.setChecked(false);
    }
}