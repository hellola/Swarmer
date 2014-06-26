package com.evo.componentagent.util;

import com.artemis.Entity;
import com.evo.componentagent.BehaviourOptions;
import com.evo.componentagent.components.Neighbourhood;
import com.evo.componentagent.components.Position;

public class NotAloneConditional implements Condition {
		private String view; 
		@Override
		public void setView(String view) { 
			this.view = view; 
		}

		@Override
		public boolean Test(Entity entity, Neighbourhood neighbourhood,
				Position position, BehaviourOptions options) {
			if (neighbourhood.getLocaleMembers(view).size() > 1) { 
				return true; 
			}
			return false;
		}

}
