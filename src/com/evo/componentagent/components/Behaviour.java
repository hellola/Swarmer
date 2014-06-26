package com.evo.componentagent.components;

import com.artemis.Component;
import com.evo.componentagent.BehaviourOptions;

public class Behaviour extends Component {
  private BehaviourOptions options; 
  public Behaviour() {
  }
  
  public BehaviourOptions getBehaviourOptions() {
    return options; 
  }

  public Behaviour(BehaviourOptions options) {
    this.options = options; 
  }
}
