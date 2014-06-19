package com.evo.componentagent.util;

import java.util.ArrayList;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

public class NeighbourhoodData {
  
  private Integer size; 
  private String name; 
  private ArrayList<String> eligibleEntities;
  private Shape shape; 
  private Vector2f transformPosition; 
  
  public NeighbourhoodData() { 
    eligibleEntities = new ArrayList<String>(); 

  }
  
  
  public Shape getShape() { 
    return shape; 
  }
  
  public void setShapeFromString(String shapeName) { 
    if (shapeName.equals("rectangle")) { 
      shape = new Rectangle(0,0,60,size); 
      transformPosition = new Vector2f(-30,0); 
    }
    else { 
      transformPosition = new Vector2f(0,0); 
      shape = new Circle(0,0,size/2); 
    }
  }
  
  
  public ArrayList<String> getEligibleEntities() {
    return eligibleEntities;
  }
  
  public void addEligibleEntity(String entity) { 
    eligibleEntities.add(entity); 
  }
  public void setEligibleEntities(ArrayList<String> filters) {
    this.eligibleEntities = filters;
  }
  
  public Integer getSize() {
    return size;
  }
  public void setSize(Integer size) {
    this.size = size;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }


  public Vector2f getTransformPosition() {
    return transformPosition;
  }


}
