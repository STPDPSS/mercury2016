package com.example.user.task1;

/**
 * Created by veronika on 10/11/16.
 */

public class Element {

  public enum Type {RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET, WHITE}

  private String name;
  private Type type;

  public Element(String name, Type type) {
    this.name = name;
    this.type = type;
  }

  public Element(Element e) {
    this.name = e.getName();
    this.type = e.getType();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }
}
