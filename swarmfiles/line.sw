
views  { 
   Follow: {
   	size: 150; 
   	entities: agent; 
	shape: rectangle;
	width: 100; 
   }
   General: {
   	size: 200; 
   	entities: agent; 
   }
   Small: { 
    size: 200; 
    entities: agent; 
   }
}

entity agent { 
	attributes {
		mass: "350"; 
		max_velocity: "1"; 
		max_force: "2"; 
		count:"10"; 
		color: "#FE9C15";
		panic_velocity: "2";
	}
	behaviours { 
		wanderbase: 0.2;
		follow: 0.6; 
		alignment: 0.8; 
		seperation:-0.2; 
	}
}

behaviour follow { 
	view: Follow; 
	operation: closest;
	attribute: vector; 
	offset: -40, 20; 
	panic: true; 
}

behaviour wanderbase { 
	view: General; 
	operation: wander; 
	condition: {alone, view: Follow}; 
}

behaviour seperation { 
	view: Small; 
	operation: closest; 
}

behaviour cohesion { 
	view: General; 
	operation: average; 
	attribute: position; 
}

behaviour alignment { 
	view: General; 
	operation: average; 
	attribute: vector; 
}

