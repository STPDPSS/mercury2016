package com.example.user.task1;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartActivity extends AppCompatActivity {

  private RetainedFragment retainedFragment;
  Handler handler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTheme(R.style.StartTheme);
    setContentView(R.layout.a_start);

    FragmentManager fm = getFragmentManager();
    retainedFragment = (RetainedFragment) fm.findFragmentByTag("handler");

    if (retainedFragment == null) {
      handler = new Handler();
      handler.postDelayed(openMainActivity, 2000);
      retainedFragment = new RetainedFragment();
      fm.beginTransaction().add(retainedFragment, "handler").commit();
      retainedFragment.setData(handler);
    } else {
      handler = (Handler) retainedFragment.getData();
    }
  }

  private Runnable openMainActivity = new Runnable() {
    @Override
    public void run() {
      startActivity(new Intent(StartActivity.this, MainActivity.class));
    }
  };
}
