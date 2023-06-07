package com.example.qlsvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edtUserName, edtPassword;
    CheckBox chkRemember;
    Button btnLogin, bthHuy;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapping();
        addAction();
        loadThongTin();
    }
    private void loadThongTin() {
        edtUserName.setText(sharedPreferences.getString("username",""));
        edtPassword.setText(sharedPreferences.getString("password",""));
        chkRemember.setChecked(sharedPreferences.getBoolean("checked",false));
    }

    private void addAction() {
        bthHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        String username = edtUserName.getText().toString();
        String password = edtPassword.getText().toString();
        if(username.equals("admin") && password.equals("123456")){
            Toast.makeText(LoginActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this,MainMenu.class);
            startActivity(intent);
            edtUserName.setText("");
            edtPassword.setText("");
            if(chkRemember.isChecked()){
                luuThongTin(username,password);
            }else {
                xoaThongTin();
            }
        }else {
            Toast.makeText(LoginActivity.this,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
        }
    }

    private void xoaThongTin() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("username");
        editor.remove("password");
        editor.remove("checked");
        editor.commit();
    }

    private void luuThongTin(String username, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.putBoolean("checked",true);
    }

    private void mapping() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassWord);
        chkRemember = findViewById(R.id.chkRemember);
        btnLogin = findViewById(R.id.btnLogin);
        bthHuy = findViewById(R.id.btnHuy);
        sharedPreferences = getSharedPreferences("data_login",MODE_PRIVATE);
    }
}