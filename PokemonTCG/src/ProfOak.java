//CARD USED: https://bulbapedia.bulbagarden.net/wiki/Professor_Oak_(Base_Set_88)
/**
 * Professor Oak (Base Set 88)
 * Discards your hand, then draws 7 cards.
 */
public class ProfOak extends Trainer {

    /**
     * Ability Function, overridden from Trainer.
     * Discards your hand, then draws 7 cards.
     * @param player The given Player to discard and draw from.
     */
    @Override
    public void ability(Player player) {
        System.out.println("Discarding hand.");
        for(Card card : player.getHand()) {
            player.getDiscardPile().add(player.getHand().removeFirst());
        }
        System.out.println("Adding cards into hand.");
        for(int i = 0; i < 7; i++) {
            player.drawCard();
        }
    }

    /**
     * ToString Function
     * @return The Card's name, "Professor Oak"
     */
    @Override
    public String toString() {
        return "Professor Oak";
    }
}
