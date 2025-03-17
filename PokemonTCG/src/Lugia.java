//Card used: https://bulbapedia.bulbagarden.net/wiki/Lugia_(XY_Promo_156)
import java.util.Random;

/**
 * Lugia (XY Promo 156)
 * Has 120 base health, is a Base Pokemon, and is colorless.
 * Lugia has two attacks, Gust & Aeroblast, which require 2 and 4 energy respectively.
 * Lugia has a retreat cost of 2 energy.
 */
public class Lugia extends Pokemon {

    /**
     * Constructor for Lugia.
     * Sets the base health to 120, the energy value for Gust at 2 and the value for Aeroblast at 4.
     */
    public Lugia() {
        super(120, 2, 4);
    }

    /**
     * Gust Attack.
     * Hits the Opponent's Active Pokemon for 30 damage.
     * @param monst The Opponent's Active Pokemon to be damaged.
     */
    @Override
    public void attack(Pokemon monst) {
        System.out.println("Lugia used Gust!");
        monst.setHP(monst.getHP() - 30);
    }

    /**
     * Aeroblast Attack.
     * Deals an initial damage of 80, but adds 20 damage for each heads across 2 coin flips.
     * @param monst
     */
    @Override
    public void attack2(Pokemon monst) {
        System.out.println("Lugia used Aeroblast!");
        Random coin = new Random();
        int count = 0;
        for(int i = 0; i < 2; i++) {
            count += coin.nextInt(0, 2);
        }
        int damage = 80 + count*20;
        monst.setHP(monst.getHP() - damage);
        System.out.println("Lugia dealt " + damage + " damage!");
    }

    /**
     * GetAttack1 Function, overridden from Pokemon Class.
     * @return Lugia's attack, Gust.
     */
    @Override
    public String getAttack1() {
        return "Gust";
    }

    /**
     * GetAttack2 Function, overridden from Pokemon Class.
     * @return Lugia's attack, Aeroblast.
     */
    @Override
    public String getAttack2() {
        return "Aeroblast";
    }

    /**
     * ToString Function.
     * @return The Pokemon's name, Lugia.
     */
    @Override 
    public String toString() {
        return "Lugia";
    }
}
