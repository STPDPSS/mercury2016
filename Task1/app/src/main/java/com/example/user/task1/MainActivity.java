package com.example.user.task1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class MainActivity extends AppCompatActivity {

  private RetainedFragment elementListFragment;
  private RetainedFragment namesArrayFragment;
  List<Element> elementList;
  ArrayList<String> names;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.a_main);

    FragmentManager fm = getFragmentManager();
    elementListFragment = (RetainedFragment) fm.findFragmentByTag("elementList");
    namesArrayFragment = (RetainedFragment) fm.findFragmentByTag("names");

    if (elementListFragment == null) {
      elementList = new ArrayList<>();
      names = new ArrayList<>();

      for (int i = 0; i < 50; i++) {
        String name = "Элемент " + String.valueOf(i + 1);
        elementList.add(new Element(name, Element.Type.values()[i % 8]));
        names.add(name);
      }

      elementListFragment = new RetainedFragment();
      fm.beginTransaction().add(elementListFragment, "elementList").commit();
      elementListFragment.setData(elementList);

      namesArrayFragment = new RetainedFragment();
      fm.beginTransaction().add(namesArrayFragment, "names").commit();
      namesArrayFragment.setData(names);
    } else {
      elementList = (ArrayList<Element>) elementListFragment.getData();
      names = (ArrayList<String>) namesArrayFragment.getData();
    }
  }

  @Override
  protected void onStart() {
    super.onStart();

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    RecyclerViewAdapter adapter = new RecyclerViewAdapter(elementList, names);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);

    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(layoutManager);


    FloatingActionButton floatingActionButton =
            (FloatingActionButton) findViewById(R.id.floatingActionButton);

    floatingActionButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        addItem();
      }

      private void addItem() {
        Intent intent = new Intent(MainActivity.this, CreateElementActivity.class);
        intent.putStringArrayListExtra("names", names);
        startActivityForResult(intent, 0);
      }
    });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (resultCode == RESULT_OK) {
      String name = data.getStringExtra("name");
      String color = data.getStringExtra("color");
      if (!names.contains(name)) {
        elementList.add(new Element(name, Element.Type.values()[Integer.valueOf(color)]));
        names.add(name);
      }
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    elementListFragment.setData(elementList);
    namesArrayFragment.setData(names);
  }

}
