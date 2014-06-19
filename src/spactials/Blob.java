package spactials;

import java.awt.Container;
import java.net.URL;

import com.artemis.ComponentMapper;

import org.newdawn.slick.geom.*;

import com.artemis.Entity;
import com.artemis.World;
import com.evo.componentagent.components.Behaviours;
import com.evo.componentagent.components.Debug;
import com.evo.componentagent.components.Neighbourhood;
import com.evo.componentagent.components.Position;
import com.evo.componentagent.components.Velocity;
import com.evo.componentagent.util.NeighbourhoodData;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.util.ResourceLoader;

public class Blob extends Spatial {

  private Position position;
  private Velocity velocity;
  private Circle blob;
  private ComponentMapper<Debug> debugMapper;
  private ComponentMapper<Position> positionMapper;
  private ComponentMapper<Velocity> velocityMapper;
  private ComponentMapper<Behaviours> behaviourMapper;
  private ComponentMapper<Neighbourhood> neighbourhoodMapper;
  private Color color;
  private String name;
  private double size;

  
  public static Font defaultFont ;
  public Blob(String name, World world, Entity owner, Color color, double size) {
    super(world, owner);
    this.name = name;
    this.color = color;
    this.size = size;
    try {
      if (defaultFont == null) { 
      String fontResource = this.getClass()
          .getResource("/resources/defaultfont.fnt").getFile();
      String fontImage = this.getClass()
          .getResource("/resources/defaultfont.png").getFile();
      defaultFont = new AngelCodeFont(fontResource, fontImage);
        
      }
      debugMapper = ComponentMapper.getFor(Debug.class, owner.getWorld());
    } catch (SlickException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void initalize() {
    positionMapper = ComponentMapper.getFor(Position.class, world);
    velocityMapper = ComponentMapper.getFor(Velocity.class, world);
    behaviourMapper = ComponentMapper.getFor(Behaviours.class, world);
    neighbourhoodMapper = ComponentMapper
        .getFor(Neighbourhood.class, world);
    blob = new Circle((float) 30, (float) 30, (float) size);

    size = 10;
  }

  @Override
  public void render(Graphics g) {
    position = positionMapper.get(owner);
    velocity = velocityMapper.get(owner);
    if (debugMapper.has(owner)) {
      g.setColor(Color.green);
    } else {
      g.setColor(color);
    }
    g.setAntiAlias(true);
    blob.setCenterX(position.getX());
    blob.setCenterY(position.getY());
    g.fill(blob);

    if (debugMapper.has(owner)) {
      String localeText = "";
      if (neighbourhoodMapper.has(owner)) {
        Neighbourhood neighbourHood = neighbourhoodMapper.get(owner);
        for (NeighbourhoodData locale : neighbourHood.getLocales()) {
          localeText += locale.getName()
              + ":"
              + ((Integer) neighbourHood.getLocaleMembers(
                  locale.getName()).size()).toString() + "\n";
        }
      }
      String id = ((Integer) owner.getId()).toString();
      defaultFont.drawString(position.getX() + 20, position.getY() + 20, name
          + " " + id + " " + localeText);
      g.setColor(Color.red);
      Debug debug = debugMapper.get(owner);
      if (debug.drawDebugVector) {
        g.drawLine(position.getX(), position.getY(), position.getX()
            + debug.debugVector.x, position.getY()
            + debug.debugVector.y);
      }
      for (Circle circle : debug.debugCircles) {
        g.draw(circle);
      }
      debug.debugCircles.clear();
      g.draw(debug.averagePositionDebugShape);
      g.setColor(Color.blue);
      g.draw(debug.averageVelocityDebugShape);
      g.draw(debug.wanderDebugCircle);
      g.setColor(Color.red);
      g.drawLine(
          debug.wanderDebugCircle.getCenterX(),
          debug.wanderDebugCircle.getCenterY(),
          debug.wanderDebugCircle.getCenterX() + debug.wanderVector.x,
          debug.wanderDebugCircle.getCenterY() + debug.wanderVector.y);
      // debug.shapes.clear();
    }
  }
}
