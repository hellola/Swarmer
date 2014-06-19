package com.evo.componentagent.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.geom.Vector2f;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import com.evo.componentagent.BehaviourOptions;
import com.evo.componentagent.components.Position;
import com.evo.componentagent.components.Summable;
import com.evo.componentagent.components.Velocity;

public class VectorAverageStrategy extends AverageStrategy {

  private ComponentMapper<Velocity> velocityMapper;

  public VectorAverageStrategy(HashMap<Entity,Position> collection, World world) {
    super(collection);
    if (collection.size() > 0) {
      velocityMapper = ComponentMapper.getFor(Velocity.class, world);
    }
  }

  @Override
  public Vector2f CalculateAverage() {
    if (collection != null && collection.size() > 1) {
      for (Entity entity : collection.keySet()) {
        Velocity velocity = velocityMapper.get(entity);
        add(velocity.getValue());
      }
      return new Vector2f(sum.x / count, sum.y / count);
    }
    return null;
  }

}
