package com.evo.componentagent.util;
import java.util.HashMap;
import java.util.Map.Entry;

import org.newdawn.slick.geom.Vector2f;
import com.artemis.Entity;
import com.artemis.World;
import com.evo.componentagent.components.Position;

public class PositionAverageStrategy extends AverageStrategy {

  private Position position; 

  public PositionAverageStrategy(Position position,World world, HashMap<Entity,Position> collection) { 
    super(collection);
    this.position = position; 
  }
  
  
  @Override
  public Vector2f CalculateAverage() {
    if (collection != null && collection.size() > 1) {
      for (Entry<Entity,Position> entry : collection.entrySet()) {
        Position neighbour = entry.getValue(); 
        add(position.getOffset(neighbour)); 
      }
      return new Vector2f((sum.x/count) ,(sum.y /count) );
    }
  return null; 
  }
}
