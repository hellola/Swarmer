package com.evo.componentagent.components;

import java.util.ArrayList;

import com.artemis.Component;
import com.evo.componentagent.BehaviourOptions;

public class Behaviours extends Component {
  protected ArrayList<BehaviourOptions> behaviourOptions; 
  private double angle; 

  public Behaviours() {
    behaviourOptions = new ArrayList<BehaviourOptions>(); 
    angle = 0; 
  }
  
  public double getAngle() { 
    return angle; 
  }
  
  public void setAngle(double angle) { 
    this.angle = angle; 
  }
  
  public ArrayList<String> getUsedNeighbourhoods() { 
    ArrayList<String> used = new ArrayList<String>(); 
    for (BehaviourOptions options : behaviourOptions) { 
      used.add(options.getLocale());  
    }
    return used; 
  }
  
  public ArrayList<BehaviourOptions> getBehaviours() {
    return behaviourOptions; 
  }

  public Behaviours(BehaviourOptions options) {
    behaviourOptions = new ArrayList<BehaviourOptions>(); 
    this.behaviourOptions.add(options);
  }
  public void AddBehaviourOption(BehaviourOptions options) { 
    this.behaviourOptions.add(options); 
  }

}
