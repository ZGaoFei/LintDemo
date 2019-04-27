package com.example.lintdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnClick;
    private TextView tvShow;
    private TextView tvTest;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_main);
        initViews();
        init();
    }

    private void init() {
        System.out.println("lint");

        Toast.makeText(this, "lint", Toast.LENGTH_SHORT).show();

        Log.d("lint", "lint");

        new Message();
        Message.obtain();
        handler.obtainMessage();
        handler.sendEmptyMessage(1);
        getLayoutInflater().inflate(R.layout.time, (ViewGroup) findViewById(R.id.activity_main));
    }

    private void initViews() {
        btnClick = (Button) findViewById(R.id.One);
        btnClick.setOnClickListener(this);
        tvShow = (TextView) findViewById(R.id.Show);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.One:
                showText();
                break;
        }
    }

    private void showText() {

        LintTestBean lintTestBean = new LintTestBean("lint", "lint");
        tvShow.setText(lintTestBean.name + "\n" +
                lintTestBean.age + "\n");

    }
}
