//CARD USED: https://bulbapedia.bulbagarden.net/wiki/Pok%C3%A9_Ball_(Jungle_64)
import java.util.Random;

/**
 * Poke Ball (Jungle 64)
 * Searches your deck for a Pokemon, finds it, then adds it to your hand.
 */
public class PokeBall extends Trainer {

    /**
     * Ability Function, overridden from Trainer.
     * Searches your deck for a Pokemon, finds it, then adds it to your hand.
     * @param player The given Player to search through their deck and add to hand.
     */
    @Override
    public void ability(Player player) {
        Random coin = new Random();
        int flip = coin.nextInt(0, 2);
        if(flip == 0) { //0 is heads
            for(Card card : player.getDeck()) {
                if(card instanceof Pokemon) {
                    System.out.println(card + " found! Adding to hand.");
                    player.getHand().add(card);
                    player.getDeck().remove(card);
                    break;
                }
            }
        }
    }

    /**
     * ToString Function.
     * @return The Card's name, "Poke Ball"
     */
    @Override
    public String toString() {
        return "Poke Ball";
    }
}
