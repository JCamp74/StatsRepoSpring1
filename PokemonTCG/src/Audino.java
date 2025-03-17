//Card used: https://bulbapedia.bulbagarden.net/wiki/Audino_(Black_%26_White_87)
import java.util.Random;

/**
 * Audino (Black 26/White 87)
 * Has 80 base health, is a Base Pokemon, and is colorless.
 * Audino has one attack, Doubleslap, which requires two energy.
 * Audino has a retreat cost of 2 energy.
 */
public class Audino extends Pokemon {

    /**
     * Constructor for Audino.
     * Sets the base health to 80, and the energy value for their attack at 2.
     */
    public Audino() {
        super(80, 2);
    }

    /**
     * Doubleslap attack. Overridden from Pokemon Class which houses an empty attack function.
     * Doubleslap flips two coins, and deals 30 damage for each heads landed on.
     * @param monst The Opponent's Pokemon to be damaged.
     */
    @Override
    public void attack(Pokemon monst) {
        System.out.println("Audino used Doubleslap!");
        Random coin = new Random();
        int count = 0;
        for(int i = 0; i < 2; i++) {
            count += coin.nextInt(2); //1 is heads in this instance.
        }
        int damage = count * 30;
        System.out.println("Coin flipped on heads " + count + " times!");

        monst.setHP(monst.getHP() - damage);
        System.out.println("Doubleslap dealt " + damage + " damage!");

    }

    /**
     * GetAttack1 Function, overridden from Pokemon Class.
     * @return Audino's attack, Doubleslap.
     */
    @Override
    public String getAttack1() {
        return "Doubleslap";
    }

    /**
     * ToString Function.
     * @return The Pokemon's name, Audino.
     */
    @Override 
    public String toString() {
        return "Audino";
    }
}
