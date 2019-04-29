package com.example.lintdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lintaar.Test;
import com.example.lintdemo.view.TestView;

public class TwoActivity extends AppCompatActivity {
    private TestView testView1;

    private TestView testView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        initView();
    }

    private void initView() {
        testView1 = findViewById(R.id.test_view3);

        TestView testView = findViewById(R.id.test_view3);

        TestView testView2 = new TestView(this);

        testView3 = new TestView(this);
    }

    private void initData() {
        TestView testView = new TestView(this);
    }
}
