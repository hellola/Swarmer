package com.evo.componentagent.util;

import java.util.ArrayList;

import com.evo.componentagent.BehaviourOptions;

public class SwarmerApplication {
  private ArrayList<BehaviourOptions> behaviours; 
  private ArrayList<SwarmerEntity> entities; 
  private ArrayList<NeighbourhoodData> locales;
  
  
  public SwarmerApplication() {
    
  }

  public BehaviourOptions getBehaviourByName(String name) { 
    for (BehaviourOptions options : behaviours) { 
      if (options.getName().equals(name)) { 
        return options; 
      }
    }
    return null; 
  }
  public ArrayList<BehaviourOptions> getBehaviours() {
    return behaviours;
  }

  public void setBehaviours(ArrayList<BehaviourOptions> behaviours) {
    this.behaviours = behaviours;
  }

  public ArrayList<SwarmerEntity> getEntities() {
    return entities;
  }

  public void setEntities(ArrayList<SwarmerEntity> entities) {
    this.entities = entities;
  }

  public ArrayList<NeighbourhoodData> getLocales(ArrayList<String> usedNeighbourhoods) { 
    ArrayList<NeighbourhoodData> searchLocales = new ArrayList<NeighbourhoodData>(); 
    for (NeighbourhoodData data : locales) { 
      for (String used : usedNeighbourhoods) { 
        if (used.equals(data.getName())) { 
          searchLocales.add(data); 
        }
      }
    }
    return searchLocales; 
  }
  public ArrayList<NeighbourhoodData> getLocales() {
    return locales;
  }

  public void setLocales(ArrayList<NeighbourhoodData> locales) {
    this.locales = locales;
  }
  

}
