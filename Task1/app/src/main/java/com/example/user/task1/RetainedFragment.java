package com.example.user.task1;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by nika on 11/14/16.
 */

public class RetainedFragment extends Fragment {

  private Object o;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
  }

  public void setData(Object o) {
    this.o = o;
  }

  public Object getData() {
    return o;
  }
}