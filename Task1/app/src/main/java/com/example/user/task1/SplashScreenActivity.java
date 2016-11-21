package com.example.user.task1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTheme(R.style.SplashScreenTheme);
    setContentView(R.layout.a_splash_screen);

    Handler handler = new Handler();
    handler.postDelayed(openMainActivity, 2000);
  }

  private Runnable openMainActivity = new Runnable() {
    @Override
    public void run() {
      startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
      finish();
    }
  };
}
