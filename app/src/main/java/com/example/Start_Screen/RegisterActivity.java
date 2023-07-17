// RegisterActivity.java
package com.example.Start_Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Start_Screen.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_id, et_pass, et_name, et_age;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);

        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();
                String userName = et_name.getText().toString();
                String userAge = et_age.getText().toString();

                // 회원 등록에 성공하였습니다. 메시지 출력
                Toast.makeText(getApplicationContext(), "회원 등록에 성공하였습니다.", Toast.LENGTH_SHORT).show();

                // Mypage로 전달할 데이터 설정
                Intent mypageIntent = new Intent(RegisterActivity.this, Mypage.class);
                mypageIntent.putExtra("userName", userName);
                startActivity(mypageIntent);

                // LoginActivity로 이동
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);

                finish(); // RegisterActivity를 종료하여 뒤로가기로 돌아오면 회원가입 화면이 나오지 않도록 함
            }
        });
    }
}

