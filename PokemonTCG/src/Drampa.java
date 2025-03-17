//Card used: https://bulbapedia.bulbagarden.net/wiki/Drampa_(Sword_%26_Shield_149)
/**
 * Drampa (Sword 26/Shield 149)
 * Has 130 base health, is a Base Pokemon, and is colorless.
 * Drampa has one attack, Dragon Pulse which requires 1 energy.
 * Drampa has a retreat cost of 2 energy.
 */
public class Drampa extends Pokemon {

    /**
     * Constructor for Drampa.
     * Sets the base health to 130, and the energy value for their attack at 2.
     */
    public Drampa() {
        super(130, 2);
    }

    /**
     * Dragon Pulse attack. Overridden from Pokemon Class which houses an empty attack function.
     * Dragon Pulse deals 80 damage, and then discards the top two cards in your deck.
     * @param monst The Opponent's Pokemon to be damaged.
     * @param player The player to discard cards from.
     */
    @Override
    public void attack(Pokemon monst, Player player) {
        System.out.println("Drampa used Dragon Pulse! Hit for 80 damage!");
        monst.setHP(monst.getHP() - 80);

        player.getDiscardPile().add(player.getDeck().removeFirst());
        player.getDiscardPile().add(player.getDeck().removeFirst());
        System.out.println("Top 2 cards in your deck have now been discarded.");
    }

    /**
     * GetAttack1 Function, overridden from Pokemon Class.
     * @return Drampa's attack, Dragon Pulse.
     */
    @Override
    public String getAttack1() {
        return "Dragon Pulse";
    }

    /**
     * ToString Function.
     * @return The Pokemon's name, Drampa.
     */
    @Override 
    public String toString() {
        return "Drampa";
    }
}
