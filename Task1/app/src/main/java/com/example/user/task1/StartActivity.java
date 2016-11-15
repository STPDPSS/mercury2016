package com.example.user.task1;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartActivity extends AppCompatActivity {

  private RetainedHandler retainedHandler;
  Handler handler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTheme(R.style.StartTheme);
    setContentView(R.layout.a_start);

    FragmentManager fm = getFragmentManager();
    retainedHandler = (RetainedHandler) fm.findFragmentByTag("handler");

    if (retainedHandler == null) {
      handler = new Handler();
      handler.postDelayed(openMainActivity, 2000);
      retainedHandler = new RetainedHandler();
      fm.beginTransaction().add(retainedHandler, "handler").commit();
      retainedHandler.setData(handler);
    } else {
      handler = retainedHandler.getData();
    }
  }

  private Runnable openMainActivity = new Runnable() {
    @Override
    public void run() {
      startActivity(new Intent(StartActivity.this, MainActivity.class));
    }
  };
}
