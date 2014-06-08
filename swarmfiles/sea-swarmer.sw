
views  { 
   General: {
   	size: 200; 
   	entities: fish; 
   }
   Medium: { 
     size: 150; 
     entities: fish; 
   }
   Small: { 
    size: 100; 
    entities: fish; 
   }
   SharkChase: { 
    size:220; 
    entities: fish; 
   }
   FishDodge: { 
    size:180; 
    entities: shark; 
   }
}

entity shark { 
	attributes { 
		color: "#C12D21"; 
		count: "1"; 
		mass: "400"; 
		max_velocity: "1.3"; 
		max_force: "3"; 
	}
	behaviours { 
		wanderbase: 1; 
		chase: 0.8;
	}
}
		
entity fish { 
	attributes {
		mass: "250"; 
		max_velocity: "1"; 
		max_force: "7"; 
		count:"10"; 
		color: "#FE9C15";
		panic_velocity: "6";
	}
	behaviours { 
		dodge: -1;
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

behaviour chase { 
	view: SharkChase; 
	operation: closest; 
}

behaviour dodge { 
	view: FishDodge; 
	operation: closest; 
	panic: true;
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

