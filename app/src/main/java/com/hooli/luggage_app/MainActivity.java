package com.hooli.luggage_app;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.hooli.luggage_app.activity.HomeActivity;

import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private ImageView iv_welcomelogo;
    private ImageView iv_welcomeword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_welcome);
        iv_welcomelogo = findViewById(R.id.iv_welcomelogo);
        iv_welcomeword = findViewById(R.id.iv_welcomeword);
        iv_welcomelogo.setAnimation(AnimationUtils.loadAnimation(this, R.anim.welcome_logo));
        iv_welcomeword.setAnimation(AnimationUtils.loadAnimation(this, R.anim.welcome_logo));
        handler.sendEmptyMessageDelayed(0, 1000);
        //setContentView(R.layout.activity_main);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            //执行一次后销毁本页面
            finish();
            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        }
    };
}
