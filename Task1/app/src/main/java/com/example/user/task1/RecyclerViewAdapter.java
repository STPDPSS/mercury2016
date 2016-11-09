package com.example.user.task1;

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
    }
  }
}
