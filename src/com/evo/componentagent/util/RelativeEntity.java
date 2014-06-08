package com.evo.componentagent.util;

import com.artemis.Entity;
import com.evo.componentagent.components.Position;

public class RelativeEntity {
	private Position position; 
	private Position delta; 
	private Entity entity; 
	public RelativeEntity(Position position,Position delta,  Entity entity) {
		this.setPosition(position);
		this.setEntity(entity); 
		this.setDelta(delta); 
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public Position getDelta() {
		return delta;
	}
	public void setDelta(Position delta) {
		this.delta = delta;
	}

}
