package com.evo.componentagent.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.evo.componentagent.BehaviourOptions;

public class SwarmerEntity {
	private String name; 
	private HashMap<String, Double> behaviours; 
	private String neighbourhood; 
	private HashMap<String,String> attributes; 
	
	public SwarmerEntity() { 
		behaviours = new HashMap<String,Double>(); 
		neighbourhood = ""; 
		name = "None"; 
		attributes = new HashMap<String,String>(); 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, Double> getBehaviours() {
		return behaviours;
	}

	public void addBehaviour(String behaviour, Double entry) {
		this.behaviours.put(behaviour, entry); 
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public HashMap<String,String> getAttributes() {
		return attributes;
	}
	
	public String getAttribute(String name) { 
		for (Entry<String,String> attribute : attributes.entrySet()) { 
			if (name.equals(attribute.getKey())) { 
				String value = attribute.getValue(); 
				return value.replaceAll("\"", ""); 
			}
		}
		return null; 
	}

	public void addAttribute(Entry<String,String> entry) {
		this.attributes.put(entry.getKey(), entry.getValue());
	}

}
