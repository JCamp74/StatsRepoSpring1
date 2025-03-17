//Card used: https://bulbapedia.bulbagarden.net/wiki/Bidoof_(XY_106)
import java.util.*;

/**
 * Bidoof (XY 106)
 * Has 60 base health, is a base Pokemon, and is colorless.
 * Bidoof has one attack, Hyper Fang, which requires 2 energy.
 * Bidoof has a retreat cost of 3 energy.
 */
public class Bidoof extends Pokemon {

    /**
     * Constructor for Bidoof.
     * Sets the base health to 60, and the energy value for their attack at 2.
     */
    public Bidoof() {
        super(60, 2);
    }

    /**
     * Hyper Fang attack. Overridden from Pokemon Class which houses an empty attack function.
     * Hyper Fang flips a coin, and if tails fails. If heads, deals 40 damage.
     * @param monst The Opponent's Pokemon to be damaged.
     */
    @Override
    public void attack(Pokemon monst) {
        System.out.println("Bidoof used Hyper Fang!");
        Random coin = new Random();
        int flip = coin.nextInt(0, 2);
        if(flip == 1) { //0 is heads in this instance.
            monst.setHP(monst.getHP() - 40);
        } else {
            System.out.println("Flip failed! The attack did nothing!");
        }
    }

    /**
     * GetAttack1 Function, overridden from Pokemon Class.
     * @return Bidoof's attack, Hyper Fang.
     */
    @Override
    public String getAttack1() {
        return "Hyper Fang";
    }

    /**
     * ToString Function.
     * @return The Pokemon's name, Bidoof.
     */
    @Override 
    public String toString() {
        return "Bidoof";
    }
}
