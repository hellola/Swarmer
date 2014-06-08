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

public class WrapSystem extends EntityProcessingSystem {

	private ComponentMapper<Position> positionMapper;
	private ComponentMapper<FacadePosition> facadePositionMapper;
	private ComponentMapper<Debug> debugMapper;
	private GameContainer container;
	private Position position;
	// TODO - dynamic
	private float largestNeighbourhood = 200;
	// boundaries
	private boolean rightBoundary;
	private boolean leftBoundary;
	private boolean topBoundary;
	private boolean bottomBoundary;
	private FacadePosition facade;
	private Entity currentEntity;

	public WrapSystem(GameContainer container) {
		super(Aspect.getAspectForAll(Position.class));
		this.container = container;
	}

	@Override
	protected void initialize() {
		positionMapper = ComponentMapper.getFor(Position.class, world);
		facadePositionMapper = ComponentMapper.getFor(FacadePosition.class,
				world);
		debugMapper = ComponentMapper.getFor(Debug.class, world);
	}

	@Override
	protected void process(Entity entity) {
		currentEntity = entity;
		position = positionMapper.get(entity);

		boolean wrappingNeeded = checkBoundaries();
		if (wrappingNeeded) {
			loadAndClearFacadePosition();
			wrapEntityToFacade();
		}
	}

	private boolean checkBoundaries() {
		rightBoundary = leftBoundary = topBoundary = bottomBoundary = false;
		rightBoundary = position.getX() > container.getWidth()
				- largestNeighbourhood;
		leftBoundary = position.getX() < largestNeighbourhood;
		topBoundary = position.getY() < largestNeighbourhood;
		bottomBoundary = position.getY() > container.getHeight()
				- largestNeighbourhood;
		return rightBoundary || leftBoundary || topBoundary || bottomBoundary;
	}

	private void loadAndClearFacadePosition() {
		facade = null;
		if (facadePositionMapper.has(currentEntity)) {
			facade = facadePositionMapper.get(currentEntity);
			facade.clearPositions();
		} else {
			facade = new FacadePosition();
			currentEntity.addComponent(facade);
		}
	}

	private void wrapEntityToFacade() {
		Position facadePosition;
		float x = position.getX();
		float y = position.getY();
		float debugX = x;
		float debugY = y;
		Debug debug = null;
		if (debugMapper.has(currentEntity)) {
			debug = debugMapper.get(currentEntity);
		}
		if (topBoundary) {
			y = position.getY() + container.getHeight();
			debugY = y - largestNeighbourhood;
			x = position.getX();
			facadePosition = new Position(x, y);
			facade.addPosition(facadePosition);
			if (debug != null) {
				debug.debugCircles.add(new Circle(debugX, debugY, 10));
			}
		}
		if (rightBoundary) {
			float fromEdge = container.getWidth() - position.getX();
			x = -fromEdge;
			debugX = -fromEdge + largestNeighbourhood;
			facadePosition = new Position(x, y);
			facade.addPosition(facadePosition);
			if (topBoundary) {
				facade.addPosition(new Position(x, position.getY()));
				if (debug != null) {
					debug.debugCircles.add(new Circle(debugX, position.getY(), 10));
				}
			}
			if (debug != null) {
				debug.debugCircles.add(new Circle(debugX, debugY, 10));
			}
		}
		if (bottomBoundary) {
			float fromEdge = container.getHeight() - position.getY();
			y = -fromEdge;
			debugY = -fromEdge + largestNeighbourhood;
			facadePosition = new Position(x, y);
			facade.addPosition(facadePosition);
			if (rightBoundary) {
				facade.addPosition(new Position(position.getX(), y));
				if (debug != null) {
					debug.debugCircles.add(new Circle(position.getX(), debugY, 10));
				}
			}
			if (debug != null) {
				debug.debugCircles.add(new Circle(debugX, debugY, 10));
			}
		}
		if (leftBoundary) {
			x = position.getX() + container.getWidth();
			debugX = x - largestNeighbourhood;
			facadePosition = new Position(x, y);
			facade.addPosition(facadePosition);
			if (bottomBoundary || topBoundary) {
				facade.addPosition(new Position(x, position.getY()));
				if (debug != null) {
					debug.debugCircles.add(new Circle(debugX, position.getY(), 10));
				}
			}
			if (debug != null) {
				debug.debugCircles.add(new Circle(debugX, debugY, 10));
			}
		}
	}

}
