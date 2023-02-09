package com.project.roguelike;

import com.badlogic.gdx.math.Vector2;

public interface ICharacter {

    void drawSpriteAnimation();
    Vector2 getPosition();
    float getxPosition();

    float getyPosition();

    void moveXAxis(float distance);
    void moveYAxis(float distance);

}
