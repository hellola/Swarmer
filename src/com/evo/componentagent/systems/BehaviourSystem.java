package com.evo.componentagent.systems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.managers.GroupManager;
import com.artemis.systems.*;
import com.evo.componentagent.BehaviourOptions;
import com.evo.componentagent.components.*;
import com.evo.componentagent.util.AverageStrategy;
import com.evo.componentagent.util.PositionAverageStrategy;
import com.evo.componentagent.util.VectorAverageStrategy;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Vector2f;

public class BehaviourSystem extends EntityProcessingSystem {

	private GameContainer container;
	private ComponentMapper<Position> positionMapper;
	private ComponentMapper<Behaviours> behaviourMapper;
	private ComponentMapper<Velocity> velocityMapper;
	private ComponentMapper<Debug> debugMapper;
	private ComponentMapper<Neighbourhood> neighbourhoodMapper;
	private GroupManager groupManager;
	private Random random;
	private Position entityPosition;
	private Velocity currentVelocity;
	private Neighbourhood neighbourhood;

	@SuppressWarnings("unchecked")
	public BehaviourSystem(GameContainer container) {
		super(Aspect.getAspectForAll(Position.class, Neighbourhood.class,
				Behaviours.class, Velocity.class));
		this.container = container;
		random = new Random(); 
	}

	@Override
	protected void initialize() {
		positionMapper = ComponentMapper.getFor(Position.class, world);
		behaviourMapper = ComponentMapper.getFor(Behaviours.class, world);
		velocityMapper = ComponentMapper.getFor(Velocity.class, world);
		debugMapper = ComponentMapper.getFor(Debug.class, world);
		neighbourhoodMapper = ComponentMapper
				.getFor(Neighbourhood.class, world);
		groupManager = world.getManager(GroupManager.class);

	}

	@Override
	protected void process(Entity entity) {
		Behaviours behaviours = behaviourMapper.get(entity);
		ArrayList<BehaviourOptions> behaviourOptionSet = behaviours.getBehaviours();

		entityPosition = positionMapper.get(entity);
		currentVelocity = velocityMapper.get(entity);
		neighbourhood = neighbourhoodMapper.get(entity);

		for (BehaviourOptions options : behaviourOptionSet) {
			applyBehaviour(entity, options);
		}

	}

	private void applyBehaviour(Entity entity, BehaviourOptions options) {
		switch (options.getOperation()) {
		case Wander:
			double angle = options.getAngle();
			double angleDelta = 5;
			float circleDistance = 100;
			float circleRadius = 40;
			Vector2f circleCenter = currentVelocity.getValue();
			circleCenter = circleCenter.normalise().scale(circleDistance);
			Vector2f displacement = new Vector2f(0, -1);
			displacement.scale(circleRadius);
			displacement.add(angle);
			angle += (random.nextFloat() * angleDelta) - angleDelta * 0.5;
			options.setAngle(angle);
			if (debugMapper.has(entity)) {
				Debug debug = debugMapper.get(entity);
				debug.wanderDebugCircle = new Circle(entityPosition.getX()
						+ circleCenter.x, entityPosition.getY() + circleCenter.y,
						circleRadius);
				debug.wanderVector = displacement;
			}
			Vector2f wanderForce = circleCenter.add(displacement);
			wanderForce.normalise();
			wanderForce.scale((float) ((float) options.getWeight() * currentVelocity.getMaxForce()));
			currentVelocity.steer(wanderForce,options.isPanic());
			break;
		case AverageFor:
			HashMap<Entity,Position> locale = neighbourhood
					.getLocaleMembers(options.getLocale());
			Vector2f result = null;

			AverageStrategy strategy = null;
			switch (options.getAttribute()) {
			case Vector:
				strategy = new VectorAverageStrategy(locale,world);
				break;
			case Position:
				strategy = new PositionAverageStrategy(entityPosition, world,
						locale);
				break;
			}
			result = strategy.CalculateAverage();

			if (result != null) {
				result = result.normalise(); 
				result = result.scale((float) ((float)options.getWeight() * currentVelocity.getMaxForce()));
				if (debugMapper.has(entity)) {
					Debug debug = debugMapper.get(entity);
					((Line) debug.averageVelocityDebugShape).set(
							entityPosition.getX(), entityPosition.getY(),
							entityPosition.getX() + (result.getX() ),
							entityPosition.getY() + (result.getY()));
				}
				currentVelocity.steer(result,options.isPanic());
			}
			break;
		case Closest:
			Vector2f minOffset = new Vector2f(100000, 100000);
			boolean changed = false;
			for (Entry<Entity,Position> currentEntry : neighbourhood.getLocaleMembers(options.getLocale()).entrySet()) {
				if (currentEntry.getKey() != entity) {
					Position currentPosition = currentEntry.getValue(); 
					Vector2f offset = entityPosition.getOffset(currentPosition);
					if (offset.length() < minOffset.length()) {
						changed = true;
						minOffset = offset;
					}
				}
			}
			// apply the force to the found minimum
			if (changed) {
				minOffset = minOffset.normalise();
				minOffset.scale((float) ((float) options.getWeight()
						* currentVelocity.getMaxForce()));
				currentVelocity.steer(minOffset,options.isPanic());
				if (debugMapper.has(entity)) {
					Debug debug = debugMapper.get(entity);
					debug.debugVector = minOffset;
					debug.drawDebugVector = true;
				}
			}

			break;
		case Force:
			break;
		default:
			break;
		}
	}

}
