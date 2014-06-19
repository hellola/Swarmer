<link href="./assets/style.css" rel="stylesheet"></link>
<link rel="stylesheet" href="./assets/highlight.js/styles/default.css">
<script src="./assets/highlight.js/highlight.pack.js"></script>
<script>hljs.initHighlightingOnLoad();</script>

Swarmer
=======

A platform which allows for easy and simple declaration of agent behaviour. 
Allowing the user to test and compose emergent behaviours in a basic 2D
environment.


Outline 
-------

Swarmer is built using the entity component system called [Artemis](http://gamadu.com/artemis/), and the 2D
game platform [Slick2D](http://slick.ninjacave.com/). 

The nature of emergent behaviour created in Swarmer is two dimensional. 

Behaviours can be declared with basic forces such as push and pull. Behaviours have a Field of View (FOV) or neighbourhood size. 

Entities in the platform can be associated with  multiple behaviours, these are stacked and weighted to produce complex steering behaviour. Making it easier to test and create emergent behavior.

Several different types of behaviours are provided for consumption: 
* Average behaviour will create a push or pull force on either the velocity or position of neighbouring entities. 
* Base behaviours - These include wandering and seeking 
* Closest behaviour will create a push or pull force respective to the closest
  agent in the FOV or Neighbourhood


Swarmer provides a simple and easy language parser, allowing users to create and configure swarmer programs in a simple syntax. 

Swarmer is not aimed for production games but rather as a platform for prototyping and experimentation of emergent behaviour. 

Features 
--------

* Written in an entity component system architecture.
* Render entities / agents in a two dimensional world.
* Steering capacity with mass and maximum velocity and force. 
* Behaviours can be compounded/ added together. 
* The parser allows one to easily make swarmer applications in a defined language syntax. 
* A neighbourhood system allows for different sized neighbourhoods.
* The world is wrapped in a toroidal space. 
* Neighbourhoods are wrapped in a toroidal space.
* Debug component is provided and will display visual debug information in runtime. 
* The game can be paused and resumed. 
* Debug mode can be toggled. 


Required Artifacts
------------------

For one iteration: 

Short design brief 

Ideal feature list

Cost- benefit on features -> shortlist 

Architecture (UML etc.. ) 

Code 

Post mortem (neighbourhood) 

