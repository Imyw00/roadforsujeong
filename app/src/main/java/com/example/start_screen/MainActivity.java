package com.example.Start_Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.Start_Screen.R;

public class MainActivity extends AppCompatActivity {

    private Button btn_move1;
    private Button btn_move2;
    private Button btn_move3;
    private Button btn_move4;
    private ImageButton btn_home;
    private ImageButton btn_mypage;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btn_move1 = findViewById(R.id.btn_move1);
        btn_move2 = findViewById(R.id.btn_move2);
        btn_move3 = findViewById(R.id.btn_move3);
        btn_move4 = findViewById(R.id.btn_move4);
        btn_home = findViewById(R.id.btn_home);
        btn_mypage = findViewById(R.id.btn_mypage);


        btn_move1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RealRevw.class);
                intent.putExtra("str", str);
                startActivity(intent);
            }
        });
        btn_move2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FoodMapActivity.class);
                startActivity(intent);
            }
        });

        btn_move3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TodayRecom.class);
                startActivity(intent);
            }
        });

        btn_move4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, certification.class);
                startActivity(intent);
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        btn_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Mypage.class);
                startActivity(intent);
            }
        });



    }
}