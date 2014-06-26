package com.evo.componentagent.util;

import com.artemis.Entity;
import com.evo.componentagent.BehaviourOptions;
import com.evo.componentagent.components.Neighbourhood;
import com.evo.componentagent.components.Position;

public interface Condition {
  public void setView(String view); 
  public boolean Test(Entity entity, Neighbourhood neighbourhood,Position position,BehaviourOptions options); 

}
