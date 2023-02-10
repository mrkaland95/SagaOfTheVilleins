package com.project.roguelike.Characters;

import com.badlogic.gdx.math.Vector2;

public class MockChar implements ICharacter{

    /*
     * This is just a mockfile to simulate how any character-file will be created.
     *
     * NOTE: DO NOT USE!
     * 
     */
    private CharacterStats stats;

    public MockChar(CharacterStats charStats){
        this.stats = charStats;
    }

    @Override
    public void drawSpriteAnimation() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Vector2 getPosition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public float getxPosition() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getyPosition() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void moveXAxis(float distance) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void moveYAxis(float distance) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public CharacterStats getStats() {
        return this.stats;
    }
    
}
