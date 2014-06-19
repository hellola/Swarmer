package com.evo.componentagent.util;

import com.artemis.Entity;
import com.artemis.utils.ImmutableBag;

public abstract class ComponentSelector {
  protected ImmutableBag<Entity> entities; 

  public ComponentSelector(ImmutableBag<Entity> entities) { 
    this.entities = entities; 
  }
  
  public abstract ImmutableBag<Entity> GetComponents(int distance); 

}
