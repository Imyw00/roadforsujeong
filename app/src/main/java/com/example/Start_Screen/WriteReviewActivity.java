package com.example.Start_Screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class WriteReviewActivity extends AppCompatActivity {

    private EditText writeEditText;
    private Button submitReviewButton;
    private ImageButton backButton;
    private String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        // 레이아웃 요소 초기화
        writeEditText = findViewById(R.id.reviewEditText);
        submitReviewButton = findViewById(R.id.submitReviewButton);
        backButton = findViewById(R.id.backButton);

        // 선택된 카테고리 정보 가져오기
        Intent intent = getIntent();
        if (intent.hasExtra("selectedCategory")) {
            selectedCategory = intent.getStringExtra("selectedCategory");
        }
        // 뒤로가기 버튼 클릭 리스너 설정
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 액티비티 종료
            }
        });

        // 등록 버튼 클릭 리스너 설정
        submitReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reviewText = writeEditText.getText().toString();
                if (!reviewText.isEmpty()) {
                    // 작성한 후기를 MainActivity로 전달
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("newReview", reviewText);
                    resultIntent.putExtra("selectedCategory", selectedCategory);
                    setResult(RESULT_OK, resultIntent);

                    // EditText 초기화
                    writeEditText.setText(""); // EditText 초기화하기

                    finish(); // 액티비티 종료
                }
            }
        });
    }
}
