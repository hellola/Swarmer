
views  { 
   GeneralAgent: {
   	size: 200; 
   	entities: agent; 
   }
   Small: { 
    size: 140; 
    entities: agent,flee; 
   }
   RepulseView: { 
     size: 400; 
     entities: agent;
   }
   GeneralFlee: {
     size: 200; 
     entities: flee; 
   }
   ObstacleAvoid: { 
   	size:300; 
   	entities: obstacle; 
   	view_shape: rectangle; 
   }
}

entity obstacle { 
	attributes { 
		mass: "200";
		count: "10"; 
		color: "#B01139"; 
		shape: "Blob"; 
		entity_size: "20";
	}
}

entity flee { 
	attributes {
		mass: "300"; 
		max_velocity: "1"; 
		max_force: "2"; 
		count:"10"; 
		color: "#8EB732"; 
	}
	behaviours { 
	  avoid: -3; 
	  wanderbase: 0.6;
	  repulse: -0.8; 
	  cohesion: 0.6; 
	  alignment: 0.7;
	  seperation: -1; 
	}
}

entity agent { 
	attributes {
		mass: "300"; 
		max_velocity: "1"; 
		max_force: "2"; 
		count:"4"; 
		color: "#FE9C15";
	}
	behaviours { 
		avoid: -3;
		wanderbase: 0.6;
		cohesion: 0.4; 
		seperation: -0.7; 
		alignment: 0.7; 
	}
}

behaviour avoid { 
	view: ObstacleAvoid; 
	operation: closest; 
}

behaviour seperation { 
	view: Small; 
	operation: closest; 
}


behaviour wanderbase { 
	view: GeneralAgent; 
	operation: wander; 
}

//behaviour follow { 
//	view: FollowView; 
//	operation: closest;
//}

behaviour repulse { 
	view: RepulseView; 
	operation: closest; 
}

behaviour cohesion { 
	view: GeneralFlee; 
	operation: average; 
	attribute: position; 
}

behaviour alignment { 
	view: GeneralFlee; 
	operation: average; 
	attribute: vector; 
}


