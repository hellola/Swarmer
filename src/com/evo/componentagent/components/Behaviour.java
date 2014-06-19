package com.evo.componentagent.components;

import com.artemis.*;
import com.evo.componentagent.*;

import org.newdawn.slick.*;

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
