package com.example.Start_Screen;

import android.graphics.drawable.Drawable;

public class rvItem {
    private Drawable d;
    private String tv_1;
    private String tv_2;

    private String title; // 추가: 글 제목
    private String content; // 추가: 글 내용

    public Drawable getD() {        return d;    }

    public void setD(Drawable d) {        this.d = d;    }

    public String getTv_1() {        return tv_1;    }

    public void setTv_1(String tv_1) {        this.tv_1 = tv_1;    }

    public String getTv_2() {        return tv_2;    }

    public void setTv_2(String tv_2) {        this.tv_2 = tv_2;    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
