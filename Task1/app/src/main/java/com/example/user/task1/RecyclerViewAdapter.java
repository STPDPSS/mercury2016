package com.example.user.task1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.admin.SystemUpdatePolicy;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
    int iconResourceId = R.drawable.empty_circle;
    switch (e.getType()) {
      case RED:
        iconResourceId = R.drawable.red_circle;
        break;
      case ORANGE:
        iconResourceId = R.drawable.orange_circle;
        break;
      case YELLOW:
        iconResourceId = R.drawable.yellow_circle;
        break;
      case GREEN:
        iconResourceId = R.drawable.green_circle;
        break;
      case BLUE:
        iconResourceId = R.drawable.blue_circle;
        break;
      case INDIGO:
        iconResourceId = R.drawable.indigo_circle;
        break;
      case VIOLET:
        iconResourceId = R.drawable.violet_circle;
        break;
    }
    viewHolder.color.setImageResource(iconResourceId);
    viewHolder.name.setText(e.getName());
    viewHolder.removeButtonListener.setElement(e);
    viewHolder.selectListener.setElement(e);
    viewHolder.changeColorListener.setElement(e);
  }

  @Override
  public int getItemCount() {
    return elements.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private ImageView color;
    private Button remove;
    private RemoveButtonListener removeButtonListener;
    private SelectListener selectListener;
    private ChangeColorListener changeColorListener;

    public ViewHolder(View itemView) {
      super(itemView);
      name = (TextView) itemView.findViewById(R.id.element_name);
      color = (ImageView) itemView.findViewById(R.id.element_color);
      remove = (Button) itemView.findViewById(R.id.remove_element);
      removeButtonListener = new RemoveButtonListener();
      selectListener = new SelectListener();
      changeColorListener = new ChangeColorListener();
      remove.setOnClickListener(removeButtonListener);
      color.setOnClickListener(changeColorListener);
      itemView.setOnClickListener(selectListener);
    }

    private class ChangeColorListener implements View.OnClickListener {

      private Element e;
      final CharSequence colors[] =
              {"Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet", "Empty"};
      int color = -1;

      @Override
      public void onClick(View view) {
        System.out.println("onClick: change i_color");
        changeColorDialog(view);
      }

      public void setElement(Element e) {
        this.e = e;
      }

      private void changeColorDialog(final View v) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        CharSequence title = v.getContext().getString(R.string.change_color)
                + " " + e.getName() + " на:";
        builder.setTitle(title)
                .setSingleChoiceItems(colors, -1, new DialogInterface.OnClickListener() {

                  @Override
                  public void onClick(DialogInterface dialog, int item) {
                    color = item;
                  }
                })
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int id) {
                    if (color != -1) {
                      e.setType(Element.Type.values()[color]);
                      notifyItemChanged(elements.indexOf(e));
                    }
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


    private class SelectListener implements View.OnClickListener {

      private Element e;

      @Override
      public void onClick(View v) {
        System.out.println("onClick: select item");
        selectDialog(v);
      }

      public void setElement(Element e) {
        this.e = e;
      }

      private void selectDialog(final View v) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        CharSequence message = v.getContext().getString(R.string.select_element)
                + " " + e.getName();
        builder.setMessage(message)
                .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
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
        System.out.println("onClick: remove item");
        removeDialog(v);
      }

      public void setElement(Element e) {
        this.e = e;
      }

      private void removeDialog(final View v) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        CharSequence message = v.getContext().getString(R.string.remove_element)
                + " " + e.getName() + "?";
        builder.setMessage(message)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int id) {
                    remove(e);
                    dialog.cancel();
                    Snackbar.make(v, e.getName() + " был удален", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                  }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
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
