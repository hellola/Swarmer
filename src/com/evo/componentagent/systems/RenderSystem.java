package com.evo.componentagent.systems;

import org.newdawn.slick.*;

import spactials.Agent;
import spactials.Spatial;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.artemis.utils.Bag;
import com.evo.componentagent.components.Debug;
import com.evo.componentagent.components.Position;
import com.evo.componentagent.components.SpatialForm;
import com.evo.componentagent.components.Velocity;

public class RenderSystem extends EntityProcessingSystem {

	private GameContainer container ; 
	private Graphics graphics; 
	private Spatial spatial; 
	private Bag<Spatial> spatials; 
	private ComponentMapper<Position> positionMapper; 
	private ComponentMapper<SpatialForm> spatialFormMapper; 

	public RenderSystem(GameContainer container) {
		super(Aspect.getAspectForAll(Position.class,Velocity.class,SpatialForm.class));
		this.container = container; 
		this.graphics = container.getGraphics(); 
		spatials = new Bag<Spatial>(); 
	}
	

	@Override
	public void initialize() {
		positionMapper = ComponentMapper.getFor(Position.class, world); 
		spatialFormMapper = ComponentMapper.getFor(SpatialForm.class, world); 
		
	}

	@Override
	protected void process(Entity entity) {
		
	      Spatial spatial = spatials.get(entity.getId());
	      Position position = positionMapper.get(entity);


	        // within bounds?
	        if(spatial != null && position.getX() >= 0 && position.getY() >= 0
	           && position.getX() < container.getWidth() && position.getY() < container.getHeight()
	           ){
	              spatial.render(graphics);
	        }
		
	}
	
    @Override
	protected void inserted(Entity entity){
        Spatial spatial = createSpatial(entity);
        if(spatial != null){
            spatial.initalize();
            // add this spatial to the list of spatials
            spatials.set(entity.getId(), spatial);
        }
    }

    @Override
    protected void removed(Entity entity){
        spatials.set(entity.getId(), null);
    }

    /**
* A spatial contains necessary rendering logic and state.
*
* Spatial allows you to encapsulate a system-specific state per entity.
* Imagine a TankSpatial. The tank has tracks that animate according to the movement of the tank.
* This is irrelevant information for all other systems, and is used only in rendering.
* You can maintain and update the state of track animation in the TankSpatial.
*
* @param entity
* @return null
*/
    private Spatial createSpatial(Entity entity){

        SpatialForm spatialForm = spatialFormMapper.get(entity);
        String spatialFormFile = spatialForm.getSpatialFormFile();
        
        if("Agent".equalsIgnoreCase(spatialFormFile)){
            return new Agent(spatialForm.getName(), world, entity, spatialForm.getColor());
        }
        // create other spatials down here
        return null;
    }

}