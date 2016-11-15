package com.example.user.task1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateElementActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.a_create_element);
  }

  @Override
  protected void onStart() {
    super.onStart();

    final EditText editText = (EditText) findViewById(R.id.editText);
    Button buttonCreate = (Button) findViewById(R.id.buttonCreate);
    Button buttonCancel = (Button) findViewById(R.id.buttonCancel);

    final Intent intent = new Intent();

    buttonCreate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        intent.putExtra("name", editText.getText().toString());
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
}
