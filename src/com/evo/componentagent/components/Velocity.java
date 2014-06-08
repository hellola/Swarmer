package com.evo.componentagent.components;


import java.util.Random;

import org.newdawn.slick.geom.Vector2f;

import com.artemis.Component;

public class Velocity extends Component implements Summable {
	private double maxVelocity; 
	private double maxForce; 
	private double panicVelocity; 
	private Vector2f vector; 
	private int mass; 

	public Vector2f getValue() { 
		return new Vector2f(vector.x, vector.y); 
	}
	public Velocity() { 
		
	}
	
	public Velocity(Vector2f vector, int mass, double maxVelocity, double maxForce, double panicVelocity) { 
		this.vector = vector; 
		this.mass = mass; 
		this.maxVelocity = maxVelocity;
		this.maxForce = maxForce; 
		this.panicVelocity = panicVelocity; 
	}
	
	public static Velocity RandomVelocity(int mass, double maxVelocity, double maxForce, double panicVelocity) { 
		Random r = new Random();
		float x = (r.nextFloat()*2) -1; 
		float y = (r.nextFloat()*2) -1; 
		Vector2f randomVector = new Vector2f(x,y).normalise();
		Velocity randomVelocity = new Velocity(randomVector, mass,maxVelocity,maxForce,panicVelocity); 
		return randomVelocity;
	}
	public static final int DefaultMass = 300; 
	public static final Double DefaultMaxVelocity = (double) 1; 
	public static final Double DefaultMaxForce = (double) 2; 
	public static final Double DefaultPanicVelocity  = (double) 3;
	
	
	public void steer(Vector2f desiredVector, boolean isPanic) {
		Vector2f steering = desiredVector.sub(vector);  
		truncate(steering, maxForce); 
		factorMass(steering); 
		this.vector.add(steering); 
		if (isPanic) { 
			truncate(vector,panicVelocity); 
		} else { 
			truncate(vector,maxVelocity); 
		}
	}
	
	private void truncate(Vector2f vector, int max) { 
		if (vector.length() > max) { 
			vector.normalise(); 
			vector.scale(max); 
		}
	}

	private void truncate(Vector2f vector, double max) { 
		if (vector.length() > max) { 
			vector.normalise(); 
			vector.scale((float)max); 
		}
	}
	
	private void factorMass(Vector2f steering) { 
		steering.x = steering.x / mass; 
		steering.y = steering.y / mass; 
	}
	
	public Vector2f getVelocity() { 
		return vector; 
	}

	public void recalibrate() { 
		truncate(vector, maxVelocity); 
	}
	
	public double getMaxForce() { 
		return maxForce; 
	}

	@Override 
	public String toString() {
		return vector.x + " " + vector.y; 
	}

}
