package com.evo.componentagent.systems;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Circle;

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
	private GameContainer container;
	private GroupManager groupManager;
	private Position entityPosition;
	private Neighbourhood neighbourhood;

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
		groupManager = world.getManager(GroupManager.class);
	}

	@Override
	protected void process(Entity entity) {
		entityPosition = positionMapper.get(entity);
		neighbourhood = neighbourhoodMapper.get(entity);
		neighbourhood.reset();

		for (NeighbourhoodData locale : neighbourhood.getLocales()) {
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
		double distance = neighbourhood.getViewSize(locale);
		Position point = positionMapper.get(currentEntity);

		if (inNeighbourhood(distance, point)) {
			neighbourhood.add(locale, currentEntity, point);
		} else {
			if (facadeMapper.has(currentEntity)) {
				FacadePosition facade = facadeMapper.get(currentEntity);
				for (Position facadePosition : facade.getPositions()) {
					if (inNeighbourhood(distance, facadePosition)) {
						neighbourhood
								.add(locale, currentEntity, facadePosition);
						break;
					}
				}
			}
		}

	}

	protected boolean inNeighbourhood(double distance, Position point) {
		int radius = (int) distance / 2;
		if (pointInCircle(entityPosition, point, radius)) {
			return true;
		}
		return false;
	}

	protected boolean pointInCircle(Position c, Position p, int radius) {
		Circle circle = new Circle(c.getX(), c.getY(), (float) radius);
		return circle.contains(p.getX(), p.getY());
		// return Math.pow(p.getX() - c.getX(), 2)
		// + Math.pow(p.getY() - c.getY(), 2) < Math.pow(radius, 2);
	}

}
