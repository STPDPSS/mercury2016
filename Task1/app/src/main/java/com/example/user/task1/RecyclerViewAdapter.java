package com.example.user.task1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by veronika on 10/11/16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

  private List<Element> elements;

  public RecyclerViewAdapter(List<Element> elements) {
    this.elements = elements;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_element, parent, false);
    return new ViewHolder(v);
  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, int position) {
    Element e = elements.get(position);
    viewHolder.name.setText(e.getName());
  }

  @Override
  public int getItemCount() {
    return elements.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private ImageView icon;
    private Button remove;

    public ViewHolder(View itemView) {
      super(itemView);
      name = (TextView) itemView.findViewById(R.id.element_name);
      icon = (ImageView) itemView.findViewById(R.id.element_color);
      remove = (Button) itemView.findViewById(R.id.remove_element);

      itemView.setOnClickListener(new SelectListener());
      remove.setOnClickListener(new RemoveButtonListener());
    }

    private class SelectListener implements View.OnClickListener {

      private Element e;

      @Override
      public void onClick(View v) {
        selectDialog(v);
      }

      private void selectDialog(View v) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setMessage(R.string.select_element);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int id) {

          }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
      }
    }

    private class RemoveButtonListener implements View.OnClickListener {

      private Element e;

      @Override
      public void onClick(View v) {
        removeDialog(v);
      //  remove(e);
      }

      private void removeDialog(View v) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setMessage(R.string.remove_element);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int id) {
//            elements.remove(index);
//            adapter.notifyDataSetChanged();
//            listView.setSelection(0);
          }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int id) {

          }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
      }
    }

    private void remove(Element e) {
      int position = elements.indexOf(e);
      elements.remove(position);
      notifyItemRemoved(position);
    }
  }
}
