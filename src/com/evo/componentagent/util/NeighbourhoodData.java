package com.evo.componentagent.util;

import java.util.ArrayList;

public class NeighbourhoodData {
	
	private Integer size; 
	private String name; 
	private ArrayList<String> eligibleEntities;
	
	public NeighbourhoodData() { 
		eligibleEntities = new ArrayList<String>(); 
	}
	
	public ArrayList<String> getEligibleEntities() {
		return eligibleEntities;
	}
	
	public void addEligibleEntity(String entity) { 
		eligibleEntities.add(entity); 
	}
	public void setEligibleEntities(ArrayList<String> filters) {
		this.eligibleEntities = filters;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 

}
