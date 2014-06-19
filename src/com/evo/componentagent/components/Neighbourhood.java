package com.evo.componentagent.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.artemis.Component;
import com.artemis.Entity;
import com.evo.componentagent.util.NeighbourhoodData;

public class Neighbourhood extends Component {
  
  private int viewSize; 
  private String name; 
  private Map<String,HashMap<Entity,Position>> localeLists; 
  private ArrayList<NeighbourhoodData> locales; 

  public Neighbourhood() { 
  }

  public Neighbourhood(ArrayList<NeighbourhoodData> locales) {
    this.locales = locales;
    localeLists = new HashMap<String, HashMap<Entity,Position>>(); 
    for (NeighbourhoodData data : locales) { 
      localeLists.put(data.getName(), new HashMap<Entity,Position>()); 
    }
  }
  
  public ArrayList<NeighbourhoodData> getLocales() { 
    return locales;
  }
  
  
  
  public HashMap<Entity,Position> getLocaleMembers(String localeName) { 
    return localeLists.get(localeName); 
  }
  
  public void add(String locale, Entity entity, Position position) { 
    if (localeLists.get(locale) == null) {
      localeLists.put(locale, new HashMap<Entity,Position>());
    }
    localeLists.get(locale).put(entity,position);
  }
  
  
  public void reset() { 
    for (HashMap<Entity,Position> locale : localeLists.values()) {
      locale.clear();
    }
  }
  

  public int getViewSize(String locale) {
    for (NeighbourhoodData data : locales) {
      if (data.getName().equals(locale)) { 
        return data.getSize(); 
      }
    }
    return 0; 
  }

  public void setViewSize(String locale,int viewSize) {
    for (NeighbourhoodData data : locales) {
      if (data.getName().equals(locale)) { 
        data.setSize(viewSize);
      }
    }
  }

}
