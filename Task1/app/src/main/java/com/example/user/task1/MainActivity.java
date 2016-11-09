package com.example.user.task1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.a_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }


  @Override
  protected void onStart() {
    super.onStart();


    final ListView listView = (ListView) findViewById(R.id.list);
    final List<String> listOfElements = new ArrayList<>();
    final int countOfStartElement = 50;
    for (int i = 1; i <= countOfStartElement; i++) {
      listOfElements.add("Элемент " + String.valueOf(i));
    }
    final ArrayAdapter<String> adapter = new ArrayAdapter<String>
            (this, R.layout.i_element, listOfElements);
    listView.setAdapter(adapter);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        createItem(listView, adapter, listOfElements);
      }
    });

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view,
                              int position, long id) {
        chooseItem(position + 1);
      }
    });

    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
      @Override
      public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        removeItem(listView, adapter, listOfElements, i);
        return true;
      }
    });

  }

  private void chooseItem(int index) {
    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage("Вы выбрали элемент номер " + String.valueOf(index));
    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {

      }
    });
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  private void createItem(final ListView listView, final ArrayAdapter<String> adapter,
                          final List<String> listOfElements) {
    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage(R.string.createItem)
            .setTitle(R.string.createItem);
    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        listOfElements.add("Элемент " + String.valueOf(listOfElements.size() + 1));
        adapter.notifyDataSetChanged();
        listView.setSelection(adapter.getCount() - 1);
      }
    });
    builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {

      }
    });
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  private void pickColor() {

  }

  private void removeItem(final ListView listView, final ArrayAdapter<String> adapter,
                          final List<String> listOfElements, final int index) {
    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage(R.string.removeItem);
    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        listOfElements.remove(index);
        adapter.notifyDataSetChanged();
        listView.setSelection(0);
      }
    });
    builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {

      }
    });
    AlertDialog dialog = builder.create();
    dialog.show();
  }
}
