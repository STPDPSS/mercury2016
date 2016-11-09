package com.example.user.task1;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class StartActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTheme(R.style.StartTheme);
    setContentView(R.layout.a_start);
    Handler handler = new Handler();
    handler.postDelayed(openMainActivity, 2000);
  }

  private Runnable openMainActivity = new Runnable() {
    @Override
    public void run() {
      startActivity(new Intent(StartActivity.this, MainActivity.class));
    }
  };

  @Override
  protected void onStop() {
    super.onStop();
    this.finish();
  }
}
