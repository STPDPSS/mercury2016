package com.example.user.task1;

import android.app.Fragment;
import android.os.Bundle;

import java.util.List;

/**
 * Created by nika on 11/14/16.
 */

public class RetainedFragment extends Fragment {

  private List<Element> elements;

  // this method is only called once for this fragment
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
  }

  public void setData(List<Element> elements) {
    this.elements = elements;
  }

  public List<Element> getData() {
    return elements;
  }
}