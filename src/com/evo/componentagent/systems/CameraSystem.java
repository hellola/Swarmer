package com.evo.componentagent.systems;

import org.newdawn.slick.GameContainer;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.evo.componentagent.components.Position;
import com.evo.componentagent.util.Direction;


public class CameraSystem extends EntityProcessingSystem {

  private ComponentMapper<Position> positionMapper;
  private GameContainer container; 
  private boolean change; 
  private boolean completed; 

  private int startEntityId; 
  public void setChange(boolean change) {
  this.change = change;
}

private Direction direction; 

  public Direction getDirection() {
  return direction;
}

public void setDirection(Direction direction) {
  this.direction = direction;
}

public CameraSystem(GameContainer container) {
    super(Aspect.getAspectForAll(Position.class)); 
    this.container = container; 
  }

  @Override 
  protected void initialize() {
    positionMapper = ComponentMapper.getFor(Position.class, world);
    startEntityId = -1; 
    completed = true; 
    change = false; 
  }
  
  @Override
  protected void process(Entity entity) {
    if (entity.getId() == startEntityId) {
      // we have cycled through everything
      completed = true; 
    }

    Position position = positionMapper.get(entity); 
    float newX = position.getX(); 
    float newY = position.getY(); 
    if (change) { 
      change = false; 
      startEntityId = entity.getId(); 
      completed = false; 
    }
    if (!completed) { 
      switch (direction) { 
      case Up: 
        newY -= 10 * world.delta; 
        break; 
      case Down: 
        newY += 10 * world.delta; 
        break; 
      case Left: 
        newX -= 10 * world.delta; 
        break;
      case Right: 
        newX += 10 * world.delta; 
        break;
      }
    }
    else { 
      startEntityId = -1; 
    }
    newX = constrain(newX, container.getWidth()); 
    newY = constrain(newY, container.getHeight()); 
    position.setLocation(newX,newY);
  }
  
  private float constrain(float pos, int boundary) { 
    
    while (pos > boundary) { 
      pos = pos % boundary;
    }
    if (pos < 0) { 
      pos = boundary - pos; 
    }
    return pos; 
  }
  

}
