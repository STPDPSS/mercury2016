package com.example.user.task1;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;


public class CreateElementActivity extends AppCompatActivity {

  RetainedFragment dataFragment;
  final CharSequence colors[] =
          {"Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet", "Empty"};
  int globalColor;
  ImageView circle;
  ArrayList<String> names;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.a_create_element);

    Intent intent = getIntent();
    names = intent.getStringArrayListExtra("names");

    FragmentManager fm = getFragmentManager();
    dataFragment = (RetainedFragment) fm.findFragmentByTag("data");
    circle = (ImageView) findViewById(R.id.color);

    if (dataFragment == null) {
      globalColor = 0;
      dataFragment = new RetainedFragment();
      fm.beginTransaction().add(dataFragment, "data").commit();
      dataFragment.setData(globalColor);
    } else {
      globalColor = (int) dataFragment.getData();
      circle.setImageResource(iconResourceId(globalColor));
    }
  }

  @Override
  protected void onStart() {
    super.onStart();

    final EditText editText = (EditText) findViewById(R.id.editText);

    ChangeColorListener listener = new ChangeColorListener();
    listener.setColor(globalColor);
    circle.setOnClickListener(new ChangeColorListener());

    Button buttonCreate = (Button) findViewById(R.id.buttonCreate);
    Button buttonCancel = (Button) findViewById(R.id.buttonCancel);

    final Intent intent = new Intent();

    buttonCreate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String name = editText.getText().toString();
        if (!names.contains(name)) {
          intent.putExtra("name", name);
          intent.putExtra("color", String.valueOf(globalColor));
          setResult(RESULT_OK, intent);
          finish();
        } else {
          busyName(name);
        }
      }
    });

    buttonCancel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        setResult(RESULT_CANCELED, intent);
        finish();
      }
    });
  }

  public void busyName(String name) {
    Snackbar.make(this.getCurrentFocus(),
            "Элемент с именем \"" + name + "\" уже существует", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
  }

  private class ChangeColorListener implements View.OnClickListener {

    int localColor;

    @Override
    public void onClick(View view) {
      System.out.println("onClick: change i_color");
      changeColorDialog(view);
    }

    public void setColor(int i) {
      this.localColor = i;
    }

    private void changeColorDialog(final View v) {
      final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
      builder.setTitle(R.string.choose_color)
              .setSingleChoiceItems(colors, globalColor, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int item) {
                  localColor = item;
                }
              })
              .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                  globalColor = localColor;
                  dataFragment.setData(globalColor);
                  circle.setImageResource(iconResourceId(globalColor));
                  dialog.cancel();
                }
              })
              .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                  dialog.cancel();
                }
              });
      AlertDialog dialog = builder.create();
      dialog.show();
    }
  }

  public static int iconResourceId(int i) {
    switch (i) {
      case 0:
        return R.drawable.red_circle;
      case 1:
        return R.drawable.orange_circle;
      case 2:
        return R.drawable.yellow_circle;
      case 3:
        return R.drawable.green_circle;
      case 4:
        return R.drawable.blue_circle;
      case 5:
        return R.drawable.indigo_circle;
      case 6:
        return R.drawable.violet_circle;
      default:
        return R.drawable.empty_circle;
    }
  }
}
