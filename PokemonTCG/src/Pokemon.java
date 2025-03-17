/**
 * Pokemon Class.
 * Is the basic shell for any given Pokemon, setting each necessary value.
 */
public class Pokemon extends Card {
    
    private int baseHP;
    private int currentHP;
    private int energyCount;
    private int atk1Val;
    private int atk2Val;

    /**
     * Constructor for Pokemon.
     * Sets the base and initial current health, the initial energy count to 0, and the energy values for attacks 1 and 2.
     * Constructor is specifically used for Lugia, who has 2 attacks.
     * @param uHP  The HP value to initially set.
     * @param atk1 The energy value for attack 1.
     * @param atk2 The energy value for attack 2.
     */
    public Pokemon(int uHP, int atk1, int atk2) {
        baseHP = uHP;
        currentHP = uHP;
        energyCount = 0;
        atk1Val = atk1;
        atk2Val = atk2;
    }

    /**
     * Constructor for Pokemon.
     * Sets the base and initial current health, the initial energy count to 0, and the energy value for attack 1.
     * @param uHP  The HP value to initially set.
     * @param atk1 The energy value for attack 1.
     */
    public Pokemon(int uHP, int atk1) {
        baseHP = uHP;
        energyCount = 0;
        atk1Val = atk1;
    }

    /**
     * Empty Constructor for Pokemon.
     */
    public Pokemon() {}

    /**
     * Get HP Function.
     * Grabs the current HP of the Pokemon.
     * @return The current HP of the Pokemon.
     */
    public int getHP() {
        return currentHP;
    }

    /**
     * Get Base HP Function.
     * Grabs the Base HP value for the given Pokemon.
     * @return The base HP value of the Pokemon.
     */
    public int getBaseHP() {
        return baseHP;
    }

    /**
     * Set HP Function.
     * Sets a new value for the current HP of the Pokemon.
     * @param newHP The new HP for the Pokemon.
     */
    public void setHP(int newHP) {
        currentHP = newHP;
    }

    /**
     * Remove Energy Function.
     * Removes a given amount of energy from the Pokemon.
     * @param enToRemove The amount of energy to remove from said Pokemon.
     */
    public void removeEnergy(int enToRemove) {
        energyCount = energyCount - enToRemove;
    }

    /**
     * Energy Check Function.
     * Returns a value on whether the given amount is contained within the Pokemon.
     * @param enToCheck The amount of energy to check for.
     * @return The boolean value if the energy matches.
     */
    public boolean energyCheck(int enToCheck) {
        if(energyCount != enToCheck) {
            return false;
        }
        return true;
    }

    /**
     * Attack Function.
     * An empty function to be overridden by a specific Pokemon.
     * @param pk The Pokemon being attacked.
     */
    public void attack(Pokemon pk) {}

    /**
     * Attack Function.
     * This function is specifically used for Drampa, as the parameters call for a Player to discard cards from.
     * @param pk The Pokemon being attacked.
     * @param p1 The Player to discard cards from.
     */
    public void attack(Pokemon pk, Player p1) {}

    /**
     * Attack 2 Function.
     * An empty function to be overridden by a specific Pokemon.
     * @param pk The Pokemon being attacked.
     */
    public void attack2(Pokemon pk) {}

    /**
     * Get Attack 1 Function.
     * To be overridden, returns the name of the first attack.
     * @return null unless overridden.
     */
    public String getAttack1() {
        return null;
    }

    /**
     * Get Attack 2 Function.
     * To be overridden, returns the name of the second attack.
     * @return null unless overridden.
     */
    public String getAttack2() {
        return null;
    }

    /**
     * Get Attack 1 Value Function.
     * Returns the energy value associated with attack 1.
     * @return The energy value of attack 1.
     */
    public int getAtk1Val() {
        return atk1Val;
    }

    /**
     * Get Attack 2 Value Function.
     * Returns the energy value associated with attack 2.
     * @return The energy value of attack 2.
     */
    public int getAtk2Val() {
        return atk2Val;
    }

    /**
     * Add Energy method.
     * Increases energy based on input.
     * @param enToAdd The amount of energy to add.
     */
    public void addEnergy(int enToAdd) {
        energyCount = energyCount + enToAdd;
    }

    /**
     * Get Energy Count Method.
     * Gets the current energy count for a Pokemon.
     * @return The current energy count.
     */
    public int getEnCount() {
        System.out.println("Current energy count for " + this + ": " + energyCount);
        return energyCount;
    }
}
