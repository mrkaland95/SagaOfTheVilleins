package com.project.roguelike.Characters;

public class CharacterStats {

    private int defence;
    private int health;
    private int power;

    public CharacterStats(int health, int defence, int power){
        this.health = health;
        this.defence = defence;
        this.power = power;
    }
    
    public int getPower(){
        return power;
    }

    public int getHealth(){
        return health;
    }

    public int getDefence(){
        return defence;
    }
}
