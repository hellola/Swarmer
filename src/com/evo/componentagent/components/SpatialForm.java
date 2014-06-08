package com.evo.componentagent.components;

import org.newdawn.slick.Color;

import com.artemis.Component;

public class SpatialForm extends Component {
	private String spatialFormFile;
	private Color color;
	private String name; 
	private double size; 

	public SpatialForm(String spatialFormFile, String name,Color color,double size) {
		this.spatialFormFile = spatialFormFile;
		this.color = color;
		this.name  = name; 
		this.size = size; 
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

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

}