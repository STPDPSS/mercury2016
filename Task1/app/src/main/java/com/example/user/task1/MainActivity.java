package com.example.user.task1;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

  private RetainedFragment dataFragment;
  List<Element> elementList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.a_main);

    FragmentManager fm = getFragmentManager();
    dataFragment = (RetainedFragment) fm.findFragmentByTag("data");

    if (dataFragment == null) {
      elementList = new ArrayList<>();
      for (int i = 0; i < 50; i++) {
        elementList.add(new Element(
                "Элемент " + String.valueOf(i + 1), Element.Type.values()[i % 8]));
      }
      dataFragment = new RetainedFragment();
      fm.beginTransaction().add(dataFragment, "data").commit();
      dataFragment.setData(elementList);
    } else {
      elementList = dataFragment.getData();
    }
  }

  @Override
  protected void onStart() {
    super.onStart();

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    RecyclerViewAdapter adapter = new RecyclerViewAdapter(elementList);
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
        startActivity(new Intent(MainActivity.this, CreateElementActivity.class));
      }
    });
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    dataFragment.setData(elementList);
  }

}
