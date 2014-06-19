package com.evo.componentagent;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Vector2f;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.*;
import com.evo.componentagent.components.*;
import com.evo.componentagent.util.NeighbourhoodData;

public class AgentFactory {
  public static Random r = new Random();

  public static Entity createAgent(World world, String group,boolean debug) {
    Entity agent = world.createEntity();
    agent.addComponent(new Position(r.nextInt(800), r.nextInt(600)));
    agent.addComponent(new SpatialForm("Agent","Basic", Color.white,10));
    ArrayList<NeighbourhoodData> neighbourhoodLocales = new ArrayList<NeighbourhoodData>();
    NeighbourhoodData locale = new NeighbourhoodData(); 
    locale.setName("View");
    locale.setSize(200);
    locale.addEligibleEntity("Agent"); 
    neighbourhoodLocales.add(locale); 
    locale = new NeighbourhoodData(); 
    locale.setName("Collision");
    locale.setSize(100);
    locale.addEligibleEntity("Agent"); 
    neighbourhoodLocales.add(locale); 
    agent.addComponent(new Neighbourhood(neighbourhoodLocales));
    Behaviours behaviours = new Behaviours(); 
    BehaviourOptions wander = BehaviourOptions.CreateBehaviourOptions(BehaviourOperation.Wander, 0.5);
    wander.setAngle(30);
    BehaviourOptions seperation = BehaviourOptions.BaseBehaviourOptions(BehaviourOperation.Closest, "Collision", -0.7, group);
    BehaviourOptions cohesion = BehaviourOptions.PositionBehaviourOptions(BehaviourOperation.AverageFor, "View", 0.3, group);
    BehaviourOptions alignment = BehaviourOptions.VectorBehaviourOptions(BehaviourOperation.AverageFor, "View", 1, group);

    behaviours.AddBehaviourOption(wander);
    behaviours.AddBehaviourOption(seperation);
    behaviours.AddBehaviourOption(cohesion);
    behaviours.AddBehaviourOption(alignment);
    
    agent.addComponent(behaviours); 
    if (debug) { 
      agent.addComponent(new Debug()); 
    }
    int mass = 350;
    agent.addComponent(Velocity.RandomVelocity(mass,1,2,Velocity.DefaultPanicVelocity));

    GroupManager manager = world.getManager(GroupManager.class);
    manager.add(agent, group);
    return agent;
  }

}
