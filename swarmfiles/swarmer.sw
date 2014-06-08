
views  { 
   GeneralAgent: {
   	size: 200; 
   	entities: agent; 
   }
   Small: { 
    size: 100; 
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
}

entity flee { 
	attributes {
		mass: "300"; 
		max_velocity: "1"; 
		max_force: "2"; 
		count:"2"; 
		color: "#8EB732"; 
	}
	behaviours { 
	  wanderbase: 0.6;
	  repulse: -0.8; 
	  cohesionFlee: 0.8; 
	  seperationFlee: -1; 
	  alignmentFlee: 0.7;
	}
}

entity agent { 
	attributes {
		mass: "300"; 
		max_velocity: "1"; 
		max_force: "2"; 
		count:"2"; 
		color: "#FE9C15";
	}
	behaviours { 
		wanderbase: 0.6;
		cohesionAgent: 0.4; 
		seperationAgent: -0.7; 
		alignmentAgent: 0.7; 
	}
}

behaviour seperationAgent { 
	view: Small; 
	operation: closest; 
}

behaviour seperationFlee { 
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

behaviour cohesionAgent { 
	view: GeneralAgent; 
	operation: average; 
	attribute: position; 
}

behaviour cohesionFlee { 
	view: GeneralFlee; 
	operation: average; 
	attribute: position; 
}

behaviour alignmentFlee { 
	view: GeneralFlee; 
	operation: average; 
	attribute: vector; 
}

behaviour alignmentAgent { 
	view: GeneralAgent; 
	operation: average; 
	attribute: vector; 
}

