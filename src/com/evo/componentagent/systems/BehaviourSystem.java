package com.evo.componentagent.systems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.managers.GroupManager;
import com.artemis.systems.EntityProcessingSystem;
import com.evo.componentagent.BehaviourOptions;
import com.evo.componentagent.components.Behaviours;
import com.evo.componentagent.components.Debug;
import com.evo.componentagent.components.FacadePosition;
import com.evo.componentagent.components.Neighbourhood;
import com.evo.componentagent.components.Position;
import com.evo.componentagent.components.Velocity;
import com.evo.componentagent.util.AverageStrategy;
import com.evo.componentagent.util.PositionAverageStrategy;
import com.evo.componentagent.util.VectorAverageStrategy;

public class BehaviourSystem extends EntityProcessingSystem {

	private ComponentMapper<Position> positionMapper;
	private ComponentMapper<Behaviours> behaviourMapper;
	private ComponentMapper<Velocity> velocityMapper;
	private ComponentMapper<Debug> debugMapper;
	private ComponentMapper<FacadePosition> facadeMapper;
	private ComponentMapper<Neighbourhood> neighbourhoodMapper;
	private Random random;
	private Position entityPosition;
	private Velocity currentVelocity;
	private Neighbourhood neighbourhood;
	private Entity processingEntity; 
	private Entity minEntity; 

	@SuppressWarnings("unchecked")
	public BehaviourSystem(GameContainer container) {
		super(Aspect.getAspectForAll(Position.class, Neighbourhood.class,
				Behaviours.class, Velocity.class));
		random = new Random();
	}

	@Override
	protected void initialize() {
		positionMapper = ComponentMapper.getFor(Position.class, world);
		behaviourMapper = ComponentMapper.getFor(Behaviours.class, world);
		velocityMapper = ComponentMapper.getFor(Velocity.class, world);
		debugMapper = ComponentMapper.getFor(Debug.class, world);
		facadeMapper = ComponentMapper.getFor(FacadePosition.class, world);
		neighbourhoodMapper = ComponentMapper
				.getFor(Neighbourhood.class, world);
		world.getManager(GroupManager.class);

	}

	@Override
	protected void process(Entity entity) {
		processingEntity = entity; 
		Behaviours behaviours = behaviourMapper.get(entity);
		ArrayList<BehaviourOptions> behaviourOptionSet = behaviours
				.getBehaviours();

		entityPosition = positionMapper.get(entity);
		currentVelocity = velocityMapper.get(entity);
		neighbourhood = neighbourhoodMapper.get(entity);

		for (BehaviourOptions options : behaviourOptionSet) {
			applyBehaviour(options);
		}

	}

