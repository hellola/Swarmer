package com.evo.componentagent.components;

import java.util.ArrayList;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.artemis.Component;

public class Debug extends Component {
  public Shape averagePositionDebugShape; 
  public Line averageVelocityDebugShape; 
  public Circle wanderDebugCircle; 
  public ArrayList<Circle> debugCircles; 
  public Vector2f wanderVector; 
  public Vector2f debugVector; 
  public boolean drawDebugVector; 
  public ArrayList<Shape> neighbourhoodShapes; 
  
  public Debug() {
    averagePositionDebugShape = new Circle(-40f,-40f, 10); 
    averageVelocityDebugShape = new Line(0, 0); 
    wanderDebugCircle = new Circle(-40f, -40f, 10);
    debugCircles = new ArrayList<Circle>(); 
    wanderVector = new Vector2f(); 
    debugVector = new Vector2f(); 
    drawDebugVector = false; 
    neighbourhoodShapes = new ArrayList<Shape>(); 
  }
  

}
