package com.example.user.task1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by veronika on 10/11/16.
 */

public class ListItem {

  public enum Color {
    RED(R.drawable.circle_red, "Красный"),
    ORANGE(R.drawable.circle_orange, "Оранжевый"),
    YELLOW(R.drawable.circle_yellow, "Желтый"),
    GREEN(R.drawable.circle_green, "Зеленый"),
    BLUE(R.drawable.circle_blue, "Голубой"),
    INDIGO(R.drawable.circle_indigo, "Синий"),
    VIOLET(R.drawable.circle_violet, "Фиолетовый"),
    EMPTY(R.drawable.circle_empty, "Пустота");

    int iconId;
    String name;

    Color(int iconId, String name) {
      this.iconId = iconId;
      this.name = name;
    }

    public static String[] getNames() {
      String[] names = new String[Color.values().length];
      for (int i = 0; i < Color.values().length; i++) {
        names[i] = Color.values()[i].name;
      }
      return names;
    }
  }

  private String name;
  private Color color;

  public ListItem(String name, Color color) {
    this.name = name;
    this.color = color;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public interface ColorChangeCallBack {
    void colorChange(Color color);
  }

  public static AlertDialog createChangeColorDialog(
      Context context, Color currentColor,
      final ColorChangeCallBack colorChangeCallBack) {
    AlertDialog dialog = (new AlertDialog.Builder(context))
        .setTitle(R.string.list_item_color_label)
        .setSingleChoiceItems(
            ListItem.Color.getNames(), currentColor.ordinal(),
            new DialogInterface.OnClickListener() {

              @Override
              public void onClick(DialogInterface dialog, int item) {
                Color itemColor = ListItem.Color.values()[item];
                colorChangeCallBack.colorChange(itemColor);
                dialog.dismiss();
              }
            }
        ).create();
    return dialog;
  }
}
