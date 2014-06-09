package com.evo.componentagent.systems;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.managers.GroupManager;
import com.artemis.systems.EntityProcessingSystem;
import com.artemis.utils.ImmutableBag;
import com.evo.componentagent.components.*;
import com.evo.componentagent.util.NeighbourhoodData;

public class NeighbourhoodSystem extends EntityProcessingSystem {

	private ComponentMapper<Position> positionMapper;
	private ComponentMapper<FacadePosition> facadeMapper;
	private ComponentMapper<Neighbourhood> neighbourhoodMapper;
	private ComponentMapper<Debug> debugMapper;
	private ComponentMapper<Velocity> velocityMapper;
	private GameContainer container;
	private GroupManager groupManager;
	private Position entityPosition;
	private Neighbourhood neighbourhood;
	private NeighbourhoodData currentNeighbourhoodData;
	private Shape viewShape;
	private Vector2f currentVelocity;
	private Entity processingEntity;

	public NeighbourhoodSystem(GameContainer container) {
		super(Aspect.getAspectForAll(Position.class, Neighbourhood.class));
		this.container = container;
	}

	@Override
	protected void initialize() {
		positionMapper = ComponentMapper.getFor(Position.class, world);
		neighbourhoodMapper = ComponentMapper
				.getFor(Neighbourhood.class, world);
		facadeMapper = ComponentMapper.getFor(FacadePosition.class, world);
		debugMapper = ComponentMapper.getFor(Debug.class, world);
		velocityMapper = ComponentMapper.getFor(Velocity.class, world);
		groupManager = world.getManager(GroupManager.class);
		currentVelocity = null;
	}

	@Override
	protected void process(Entity entity) {
		processingEntity = entity;
		entityPosition = positionMapper.get(entity);
		if (velocityMapper.has(entity)) {
			currentVelocity = velocityMapper.get(entity).getValue();
		} else {
			currentVelocity = null;
		}
		neighbourhood = neighbourhoodMapper.get(entity);
		neighbourhood.reset();

		for (NeighbourhoodData locale : neighbourhood.getLocales()) {
			viewShape = locale.getShape();
			currentNeighbourhoodData = locale; 
			setUpShape();
			for (String entityGroup : locale.getEligibleEntities()) {
				ImmutableBag<Entity> entities = groupManager
						.getEntities(entityGroup);
				for (int i = 0; i < entities.size(); i++) {
					Entity currentEntity = entities.get(i);
					if (currentEntity != entity) {
						searchEntity(locale.getName(), currentEntity);
					} else {
						neighbourhood.add(locale.getName(), currentEntity,
								entityPosition);
					}
				}
			}

		}

	}

	private void searchEntity(String locale, Entity currentEntity) {
		Position point = positionMapper.get(currentEntity);

		if (inNeighbourhood(point)) {
			neighbourhood.add(locale, currentEntity, point);
		} else {
			if (facadeMapper.has(currentEntity)) {
				FacadePosition facade = facadeMapper.get(currentEntity);
				for (Position facadePosition : facade.getPositions()) {
					if (inNeighbourhood(facadePosition)) {
						neighbourhood
								.add(locale, currentEntity, facadePosition);
						break;
					}
				}
			}
		}

	}

	protected boolean inNeighbourhood(Position point) {
		if (pointInShape(point)) {
			return true;
		}
		return false;
	}

	protected boolean pointInShape(Position p) {
		return viewShape.contains(p.getX(), p.getY());
	}

	protected void setUpShape() {
		Vector2f transformPosition = currentNeighbourhoodData.getTransformPosition(); 
		if (viewShape instanceof Circle) {
			viewShape.setCenterX(entityPosition.getX() + transformPosition.getX());
			viewShape.setCenterY(entityPosition.getY() + transformPosition.getY());
		} else {
			viewShape.setX(entityPosition.getX() + transformPosition.getX());
			viewShape.setY(entityPosition.getY() + transformPosition.getY());

			if (currentVelocity != null) {
				float angle = (float) Math
						.toRadians(currentVelocity.getTheta() - 90);
				Shape rotated = viewShape.transform(Transform
						.createRotateTransform(angle, entityPosition.getX(),
								entityPosition.getY()));
				viewShape = rotated;
			}

		}
		if (debugMapper.has(processingEntity)) {
			Shape shape = viewShape.transform(Transform.createRotateTransform(0)); 
			debugMapper.get(processingEntity).neighbourhoodShapes
					.add(shape);
		}

	}

}
