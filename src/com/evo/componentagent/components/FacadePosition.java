package com.evo.componentagent.components;

import java.util.ArrayList;

import com.artemis.*;
import com.evo.componentagent.*;

import org.newdawn.slick.*;

public class FacadePosition extends Component {
  protected ArrayList<Position> facadePositions; 

  public FacadePosition() {
    facadePositions = new ArrayList<Position>(); 
  }
  
  public ArrayList<Position> getPositions() {
    return facadePositions; 
  }
  
  public void clearPositions() { 
    facadePositions.clear();
  }

  public FacadePosition(Position options) {
    facadePositions = new ArrayList<Position>(); 
    this.facadePositions.add(options);
  }
  public void addPosition(Position options) { 
    this.facadePositions.add(options); 
  }
}
