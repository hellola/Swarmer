package com.evo.componentagent.systems;

import org.newdawn.slick.GameContainer;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.evo.componentagent.components.Position;
import com.evo.componentagent.components.Velocity;


public class MovementSystem extends EntityProcessingSystem {

  private ComponentMapper<Position> positionMapper;
  private ComponentMapper<Velocity> velocityMapper;
  private GameContainer container; 

  public MovementSystem(GameContainer container) {
    super(Aspect.getAspectForAll(Position.class, Velocity.class)); 
    this.container = container; 
  }

  @Override 
  protected void initialize() {
    positionMapper = ComponentMapper.getFor(Position.class, world);
    velocityMapper = ComponentMapper.getFor(Velocity.class, world);
  }
  
  @Override
  protected void process(Entity entity) {
    Position position = positionMapper.get(entity); 
    Velocity velocity = velocityMapper.get(entity); 
    
    velocity.recalibrate();
    
    float newX = position.getX() + (velocity.getVelocity().x * world.getDelta()); 
    float newY = position.getY() + (velocity.getVelocity().y * world.getDelta()); 

    newX = constrain(newX, container.getWidth()); 
    newY = constrain(newY, container.getHeight()); 

    position.setLocation(newX,newY);
  }
  
  private float constrain(float pos, int boundary) { 
    
    while (pos > boundary) { 
      pos = pos - boundary;
    }
    if (pos < 0) { 
      pos = boundary + pos; 
    }
    return pos; 
  }
  

}
