# Pixel Game by Java: Frog Cross The River

## Introduction

This is a little pixel game created by Java (Version: 10.0.2). The LWJGL (Light-Weight Java Gaming Library) was used for its graphics and input.

There are two levels of the game. The player will control the frog by press 'Up', 'Down', 'Left' and 'Right' on the keyboard. 

In the first level, the frog initially has three lives. Everytime when the frog is crashed by a car or falls into the river, the frog will lose one life. The empty tiles at the top of the screen is the game goal. When the frog reaches all the five tiles, the game will be proceeded to the next level.

In the second level, the frog will also lose one life if hit by a vehicle. The frog will not lose one life if hit by a bulldozer. Instead, it will be pushed in the direction of travel unless the frog were to be pushed off the screen. The turtles will also save the frog's life, but they will disappear under the water occasionally. The blue frog that randomly appears on the log will gain the frog one life if eaten.

## How to play

1. clone the repo to the local PC
2. import the project in the Eclipse
3. If the lack of the Slick Libraries, import all of the ```.jar``` files and ```.dll``` files (Windows), or ```.dylib``` files (OSX), or ```.so``` files (Linux)
4. right click each of the ```.jar``` files and press 'Build Path -- Add to build path'
5. right click ```lwjgl.jar``` from the new-appeared 'Refferenced Libraries', press 'Properties -- Native Library -- External Folder'
6. ```App.java``` is the main entrance of the project.

## Features

Object-orented programming was used to the project. When the game is started, ```App.java``` initialize the new ```World```, which contains all the elements(sprites). The ```class``` ```Sprite``` is the most base class because every element is a sprite in this pixel game. The ```Sprite``` class contains lots of methods that makes very sense in terms of a ```sprite```. The ```render()``` method to render the sprite, the ```update()``` method in case of the sprite will be able to move. Then every other class will inherit the base class and will have their own new features or just override the father's method. For instance, the ```update()``` and ```moveAlong()``` methods in class ```Frog``` will not be the same with that in ```ExtraLife```. 

