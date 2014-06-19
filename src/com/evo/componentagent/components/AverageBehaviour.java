package com.evo.componentagent.components;

import com.artemis.*;
import com.evo.componentagent.*;

import org.newdawn.slick.*;

public class AverageBehaviour extends Component {
  private BehaviourOptions options; 
  public AverageBehaviour() {
  }
  
  public BehaviourOptions getBehaviourOptions() {
    return options; 
  }

  public AverageBehaviour(BehaviourOptions options) {
    this.options = options; 
  }
}
