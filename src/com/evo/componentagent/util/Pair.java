package com.evo.componentagent.util;

import org.newdawn.slick.geom.Vector2f;

import com.evo.componentagent.components.Position;

public class Pair {
  private float x; 
  private float y; 
  public Pair(float x, float y) { 
    this.setX(x); 
    this.setY(y); 
  }
  
  public Position toPosition() { 
    return new Position(x,y); 
  }
  
  public Vector2f toVector() { 
    return new Vector2f(x,y); 
  }
  public float getX() {
    return x;
  }
  public void setX(float x) {
    this.x = x;
  }
  public float getY() {
    return y;
  }
  public void setY(float y) {
    this.y = y;
  }

}
