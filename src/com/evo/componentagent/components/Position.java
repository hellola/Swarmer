package com.evo.componentagent.components;

import org.newdawn.slick.geom.Vector2f;

import com.artemis.Component;

public class Position extends Component implements Summable {
	public Position() {
		
	}
	
	private float x; 
	private float y; 
	
	public Vector2f getValue() { 
		return new Vector2f(x,y); 
	}
	
	public Position(int x,int y) { 
		this.x = x; 
		this.y = y; 
	}
	
	public Position(float x, float y) {
		this.x = x; 
		this.y = y; 
	}
	
	public Position sub(float x , float y) { 
		return new Position(this.x - x, this.y -y); 
	}
	
	public Position sub(Position p) { 
		return new Position(this.x - p.x, this.y -p.y); 
	}
	
	public Position add(float x , float y) { 
		return new Position(this.x + x, this.y +y); 
	}
	
	
	public float getX() { 
		return x; 
	}
	
	public Vector2f getOffset(Position position) { 
		float offsetX = position.x -x; 
		float offsetY = position.y -y; 
		return new Vector2f(offsetX,offsetY); 
	}
	
	public float getY() { 
		return y; 
	}
	
	public void setLocation(float x, float y) { 
		this.x = x; 
		this.y = y; 
	}
	
	public void addX(float x) {
		this.x += x;
	}

	public void addY(float y) {
		this.y += y; 
	}

}
