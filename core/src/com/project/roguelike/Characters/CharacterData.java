package com.project.roguelike.Characters;

public interface CharacterData {
    public int getHealth(ICharacter character);
    public int getStrength(ICharacter character);
    public int getDefense(ICharacter character);
    public int getMaxHealth(ICharacter character);
    //public int getMaxDefense(); implemnet after debuffs and buffs
    //public int getMaxStength();
    //public int setMaxHealth();
    public void setHealth(int damageTaken, ICharacter character); // call when chararcther get damaged
    //public void setStrength();
    //public void setDefense();


}
