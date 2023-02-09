package com.project.roguelike;

public class Player implements Entity {
    private float xPosition;
    private float yPosition;

    public Player() {

    }

    @Override
    public float getPosition() {
        return 0;
    }
    @Override
    public float getxPosition() {
        return xPosition;
    }
    @Override
    public float getyPosition() {
        return yPosition;
    }
}
