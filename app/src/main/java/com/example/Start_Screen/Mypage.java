package com.example.Start_Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.Start_Screen.R;

public class Mypage extends AppCompatActivity {

    private TextView tv_regisname;
    private ImageButton btn_back;
    private EditText et_name, et_student_id, et_nickname;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        btn_back = findViewById(R.id.btn_back);
        tv_regisname = findViewById(R.id.tv_regisname);
        et_name = findViewById(R.id.et_name);
        et_student_id = findViewById(R.id.et_student_id);
        et_nickname = findViewById(R.id.et_nickname);
        button3 = findViewById(R.id.button3);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");

        String greeting = userName + "님";
        tv_regisname.setText(greeting);
        tv_regisname.setGravity(Gravity.END);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mypage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.sungshin.ac.kr/sites/main_kor/main.jsp";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        et_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    String name = et_name.getText().toString().trim();
                    if (name.isEmpty()) {
                        tv_regisname.setText("");
                    } else {
                        String greeting = name + "님";
                        tv_regisname.setText(greeting);
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 저장된 정보가 있을 경우, EditText에 표시
        String name = getPreferences(MODE_PRIVATE).getString("name", "");
        String studentId = getPreferences(MODE_PRIVATE).getString("studentId", "");
        String nickname = getPreferences(MODE_PRIVATE).getString("nickname", "");

        et_name.setText(name);
        et_student_id.setText(studentId);
        et_nickname.setText(nickname);

        if (!name.isEmpty()) {
            String greeting = name + "님";
            tv_regisname.setText(greeting);
        } else {
            tv_regisname.setText("");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // 액티비티가 일시정지되면 입력한 정보 저장
        String name = et_name.getText().toString().trim();
        String studentId = et_student_id.getText().toString();
        String nickname = et_nickname.getText().toString();

        // 정보를 SharedPreferences에 저장
        getPreferences(MODE_PRIVATE)
                .edit()
                .putString("name", name)
                .putString("studentId", studentId)
                .putString("nickname", nickname)
                .apply();
    }
}
