package com.example.Start_Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.Start_Screen.R;

import java.util.ArrayList;
import java.util.List;

public class RealRevw extends AppCompatActivity {

    private ListView list_sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realrevw);

        list_sample=(ListView) findViewById(R.id.list_sample);

        List<String> data=new ArrayList<>();

        ArrayAdapter<String> adapter =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,data);
        list_sample.setAdapter(adapter);

        data.add("수정1");
        data.add("수정2");
        data.add("수정3");
        data.add("수정4");
        adapter.notifyDataSetChanged();
    }
}