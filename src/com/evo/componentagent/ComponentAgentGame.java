package com.evo.componentagent;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Random;

import org.newdawn.slick.*;

import spactials.Spatial;

import com.artemis.*;
import com.artemis.managers.GroupManager;
import com.evo.componentagent.components.Behaviours;
import com.evo.componentagent.components.Debug;
import com.evo.componentagent.components.Neighbourhood;
import com.evo.componentagent.components.Position;
import com.evo.componentagent.components.SpatialForm;
import com.evo.componentagent.components.Velocity;
import com.evo.componentagent.systems.*;
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
	private int agentCount;
	private int currentDebugIndex;
	private static final int DefaultNumberEntities = 10;
	private SwarmerApplication application;
	private Random random;
	private float timeController; 
	private Boolean debug;

	public ComponentAgentGame() {
		super("Agents");
		agentCount = 3;
		timeController = 10; 
		debug = true;
		currentDebugIndex = 0;
		application = null;
		random = new Random();
	}

	public ComponentAgentGame(SwarmerApplication application) {
		this();
		this.application = application;
	}

	public static void main(String[] args) {

		try {
			AppGameContainer container = new AppGameContainer(
					new ComponentAgentGame());
			container.setTargetFrameRate(100);
			container.setDisplayMode(800, 600, false);
			container.setAlwaysRender(true);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public static void startApplication(SwarmerApplication application) {
		try {
			AppGameContainer container = new AppGameContainer(
					new ComponentAgentGame(application));
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
			timeController-= 0.5; 
		}
		if (c == '-') { 
			timeController += 0.5; 
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {

		this.container = container;
		world = new World();

		renderSystem = world.setSystem(new RenderSystem(container));
		wrapSystem = world.setSystem(new WrapSystem(container));
		neighbourhoodSystem = world
				.setSystem(new NeighbourhoodSystem(container));
		behaviourSystem = world.setSystem(new BehaviourSystem(container));
		movementSystem = world.setSystem(new MovementSystem(container));

		world.setManager(new GroupManager());

		world.initialize();

		if (application == null) {
			addAgents();
		} else {
			setUpFromSwarmerApplication();
		}
	}

	private void setUpFromSwarmerApplication() {
		GroupManager manager = world.getManager(GroupManager.class);

		boolean debugCreated = false;
		for (SwarmerEntity entity : application.getEntities()) {
			String tempAttr = entity.getAttribute("count");
			int numEntities = DefaultNumberEntities;
			if (tempAttr != null) {
				numEntities = Integer.parseInt(tempAttr);
			}

			for (int i = 0; i < numEntities; i++) {
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
					/*
					 * if (tempAttr.length() == 6) { tempAttr = "FF" + tempAttr;
					 * }
					 */
					int colorInt = Integer.parseInt(tempAttr, 16);
					color = new Color(colorInt);
				}
				
				double entitySize = Spatial.DefaultSize;
				if (tempAttr != null) {  
					entitySize = Double.parseDouble(tempAttr); 
				}

				worldEntity.addComponent(new SpatialForm("Agent", entity.getName(), color,entitySize));
				Behaviours behaviours = new Behaviours();
				for (Entry<String, Double> behaviour : entity.getBehaviours()
						.entrySet()) {
					BehaviourOptions options = new BehaviourOptions(
							application.getBehaviourByName(behaviour.getKey()));
					options.setWeight(behaviour.getValue());
					options.setGroup(entity.getName());
					behaviours.AddBehaviourOption(options);
				}
				ArrayList<String> usedNeighbourhoods = behaviours
						.getUsedNeighbourhoods();
				Neighbourhood neighbourhood = new Neighbourhood(
						application.getLocales(usedNeighbourhoods));
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

				worldEntity.addComponent(Velocity.RandomVelocity(mass,
						maxVelocity, maxForce, panicVelocity));
				manager.add(worldEntity, entity.getName());

				world.addEntity(worldEntity);
			}

		}

	}

	private void addAgents() {
		BlobFactory.createSimulation(world); 
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		if (!container.isPaused()) {
			world.setDelta(delta / timeController);
			wrapSystem.process();
			neighbourhoodSystem.process();
			behaviourSystem.process();
			movementSystem.process();
			world.process();
		}
	}

}
