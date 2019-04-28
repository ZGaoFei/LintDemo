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

import com.example.lintdemo.view.TestView;
import com.example.lintdemo.view.ViewTest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnClick;
    private TextView tvShow;
    private TextView tvTest;

    private TestView testView;
    private TestView testView2;

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

        ViewTest viewTest = new ViewTest();
        viewTest.setName("hello world");
        String name = viewTest.getName();

        TestView testView = findViewById(R.id.test_view);
        TestView testView2 = new TestView(this);

        makeText();

    }

    private void init() {
        System.out.println("lint");

        System.out.println("lint is hahahah");

        String a = "lint is hahahaha";

        Toast.makeText(this, "test log", Toast.LENGTH_SHORT).show();

        Log.d("lint", "lint");

        Toast.makeText(this, "test toast", Toast.LENGTH_SHORT).show();

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

    private void makeText() {
        String b = "lint is a check code tools";

        ViewTest viewTest = new ViewTest();
        viewTest.setName("hello world");
        String name = viewTest.getName();
    }
}
