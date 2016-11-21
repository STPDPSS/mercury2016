package com.example.user.task1;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;


public class AddListItemActivity extends AppCompatActivity {

  ListItem.Color iconColor = ListItem.Color.RED;

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(R.menu.add_list_item, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.create_element_button:
        EditText editText = (EditText) findViewById(R.id.editText);
        String name = editText.getText().toString();
        ArrayList<String> existingNames = getIntent().getStringArrayListExtra("names");
        if (!existingNames.contains(name)) {
          Intent intent = getIntent();
          intent.putExtra("name", name);
          intent.putExtra("color", iconColor);
          setResult(RESULT_OK, intent);
          finish();
        } else {
          Snackbar.make(
              getCurrentFocus(),
              String.format(getString(R.string.list_item_already_exists), name),
              Snackbar.LENGTH_LONG).show();
        }
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.a_add_list_item);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    ImageView iconImageView = (ImageView) findViewById(R.id.color);

    iconImageView.setOnClickListener(new ChangeColorListener());
    iconImageView.setImageResource(iconColor.iconId);
  }

  private class ChangeColorListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {
      AlertDialog dialog = ListItem.createChangeColorDialog(
          view.getContext(),
          iconColor,
          new ListItem.ColorChangeCallBack() {
            @Override
            public void colorChange(ListItem.Color color) {
              iconColor = color;
              ImageView iconImageView = (ImageView) findViewById(R.id.color);
              iconImageView.setImageResource(iconColor.iconId);
            }
          });
      dialog.show();
      Log.i("SplashScreen", "onClick: change i_color");
    }
  }
}
