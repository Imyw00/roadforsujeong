package com.example.Start_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class TodayRecom extends AppCompatActivity {

    private EditText etTitle;
    private EditText etContent;
    private rvAdapter adapter;
    private ArrayList<rvItem> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todayrecom);

        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new rvAdapter();
        recyclerView.setAdapter(adapter);

        // 기존 데이터를 어댑터에 설정
        adapter.setItems(itemList);

        // 사용자 입력을 받기 위해 EditText 참조 가져오기
        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);
    }

    // 사용자 입력 처리 메소드
    public void onAddButtonClicked(View view) {
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.icon3_foreground);

        // 새로운 글을 리스트에 추가
        adapter.addItem(drawable, title, content);

        // 입력 후 EditText 초기화
        etTitle.setText("");
        etContent.setText("");
    }
}
