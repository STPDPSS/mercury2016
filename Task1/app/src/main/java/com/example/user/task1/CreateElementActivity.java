package com.example.user.task1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;




public class CreateElementActivity extends AppCompatActivity {

  final CharSequence colors[] =
          {"Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet", "Empty"};
  int globalColor = 0;
  ImageView circle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.a_create_element);
  }

  @Override
  protected void onStart() {
    super.onStart();

    final EditText editText = (EditText) findViewById(R.id.editText);

    circle = (ImageView) findViewById(R.id.color);
    ChangeColorListener listener = new ChangeColorListener();
    listener.setColor(globalColor);
    circle.setOnClickListener(new ChangeColorListener());


    Button buttonCreate = (Button) findViewById(R.id.buttonCreate);
    Button buttonCancel = (Button) findViewById(R.id.buttonCancel);

    final Intent intent = new Intent();

    buttonCreate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        intent.putExtra("name", editText.getText().toString());
        intent.putExtra("color", String.valueOf(globalColor));
        setResult(RESULT_OK, intent);
        finish();
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
                  int iconResourceId;
                  switch (globalColor) {
                    case 0:
                      iconResourceId = R.drawable.red_circle;
                      break;
                    case 1:
                      iconResourceId = R.drawable.orange_circle;
                      break;
                    case 2:
                      iconResourceId = R.drawable.yellow_circle;
                      break;
                    case 3:
                      iconResourceId = R.drawable.green_circle;
                      break;
                    case 4:
                      iconResourceId = R.drawable.blue_circle;
                      break;
                    case 5:
                      iconResourceId = R.drawable.indigo_circle;
                      break;
                    case 6:
                      iconResourceId = R.drawable.violet_circle;
                      break;
                    default:
                      iconResourceId = R.drawable.empty_circle;
                      break;
                  }
                  circle.setImageResource(iconResourceId);
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

}
