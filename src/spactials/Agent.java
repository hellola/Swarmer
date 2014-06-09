package spactials;

import java.awt.Container;
import java.net.URL;

import com.artemis.ComponentMapper;

import org.newdawn.slick.geom.*;

import com.artemis.Entity;
import com.artemis.World;
import com.evo.componentagent.components.Behaviours;
import com.evo.componentagent.components.Debug;
import com.evo.componentagent.components.Neighbourhood;
import com.evo.componentagent.components.Position;
import com.evo.componentagent.components.Velocity;
import com.evo.componentagent.util.NeighbourhoodData;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;

public class Agent extends Spatial {

	private Position position;
	private Velocity velocity;
	private Polygon ship;
	private Font font;
	private ComponentMapper<Debug> debugMapper;
	private ComponentMapper<Position> positionMapper;
	private ComponentMapper<Velocity> velocityMapper;
	private ComponentMapper<Behaviours> behaviourMapper;
	private ComponentMapper<Neighbourhood> neighbourhoodMapper;
	private Color color;
	private String name; 

	public Agent(String name, World world, Entity owner, Color color) {
		super(world, owner);
		this.name = name; 
		this.color = color;
		try {
			String fontResource = this.getClass().getResource("/resources/defaultfont.fnt").getFile();
			String fontImage = this.getClass().getResource("/resources/defaultfont.png").getFile();
			font = new AngelCodeFont(fontResource,
					fontImage);
			debugMapper = ComponentMapper.getFor(Debug.class, owner.getWorld());
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initalize() {
		positionMapper = ComponentMapper.getFor(Position.class, world);
		velocityMapper = ComponentMapper.getFor(Velocity.class, world);
		behaviourMapper = ComponentMapper.getFor(Behaviours.class, world);
		neighbourhoodMapper = ComponentMapper
				.getFor(Neighbourhood.class, world);

		int agentSize = 10;
		ship = new Polygon();
		ship.addPoint(-agentSize, -agentSize);
		ship.addPoint(0, agentSize * 2);
		ship.addPoint(agentSize, -agentSize);
		ship.setClosed(true);
	}

	@Override
	public void render(Graphics g) {
		position = positionMapper.get(owner);
		velocity = velocityMapper.get(owner);
		Neighbourhood neighbourHood = neighbourhoodMapper.get(owner);
		if (debugMapper.has(owner)) {
			g.setColor(Color.green);
		} else {
			g.setColor(color);
		}
		g.setAntiAlias(true);
		Vector2f velocityVector = velocity.getVelocity();
		ship.setLocation(position.getX(), position.getY());
		float angle = (float) Math.toRadians(velocityVector.getTheta() - 90);
		Polygon rotated = (Polygon) ship
				.transform(Transform.createRotateTransform(angle,
						position.getX(), position.getY()));
		g.fill(rotated);
		g.setColor(Color.orange);
		Line direction = new Line(position.getX(), position.getY(),
				position.getX() + (velocityVector.x * 50), position.getY()
						+ (velocityVector.y * 50));
		g.draw(direction);

		if (debugMapper.has(owner)) {
			String localeText = "";
			for (NeighbourhoodData locale : neighbourHood.getLocales()) {
				localeText += locale.getName()
						+ ":"
						+ ((Integer) neighbourHood.getLocaleMembers(
								locale.getName()).size()).toString() + "\n";
			}
			String id = ((Integer) owner.getId()).toString();
			font.drawString(position.getX() + 20, position.getY() + 20, name
					+ " " + id + " " + localeText);
			g.setColor(Color.red);
			Debug debug = debugMapper.get(owner);

			g.setColor(Color.pink);
			for (Shape shape : debug.neighbourhoodShapes) { 
				g.draw(shape);
			}
			debug.neighbourhoodShapes.clear();

			if (debug.drawDebugVector) {
				g.drawLine(position.getX(), position.getY(), position.getX()
						+ debug.debugVector.x, position.getY()
						+ debug.debugVector.y);
			}
			for (Circle circle : debug.debugCircles) {
				g.draw(circle);
			}
			debug.debugCircles.clear();
			g.draw(debug.averagePositionDebugShape);
			g.setColor(Color.blue);
			g.draw(debug.averageVelocityDebugShape);
			g.draw(debug.wanderDebugCircle);
			g.setColor(Color.red);
			g.drawLine(
					debug.wanderDebugCircle.getCenterX(),
					debug.wanderDebugCircle.getCenterY(),
					debug.wanderDebugCircle.getCenterX() + debug.wanderVector.x,
					debug.wanderDebugCircle.getCenterY() + debug.wanderVector.y);
			// debug.shapes.clear();
		}
	}
}