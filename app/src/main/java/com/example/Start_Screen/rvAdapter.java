package com.example.Start_Screen;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class rvAdapter extends RecyclerView.Adapter<rvAdapter.ViewHolder> {
    private ArrayList<rvItem> itemList = new ArrayList<>();

    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView titleTextView;
        private TextView contentTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv);
            titleTextView = itemView.findViewById(R.id.tv_name);
            contentTextView = itemView.findViewById(R.id.tv_text);
        }
    }

    @NonNull
    @Override
    public rvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rvAdapter.ViewHolder holder, int position) {
        rvItem item = itemList.get(position);
        holder.imageView.setImageDrawable(item.getD());
        holder.titleTextView.setText(item.getTitle());
        holder.contentTextView.setText(item.getContent());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // 데이터 추가를 위한 메소드
    public void addItem(Drawable d, String title, String content) {
        rvItem item = new rvItem();
        item.setD(d);
        item.setTitle(title);
        item.setContent(content);
        itemList.add(item);
        notifyDataSetChanged(); // 데이터 변경을 어댑터에 알려서 화면을 갱신
    }

    // 데이터 리스트를 한 번에 설정하는 메소드
    public void setItems(ArrayList<rvItem> items) {
        itemList = items;
        notifyDataSetChanged();
    }
}

