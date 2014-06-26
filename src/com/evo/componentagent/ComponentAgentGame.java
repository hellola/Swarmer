package com.evo.componentagent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import parser.ParseException;
import parser.SwarmerParser;
import spactials.Spatial;

import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.evo.componentagent.components.Behaviours;
import com.evo.componentagent.components.Debug;
import com.evo.componentagent.components.Neighbourhood;
import com.evo.componentagent.components.Position;
import com.evo.componentagent.components.SpatialForm;
import com.evo.componentagent.components.Velocity;
import com.evo.componentagent.systems.BehaviourSystem;
import com.evo.componentagent.systems.CameraSystem;
import com.evo.componentagent.systems.MovementSystem;
import com.evo.componentagent.systems.NeighbourhoodSystem;
import com.evo.componentagent.systems.RenderSystem;
import com.evo.componentagent.systems.WrapSystem;
import com.evo.componentagent.util.Direction;
import com.evo.componentagent.util.SwarmerApplication;
import com.evo.componentagent.util.SwarmerEntity;

public class ComponentAgentGame extends BasicGame {

  private World world;
  private GameContainer container;
  private EntitySystem renderSystem;
  private EntitySystem behaviourSystem;
  private EntitySystem movementSystem;
  private EntitySystem wrapSystem;
  private EntitySystem neighbourhoodSystem;
  private EntitySystem cameraSystem;
  private int agentCount;
  private int currentDebugIndex;
  private static final int DefaultNumberEntities = 10;
  private SwarmerApplication application;
  private Random random;
  private float timeController;
  private Boolean debug;
  private ArrayList<String> swarmerFiles;
  private int currentSwarmerApplicationId; 

  public ComponentAgentGame(String[] args) {
    super("Agents");
    agentCount = 3;
    timeController = 10;
    debug = true;
    currentDebugIndex = 0;
    application = null;
    random = new Random();
    currentSwarmerApplicationId = 0; 
    swarmerFiles = new ArrayList<String>(); 
    for (int i = 0; i < args.length ;i ++ ) { 
      swarmerFiles.add(args[i]); 
    }
  }


