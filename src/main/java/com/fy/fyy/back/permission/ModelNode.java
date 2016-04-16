package com.fy.fyy.back.permission;

public class ModelNode {

  private String id;
  private String text;
  private String parent;
  private String href;
  private State state;

  public ModelNode() {}

  public ModelNode( String id, String text, String parent, String href ) {
    this.id = id;
    this.text = text;
    this.parent = parent;
    this.href = href;
    this.state = new State();
  }

  public String getId() {
    return id;
  }

  public void setId( String id ) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText( String text ) {
    this.text = text;
  }

  public String getParent() {
    return parent;
  }

  public void setParent( String parent ) {
    this.parent = parent;
  }

  public String getHref() {
    return href;
  }

  public void setHref( String href ) {
    this.href = href;
  }

  public State getState() {
    return state;
  }

  public void setState( State state ) {
    this.state = state;
  }

}
