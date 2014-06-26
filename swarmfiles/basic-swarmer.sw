
views  { 
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
		count:"30"; 
		color: "#FE9C15";
	}
	behaviours { 
		wanderbase: 0.5;
		cohesion: 0.3; 
		seperation: -0.7; 
		alignment: 0.8; 
	}
}

behaviour wanderbase { 
	view: GeneralAgent; 
	operation: wander; 
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

