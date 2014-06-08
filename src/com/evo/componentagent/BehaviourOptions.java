package com.evo.componentagent;

import com.evo.componentagent.components.Behaviours;

public class BehaviourOptions {
	private BehaviourOperation operation; 
	private BehaviourAttribute attribute; 
	private String name; 
	private String locale; 
	private double angle; 
	private double weight; 
	private String group;
	private boolean panic; 

	public  BehaviourOptions() { 
	}
	
	public BehaviourOptions(BehaviourOptions options) { 
		this.name = options.getName(); 
		this.locale = options.getLocale(); 
		this.angle = options.getAngle(); 
		this.weight = options.getWeight(); 
		this.group = options.getGroup(); 
		this.operation = options.getOperation(); 
		this.attribute = options.getAttribute(); 
	}
	
	public static BehaviourOptions VectorBehaviourOptions(BehaviourOperation operation, String locale, double force, String group) {
		return CreateBehaviourOptions(BehaviourAttribute.Vector, operation, locale, force, group); 
	}

	public static BehaviourOptions PositionBehaviourOptions(BehaviourOperation operation, String locale, double force, String group) {
		return CreateBehaviourOptions(BehaviourAttribute.Position, operation, locale, force, group); 
	}

	public static BehaviourOptions BaseBehaviourOptions(BehaviourOperation operation, String locale, double force, String group) {
		return CreateBehaviourOptions(BehaviourAttribute.Position, operation, locale, force, group); 
	}
	
	public static BehaviourOptions CreateBehaviourOptions(BehaviourAttribute attribute, BehaviourOperation operation, String locale, double force, String group) {
		BehaviourOptions options = new BehaviourOptions();
		options.setAttribute(attribute); 
		options.setLocale(locale);
		options.setOperation(operation); 
		options.setWeight(force); 
		options.setGroup(group); 
		return options; 
	}
	
	public static BehaviourOptions CreateBehaviourOptions(BehaviourOperation operation, double force) {
		BehaviourOptions options = new BehaviourOptions();
		options.setWeight(force);
		options.setOperation(operation); 
		return options; 
	}
	
	public BehaviourOperation getOperation() {
		return operation;
	}
	public void setOperation(BehaviourOperation operation) {
		this.operation = operation;
	}
	public BehaviourAttribute getAttribute() {
		return attribute;
	}
	public void setAttribute(BehaviourAttribute attribute) {
		this.attribute = attribute;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double force) {
		this.weight = force;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean isPanic() {
		return panic;
	}

	public void setPanic(boolean panic) {
		this.panic = panic;
	}

}
