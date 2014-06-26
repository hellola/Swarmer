package com.evo.componentagent.util;

import java.util.HashMap;

import org.newdawn.slick.geom.Vector2f;

import com.artemis.Entity;
import com.evo.componentagent.components.Position;

public abstract class AverageStrategy {

  protected int count; 
  protected Vector2f sum; 
  protected HashMap<Entity,Position> collection; 

  public abstract Vector2f CalculateAverage(); 
  public AverageStrategy(HashMap<Entity,Position> collection) { 
    count = 0; 
    sum = new Vector2f(0,0); 
    this.collection = collection; 
  }

  protected void add(Vector2f value) { 
    count++; 
    sum.add(value);
  }


}
