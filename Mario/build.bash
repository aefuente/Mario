#!/bin/bash
set -u -e
javac Game.java View.java Controller.java Model.java Tube.java Json.java Mario.java Goomba.java FireBall.java
java Game
