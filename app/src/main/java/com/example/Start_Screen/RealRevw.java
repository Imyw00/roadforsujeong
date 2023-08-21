package com.example.Start_Screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RealRevw extends AppCompatActivity {

    private Spinner categorySpinner;
    private RecyclerView reviewRecyclerView;
    private ReviewAdapter reviewAdapter;
    private Map<String, List<String>> reviewMap = new HashMap<>(); // 카테고리별 리뷰 데이터 저장
    private Button submitReviewButton; // 작성 양식을 없애고 버튼만 사용
    private String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realrevw);

        // 레이아웃 요소 초기화
        categorySpinner = findViewById(R.id.categorySpinner);
        reviewRecyclerView = findViewById(R.id.reviewRecyclerView);
        submitReviewButton = findViewById(R.id.submitReviewButton); // 작성 양식을 없애고 버튼만 사용

        // 스피너에 카테고리 목록 설정
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        // 리사이클러뷰 설정
        reviewAdapter = new ReviewAdapter();
        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewRecyclerView.setAdapter(reviewAdapter);

        // 리사이클러뷰 클릭 이벤트 처리
        reviewRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, reviewRecyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String selectedReview = reviewAdapter.getReviewData().get(position);
                        // TODO: 선택된 후기에 대한 처리 추가 (예: 상세 화면으로 이동)
                    }
                }));

        // 스피너 아이템 선택 시 리스너 설정
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = parent.getItemAtPosition(position).toString();
                updateRecyclerView(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 아무 동작 필요 없음
            }
        });

        // 등록 버튼 클릭 리스너 설정
        submitReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWriteReviewActivity(selectedCategory);
            }
        });
    }

    private void updateRecyclerView(String selectedCategory) {
        List<String> reviewData = reviewMap.get(selectedCategory);
        if (reviewData == null) {
            reviewData = new ArrayList<>();
            reviewMap.put(selectedCategory, reviewData);
        }
        reviewAdapter.setReviewData(reviewData);
    }

    public void startWriteReviewActivity(String selectedCategory) {
        Intent intent = new Intent(this, WriteReviewActivity.class);
        intent.putExtra("selectedCategory", selectedCategory);
        startActivityForResult(intent, REQUEST_WRITE_REVIEW);
    }

    private static final int REQUEST_WRITE_REVIEW = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_WRITE_REVIEW && resultCode == RESULT_OK) {
            String newReview = data.getStringExtra("newReview");
            if (newReview != null && !newReview.isEmpty()) {
                String selectedCategory = data.getStringExtra("selectedCategory");
                List<String> reviewData = reviewMap.get(selectedCategory);
                if (reviewData == null) {
                    reviewData = new ArrayList<>();
                    reviewMap.put(selectedCategory, reviewData);
                }
                reviewData.add(newReview);
                updateRecyclerView(selectedCategory);
            }
        }
    }
}
