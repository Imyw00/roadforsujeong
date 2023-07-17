// LoginActivity.java
package com.example.Start_Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Start_Screen.R;

public class LoginActivity extends AppCompatActivity {

    private EditText et_id, et_pass2;
    private Button btn_login, btn_register2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_id);
        et_pass2 = findViewById(R.id.et_pass2);
        btn_login = findViewById(R.id.btn_login);
        btn_register2 = findViewById(R.id.btn_register2);

        btn_register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = et_id.getText().toString();
                String userPass = et_pass2.getText().toString();

                // 로그인 성공시 사용자명을 포함한 환영 메시지 출력
                String welcomeMessage = userID + "님 환영합니다.";
                Toast.makeText(getApplicationContext(), welcomeMessage, Toast.LENGTH_LONG).show();

                // 로그인 성공시 MainActivity로 이동
                Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }
}