	private void applyBehaviour(BehaviourOptions options) {
		if (options.getConditional() != null && !options.getConditional().Test(processingEntity, neighbourhood, entityPosition, options)) { 
			return; 
		}
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
			if (debugMapper.has(processingEntity)) {
				Debug debug = debugMapper.get(processingEntity);
				debug.wanderDebugCircle = new Circle(entityPosition.getX()
						+ circleCenter.x, entityPosition.getY()
						+ circleCenter.y, circleRadius);
				debug.wanderVector = displacement;
			}
			Vector2f wanderForce = circleCenter.add(displacement);
			wanderForce.normalise();
			wanderForce
					.scale((float) ((float) options.getWeight() * currentVelocity
							.getMaxForce()));
			currentVelocity.steer(wanderForce, options.isPanic());
			break;

		case Entity:
			Entity targetEntity = world.getEntity(options.getEntityId());
			if (positionMapper.has(targetEntity)) {
				Position masterPosition = positionMapper.get(targetEntity);
				Vector2f entityForce = entityPosition.getOffset(masterPosition);
				if (facadeMapper.has(targetEntity)) {
					FacadePosition facade = facadeMapper.get(targetEntity);
					for (Position position : facade.getPositions()) {
						Vector2f facadeVector = entityPosition
								.getOffset(position);
						if (entityForce.length() > facadeVector.length()) {
							entityForce = facadeVector;
						}
					}
				}
				entityForce.normalise();
				entityForce
						.scale((float) ((float) options.getWeight() * currentVelocity
								.getMaxForce()));
				currentVelocity.steer(entityForce);
			}

		case AverageFor:
			HashMap<Entity, Position> locale = neighbourhood
					.getLocaleMembers(options.getLocale());
			Vector2f result = null;

			AverageStrategy strategy = null;
			switch (options.getAttribute()) {
			case Vector:
				strategy = new VectorAverageStrategy(locale, world);
				break;
			case Position:
				strategy = new PositionAverageStrategy(entityPosition, world,
						locale);
				break;
			}
			result = strategy.CalculateAverage();

			if (result != null) {
				result = result.normalise();
				result = result
						.scale((float) ((float) options.getWeight() * currentVelocity
								.getMaxForce()));
				if (debugMapper.has(processingEntity)) {
					Debug debug = debugMapper.get(processingEntity);
					debug.averageVelocityDebugShape.set(entityPosition.getX(),
							entityPosition.getY(), entityPosition.getX()
									+ (result.getX()), entityPosition.getY()
									+ (result.getY()));
				}
				currentVelocity.steer(result, options.isPanic());
			}
			break;
		case Field:
			Vector2f minOffset = new Vector2f(100000, 100000);
			boolean changed = false;
			for (Entry<Entity, Position> currentEntry : neighbourhood
					.getLocaleMembers(options.getLocale()).entrySet()) {
				if (currentEntry.getKey() != processingEntity) {
					Position currentPosition = currentEntry.getValue();
					Vector2f offset = entityPosition.getOffset(currentPosition);
					if (offset.length() < minOffset.length()) {
						changed = true;
						minOffset = offset;
					}
				}
			}
			if (changed) {
				minOffset = minOffset.normalise();
				int max = neighbourhood.getViewSize(options.getLocale());
				double proportion = 1 - (minOffset.length() / max);

				minOffset
						.scale((float) ((float) (options.getWeight() * proportion) * currentVelocity
								.getMaxForce()));
				currentVelocity.steer(minOffset, options.isPanic());
				if (debugMapper.has(processingEntity)) {
					Debug debug = debugMapper.get(processingEntity);
					debug.debugVector = minOffset;
					debug.drawDebugVector = true;
				}
			}

			break;
		case Flank:
			minOffset = new Vector2f(100000, 100000);
			changed = false;
			for (Entry<Entity, Position> currentEntry : neighbourhood
					.getLocaleMembers(options.getLocale()).entrySet()) {
				if (currentEntry.getKey() != processingEntity) {
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
				minOffset
						.scale((float) ((float) options.getWeight() * currentVelocity
								.getMaxForce()));
				minOffset = minOffset.getPerpendicular();
				currentVelocity.steer(minOffset, options.isPanic());
				if (debugMapper.has(processingEntity)) {
					Debug debug = debugMapper.get(processingEntity);
					debug.debugVector = minOffset;
					debug.drawDebugVector = true;
				}
			}

			break;
		case Closest:
			minOffset = getClosest(options);

			if (options.hasOffset() && minEntity != null) {

				switch (options.getAttribute()) {
				case Vector:
					Velocity velocity = velocityMapper.get(minEntity);
					double theta = velocity.getVelocity().getTheta();
					Vector2f delta = options.getOffset().copy(); 
					delta.add(theta); 
					minOffset.add(delta);
					break;
				case Position:
					minOffset.add(options.getOffset());
					break;
				default:
					break;
				}
			}
			minOffset = minOffset.normalise();
			minOffset.scale((float) (100));
			if (debugMapper.has(processingEntity)) {
				Debug debug = debugMapper.get(processingEntity);
				debug.debugCircles.add(new Circle(entityPosition.getX() + minOffset.x, entityPosition.getY()+ minOffset.y , 20)); 
				debug.debugVector = minOffset;
				debug.drawDebugVector = true;
			}
			minOffset = minOffset.normalise();
			minOffset.scale((float) ((float) options.getWeight() * currentVelocity
							.getMaxForce()));
			currentVelocity.steer(minOffset, options.isPanic());
			break;
		case Force:
			break;
		default:
			break;
		}
	}


	private Vector2f getClosest(BehaviourOptions options) {
		minEntity = null; 
		Vector2f minOffset = new Vector2f(100000, 100000);
		Vector2f result = new Vector2f(0, 0);
		boolean changed;
		changed = false;
		for (Entry<Entity, Position> currentEntry : neighbourhood
				.getLocaleMembers(options.getLocale()).entrySet()) {
			if (currentEntry.getKey() != processingEntity) {
				Position currentPosition = currentEntry.getValue();
				Vector2f offset = entityPosition.getOffset(currentPosition);
				if (offset.length() < minOffset.length()) {
					changed = true;
					minEntity = currentEntry.getKey();
					minOffset = offset;
				}
			}
		}
		// apply the force to the found minimum
		if (changed) {
			result = minOffset;
		}
		return result;
	}

}
