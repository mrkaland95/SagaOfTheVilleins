package com.project.roguelike.Tile;

import com.badlogic.gdx.math.Vector3;
import com.project.roguelike.Characters.ICharacter;

public class Tile {
    private ICharacter character;
    private Vector3 coordinate;

    public Tile(){
        this.character = null;
        this.coordinate = null;
    }

    public ICharacter getChar(){
        return this.character;
    }

    public Vector3 getCoordinate(){
        return this.coordinate;
    }
}
