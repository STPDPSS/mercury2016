package com.example.user.task1;

import android.app.Fragment;
import android.os.Bundle;

import java.util.List;

/**
 * Created by nika on 11/14/16.
 */

public class RetainedFragment extends Fragment {

  private Object o;
  private List<Element> elements;
  private Fragment f;

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