package com.example.qlsvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {
    Button btnQL,btnWeb,btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        btnQL = findViewById(R.id.btnQL);
        btnLogout = findViewById(R.id.btnLogout);
        btnWeb = findViewById(R.id.btnWebsite);
        btnQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(MainMenu.this,"Bạn đã đăng xuất!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void openBrower(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://dainam.edu.vn/vi"));
        startActivity(intent);
    }
}