package com.example.user.task1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

  private List<ListItem> listItems;

  public RecyclerViewAdapter(List<ListItem> listItems) {
    this.listItems = listItems;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, int position) {
    ListItem item = listItems.get(position);
    viewHolder.colorIcon.setImageResource(item.getColor().iconId);
    viewHolder.nameText.setText(item.getName());
    viewHolder.removeButtonListener.setListItem(item);
    viewHolder.selectListener.setListItem(item);
    viewHolder.changeColorListener.setListItem(item);
  }

  @Override
  public int getItemCount() {
    return listItems.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    private TextView nameText;
    private ImageView colorIcon;
    private Button removeButton;
    private RemoveButtonListener removeButtonListener;
    private SelectListener selectListener;
    private ChangeColorListener changeColorListener;

    public ViewHolder(View itemView) {
      super(itemView);
      nameText = (TextView) itemView.findViewById(R.id.element_name);
      colorIcon = (ImageView) itemView.findViewById(R.id.element_color);
      removeButton = (Button) itemView.findViewById(R.id.remove_element);
      removeButtonListener = new RemoveButtonListener();
      removeButton.setOnClickListener(removeButtonListener);
      selectListener = new SelectListener();
      itemView.setOnClickListener(selectListener);
      changeColorListener = new ChangeColorListener();
      colorIcon.setOnClickListener(changeColorListener);
    }

    private class ChangeColorListener implements View.OnClickListener {

      private ListItem item;

      @Override
      public void onClick(View view) {
        Log.i("SplashScreen", "onClick: change i_color");
        AlertDialog dialog = ListItem.createChangeColorDialog(
            view.getContext(),
            item.getColor(),
            new ListItem.ColorChangeCallBack() {
              @Override
              public void colorChange(ListItem.Color color) {
                item.setColor(color);
                notifyItemChanged(listItems.indexOf(item));
              }
            }
        );
        dialog.show();
      }

      public void setListItem(ListItem item) {
        this.item = item;
      }
    }

    private class SelectListener implements View.OnClickListener {

      private ListItem item;

      @Override
      public void onClick(View view) {
        Log.i("SplashScreen", "onClick: select item");
        AlertDialog dialog = (new AlertDialog.Builder(view.getContext()))
            .setMessage(
                String.format(view.getContext().getString(R.string.list_item_selection_notification), item.getName()))
            .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
              }
            }).create();
        dialog.show();
      }

      public void setListItem(ListItem item) {
        this.item = item;
      }
    }

    private class RemoveButtonListener implements View.OnClickListener {

      private ListItem item;

      @Override
      public void onClick(final View view) {
        Log.i("SplashScreen", "onClick: removeButton item");
        AlertDialog dialog = (new AlertDialog.Builder(view.getContext()))
            .setMessage(
                String.format(view.getContext().getString(R.string.remove_list_item_prompt), item.getName()))
            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                int position = listItems.indexOf(item);
                listItems.remove(position);
                notifyItemRemoved(position);
                dialog.dismiss();
                Snackbar.make(view,
                    String.format(
                        view.getContext().getString(R.string.list_item_has_been_removed), item.getName()),
                    Snackbar.LENGTH_LONG)
                    .show();
              }
            })
            .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
              }
            }).create();
        dialog.show();
      }

      public void setListItem(ListItem item) {
        this.item = item;
      }
    }
  }
}
