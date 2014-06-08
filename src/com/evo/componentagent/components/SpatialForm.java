package com.evo.componentagent.components;

import org.newdawn.slick.Color;

import com.artemis.Component;

public class SpatialForm extends Component {
	private String spatialFormFile;
	private Color color;
	private String name; 

	public SpatialForm(String spatialFormFile, String name,Color color) {
		this.spatialFormFile = spatialFormFile;
		this.color = color;
		this.name  = name; 
	}
	
	public String getName() { 
		return name; 
	}

	public Color getColor() {
		return color;
	}

	public String getSpatialFormFile() {
		return spatialFormFile;
	}

}