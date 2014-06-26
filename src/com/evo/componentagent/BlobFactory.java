package com.evo.componentagent;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Vector2f;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.evo.componentagent.components.Behaviours;
import com.evo.componentagent.components.Debug;
import com.evo.componentagent.components.Neighbourhood;
import com.evo.componentagent.components.Position;
import com.evo.componentagent.components.SpatialForm;
import com.evo.componentagent.components.Velocity;
import com.evo.componentagent.util.NeighbourhoodData;

public class BlobFactory {
  public static Random r = new Random();

  private static ArrayList<NeighbourhoodData> neighbourhoodLocales; 
  private static ArrayList<NeighbourhoodData> smallNeighbourhoodLocales; 
  public static void createSimulation(World world) { 
    
    neighbourhoodLocales = new ArrayList<NeighbourhoodData>();
    NeighbourhoodData locale = new NeighbourhoodData(); 
    locale = new NeighbourhoodData(); 
    locale.setName("Collision");
    locale.setSize(100);
    locale.addEligibleEntity("Floating"); 
    locale.addEligibleEntity("Blob"); 
    neighbourhoodLocales.add(locale);
    smallNeighbourhoodLocales = new ArrayList<NeighbourhoodData>(); 
    locale = new NeighbourhoodData(); 
    locale.setName("SmallCollision");
    locale.setSize(40);
    locale.addEligibleEntity("SmallFloating"); 
    locale.addEligibleEntity("Floating"); 
    locale.addEligibleEntity("Blob"); 
    smallNeighbourhoodLocales.add(locale); 
    
    
    for (int i = 0; i < 4; i++) {
      Entity master = BlobFactory.createBlob(world, "Blob", i == 0);
      world.addEntity(master); 
      for (int j = 0; j < 3; j++) { 
        Entity medium = BlobFactory.createFloatingBlob(master, world, "Floating", false, j+1,10 );
        world.addEntity(medium); 
        for (int k = 0; k < 1; k++) { 
          world.addEntity(BlobFactory.createFloatingBlob(medium, world, "SmallFloating", false, k+1, 5));
        }
      }
      
      
    }
  }
  public static Entity createBlob(World world, String group,boolean debug) {
    Entity agent = world.createEntity();
    agent.addComponent(new Position(r.nextInt(800), r.nextInt(600)));
    agent.addComponent(new SpatialForm("Blob","Basic", Color.white,30));
    if (debug) { 
      agent.addComponent(new Debug()); 
    }

    GroupManager manager = world.getManager(GroupManager.class);
    manager.add(agent, group);
    return agent;
  }
  public static Entity createFloatingBlob(Entity master,World world, String group,boolean debug, int number,int size) {
    Entity agent = world.createEntity();
    ComponentMapper<Position> positionMapper = ComponentMapper.getFor(Position.class, world); 
    Position masterPosition = positionMapper.get(master); 
    int deltaDegree = number * 25; 
    Vector2f vector = new Vector2f(); 
    vector.add(deltaDegree); 
    vector.scale(120); 
    
    agent.addComponent(new Position(masterPosition.getX() +vector.x , masterPosition.getY() + vector.y));
    
    Color color = Color.white; 
    
    Behaviours behaviours = new Behaviours(); 
    String view = "SmallCollision"; 
    if (group == "Floating") { 
      color = Color.blue; 
      agent.addComponent(new Neighbourhood(neighbourhoodLocales));
      view = "Collision"; 
    }
    else { 
      agent.addComponent(new Neighbourhood(smallNeighbourhoodLocales));
      color = Color.red;
    }
    
    
    BehaviourOptions seperation = BehaviourOptions.BaseBehaviourOptions(BehaviourOperation.Closest, view, -0.9, group);
    BehaviourOptions gravity = BehaviourOptions.EntityBehaviourOptions( master.getId(), 0.7, group);

    agent.addComponent(new SpatialForm("Blob","Basic", color,size));
    if (debug) { 
      agent.addComponent(new Debug()); 
    }

    behaviours.AddBehaviourOption(seperation);
    behaviours.AddBehaviourOption(gravity);
    
    agent.addComponent(behaviours); 
    int mass = 150;
    agent.addComponent(Velocity.RandomVelocity(mass,1,2,Velocity.DefaultPanicVelocity));

    GroupManager manager = world.getManager(GroupManager.class);
    manager.add(agent, group);
    return agent;
  }
  

}
