package com.example.Start_Screen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class certification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 원하는 링크를 설정합니다.
                String url = "https://open.kakao.com/o/slmFB1Af";

                // 링크를 여는 인텐트를 생성합니다.
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                // 인텐트를 실행합니다.
                startActivity(intent);
            }
        });
    }
}