  public static void main(String[] args) {
    try {
      AppGameContainer container = new AppGameContainer(
          new ComponentAgentGame(args));
      container.setDisplayMode(800, 600, false);
      container.setAlwaysRender(true);
      container.start();
    } catch (SlickException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void render(GameContainer container, Graphics graphics)
      throws SlickException {
    String currentFile = swarmerFiles.get(currentSwarmerApplicationId); 
    graphics.drawString(currentFile, container.getWidth() - container.getDefaultFont().getWidth(currentFile), 10);
    graphics.setBackground(Color.black);
    renderSystem.process();
  }

  @Override
  public void keyPressed(int key, char c) {
    if (c == 'p') {
      if (container.isPaused())
        container.resume();
      else
        container.pause();

    }
    if (c == 'd') {
      // toggle debug
      if (debug) {
        world.getEntity(currentDebugIndex).removeComponent(Debug.class);
      }
      if (!debug) {
        world.getEntity(currentDebugIndex).addComponent(new Debug());
      }
      debug = !debug;
    }
    if (c == 'n') {
      world.getEntity(currentDebugIndex).removeComponent(Debug.class);
      currentDebugIndex = (currentDebugIndex + 1) % (agentCount);
      world.getEntity(currentDebugIndex).addComponent(new Debug());
    }
    if (c == 'c') {
      world.addEntity(AgentFactory.createAgent(world, "Agent", false));
    }
    if (c == '+') {
      timeController -= 0.5;
    }
    if (c == '-') {
      timeController += 0.5;
    }
    if (c == 'N'){ 
      nextSwarmerApplication();
      loadAndRunCurrentSwarmerApplication();
    }
    if (c == 'R'){ 
      loadAndRunCurrentSwarmerApplication();
    }
    if (key == Input.KEY_UP) {
      ((CameraSystem) cameraSystem).setChange(true);
      ((CameraSystem) cameraSystem).setDirection(Direction.Up);
    }
    if (key == Input.KEY_LEFT) {
      ((CameraSystem) cameraSystem).setChange(true);
      ((CameraSystem) cameraSystem).setDirection(Direction.Left);
    }
    if (key == Input.KEY_RIGHT) {
      ((CameraSystem) cameraSystem).setChange(true);
      ((CameraSystem) cameraSystem).setDirection(Direction.Right);
    }
    if (key == Input.KEY_DOWN) {
      ((CameraSystem) cameraSystem).setChange(true);
      ((CameraSystem) cameraSystem).setDirection(Direction.Down);
    }
  }
  
  private void nextSwarmerApplication() { 
    currentSwarmerApplicationId= ++currentSwarmerApplicationId % swarmerFiles.size(); 
  }

  @Override
  public void init(GameContainer container) throws SlickException {

    this.container = container;
    loadAndRunCurrentSwarmerApplication();
  }
  
  private void loadAndRunCurrentSwarmerApplication() { 
    initialiseEntitySystem(); 
    loadCurrentSwarmerApplication(); 
    setUpFromSwarmerApplication();
  }
  private void loadCurrentSwarmerApplication() { 
    String swarmerFile = swarmerFiles.get(currentSwarmerApplicationId); 
    try {
      SwarmerParser parser = new SwarmerParser(new FileInputStream(swarmerFile));
      this.application = parser.CompilationUnit(); 
    } catch (FileNotFoundException e) {
      nextSwarmerApplication(); 
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    } 

  }
  
  private void initialiseEntitySystem() { 
    world = new World();

    renderSystem = world.setSystem(new RenderSystem(container));
    wrapSystem = world.setSystem(new WrapSystem(container));
    neighbourhoodSystem = world
        .setSystem(new NeighbourhoodSystem(container));
    behaviourSystem = world.setSystem(new BehaviourSystem(container));
    movementSystem = world.setSystem(new MovementSystem(container));
    cameraSystem = world.setSystem(new CameraSystem(container));

    world.setManager(new GroupManager());

    world.initialize();
  }

  private void setUpFromSwarmerApplication() {
    agentCount = 0;
    GroupManager manager = world.getManager(GroupManager.class);

    boolean debugCreated = false;
    for (SwarmerEntity entity : application.getEntities()) {
      String tempAttr = entity.getAttribute("count");
      int numEntities = DefaultNumberEntities;
      if (tempAttr != null) {
        numEntities = Integer.parseInt(tempAttr);
      }

      Behaviours behaviours = new Behaviours();
      for (Entry<String, Double> behaviour : entity.getBehaviours()
          .entrySet()) {
        BehaviourOptions options = new BehaviourOptions(
            application.getBehaviourByName(behaviour.getKey()));
        options.setWeight(behaviour.getValue());
        options.setGroup(entity.getName());
        behaviours.AddBehaviourOption(options);
      }

      for (int i = 0; i < numEntities; i++) {

        ArrayList<String> usedNeighbourhoods = behaviours
            .getUsedNeighbourhoods();
        Neighbourhood neighbourhood = new Neighbourhood(
            application.getLocales(usedNeighbourhoods));

        Entity worldEntity = world.createEntity();
        if (i == currentDebugIndex && !debugCreated) {
          worldEntity.addComponent(new Debug());
          debugCreated = true;
        }
        worldEntity.addComponent(new Position(random.nextInt(container
            .getWidth()), random.nextInt(container.getHeight())));
        Color color = Color.white;
        tempAttr = entity.getAttribute("color");
        if (tempAttr != null) {
          tempAttr = tempAttr.replace("#", "");
          int colorInt = Integer.parseInt(tempAttr, 16);
          color = new Color(colorInt);
        }

        double entitySize = Spatial.DefaultSize;
        tempAttr = entity.getAttribute("entity_size");
        if (tempAttr != null) {
          entitySize = Double.parseDouble(tempAttr);
        }

        String form = Spatial.DefaultForm;
        tempAttr = entity.getAttribute("shape");
        if (tempAttr != null) {
          form = tempAttr;
        }

        worldEntity.addComponent(new SpatialForm(form,
            entity.getName(), color, entitySize));

        worldEntity.addComponent(neighbourhood);

        worldEntity.addComponent(behaviours);

        int mass = Velocity.DefaultMass;
        tempAttr = entity.getAttribute("mass");
        if (tempAttr != null) {
          mass = Integer.parseInt(tempAttr);
        }
        double maxVelocity = Velocity.DefaultMaxVelocity;
        tempAttr = entity.getAttribute("max_velocity");
        if (tempAttr != null) {
          maxVelocity = Double.parseDouble(tempAttr);
        }

        double maxForce = Velocity.DefaultMaxForce;
        tempAttr = entity.getAttribute("max_force");
        if (tempAttr != null) {
          maxForce = Double.parseDouble(tempAttr);
        }

        double panicVelocity = Velocity.DefaultPanicVelocity;
        tempAttr = entity.getAttribute("panic_velocity");
        if (tempAttr != null) {
          panicVelocity = Double.parseDouble(tempAttr);
        }

        if (behaviours.getBehaviours().size() > 0) {
          worldEntity.addComponent(Velocity.RandomVelocity(mass,
              maxVelocity, maxForce, panicVelocity));
        }
        manager.add(worldEntity, entity.getName());

        world.addEntity(worldEntity);
        agentCount++;
      }

    }

  }

  @Override
  public void update(GameContainer container, int delta)
      throws SlickException {
    if (!container.isPaused()) {
      world.setDelta(delta / timeController);
      wrapSystem.process();
      neighbourhoodSystem.process();
      behaviourSystem.process();
      cameraSystem.process();
      movementSystem.process();
      world.process();
    }
  }

}
