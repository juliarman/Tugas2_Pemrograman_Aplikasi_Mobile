package com.juliarman.tugas1_juliarmanumar_152267;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.PrintWriter;
import java.util.Scanner;

public class FormPendaftaran extends AppCompatActivity implements View.OnClickListener {

    private static final int PERMISSION_REQUEST_CODE = 1 ;
    AlertDialog dialog;
    String fileName;
    TextView tvDataView;
    EditText edtNim, edtMhs, edtEmail, edtTlp;
    RadioGroup rgJkl;
    RadioButton rbMale, rbFemale;
    CheckBox cbGoogling, cbReading, cbMusic;
    Button btnSave, btnClear, btnView;

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
        btnView = findViewById(R.id.btn_view);


        if (Build.VERSION.SDK_INT >= 23){

            if (checkPermission())
            {

            }else {

                requestPermissions();
            }
        }

        btnSave.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnView.setOnClickListener(this);

    }

    private void requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(FormPendaftaran.this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(FormPendaftaran.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.",
            Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(FormPendaftaran.this, new
                            String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
         switch (requestCode){

             case  PERMISSION_REQUEST_CODE:
                 if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                     Log.e("Value", "Permission Granted, Now you can use local drive");
                 }else {
                     Log.e("Value", "Permission Denied, you cannot use local drive.");
                 }
                 break;
         }
    }

    private boolean checkPermission() {

        int result = ContextCompat.checkSelfPermission(FormPendaftaran.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED){
            return  true;
        }else {
            return false;
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_save:
                save();
                //Toast.makeText(this, "NIM: "+txtNim+ "\nNama: "+txtMhs+"\nEmail: "+txtEmail+"\nTelpon: "+txtTelp+"\nJenis Kelamin: "+txtjkl+"\nHobby: "+cbChoose,  Toast.LENGTH_SHORT).show();
             break;

            case R.id.btn_view:
                view();
                break;

            case R.id.btn_clear:
                clear();
                break;
        }
    }


    public void save(){

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


        fileName = Environment.getExternalStorageDirectory()+"/juliarman_152267.txt";
        dialog = new AlertDialog.Builder(this).create();

        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(fileName);

            printWriter.println(txtNim);
            printWriter.println(txtMhs);
            printWriter.println(txtEmail);
            printWriter.println(txtTelp);
            printWriter.println(txtjkl);
            printWriter.println(cbChoose);

            printWriter.close();

            dialog.setMessage("Sukses Menyimpan Data ke File");
            dialog.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
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

    public void view(){

         setContentView(R.layout.activity_view);

         tvDataView = findViewById(R.id.tv_data_view);


         File fileInput = new File(fileName);
         Scanner scanner;
         StringBuilder stringBuilder = new StringBuilder();
         try {
             scanner = new Scanner(fileInput);
             String strBaris;
             while (scanner.hasNextLine()){
                 strBaris = scanner.nextLine();
                 stringBuilder.append("\n");
                 stringBuilder.append(strBaris);
             }
             scanner.close();

               tvDataView.setText(stringBuilder.toString());


           } catch (Exception e) {
             e.printStackTrace();
        }
    }
}