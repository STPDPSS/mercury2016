package com.example.user.task1;

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

  List<ListItem> listItems = new ArrayList<>();

  RecyclerView recyclerView;
  LinearLayoutManager layoutManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.a_main);

    for (int i = 0; i < 50; i++) {
      String name = String.format(getString(R.string.list_item_default_name), i + 1);
      listItems.add(new ListItem(name, ListItem.Color.values()[i % 8]));
    }

    recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    RecyclerViewAdapter adapter = new RecyclerViewAdapter(listItems);
    layoutManager = new LinearLayoutManager(this);

    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(layoutManager);

    FloatingActionButton newListItemButton =
            (FloatingActionButton) findViewById(R.id.floatingActionButton);

    newListItemButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        ArrayList<String> names = new ArrayList<>();
        for (ListItem item : listItems) {
          names.add(item.getName());
        }
        Intent intent = new Intent(MainActivity.this, AddListItemActivity.class);
        intent.putStringArrayListExtra("names", names);
        startActivityForResult(intent, 0);
      }
    });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == 0 && resultCode == RESULT_OK) {
      String name = data.getStringExtra("name");
      ListItem.Color color = (ListItem.Color) data.getSerializableExtra("color");
      listItems.add(new ListItem(name, color));
      layoutManager.scrollToPosition(listItems.size() - 1);
    }
  }
}
