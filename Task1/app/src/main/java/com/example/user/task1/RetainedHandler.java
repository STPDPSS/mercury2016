package com.example.user.task1;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;

import java.util.List;

/**
 * Created by nika on 11/15/16.
 */

public class RetainedHandler extends Fragment {

  private Handler handler;
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
  }

  public void setData(Handler handler) {
    this.handler = handler;
  }

  public Handler getData() {
    return handler;
  }
}
