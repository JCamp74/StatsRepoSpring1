//USE THIS TO FIND CARDS: https://bulbapedia.bulbagarden.net/wiki/Category:Colorless-type_Pok%C3%A9mon_cards
import java.util.ArrayList;
import java.util.Collections;

/**
 * Card Setup Class.
 * Houses the methods that sets each player's deck, hand, and prize pool up for gameplay.
 * Performs a hand reset as necessary when there is no Pokemon in the hand, ensuring play is able.
 */
public class CardSetup {

    /**
     * Card Setup Constructor.
     */
    public CardSetup(){}

    /**
     * Fill Deck Function.
     * Fills the deck with 4 of each Pokemon card, 4 of each Trainer card, and the rest with energy.
     * Shuffles the deck after creation to ensure proper play.
     *
     * @param deck The given deck to create.
     */
    public void fillDeck(ArrayList<Card> deck) {
        for(int i = 0; i < 4; i++) { //16 total cards
            deck.add(new Bidoof());
            deck.add(new Audino());
            deck.add(new Drampa());
            deck.add(new Lugia());
        }
        for(int i = 0; i < 4; i++) { //32 total cards after add
            deck.add(new ProfOak());
            deck.add(new PokeBall());
            deck.add(new Potion());
            deck.add(new Switch());
        }
        for(int i = 0; i < 28; i++) { //60 total cards after add
            deck.add(new Energy());
        }
        
        Collections.shuffle(deck);
    }

    /**
     * Draw Hand Function.
     * Draws from the given deck, and adds the card into the given hand.
     *
     * @param deck The deck cards are taken from.
     * @param hand The hand cards are added to.
     */
    public void drawHand(ArrayList<Card> deck, ArrayList<Card> hand) {
        for(int i = 0; i < 7; i++) {
            hand.add(deck.getFirst());
            deck.removeFirst();
        }
    }

    /**
     * Draw Prize Pool Function.
     * Draws from the given deck, and adds cards to the given prize pool.
     *
     * @param deck The deck cards are taken from.
     * @param prizePool The prize pool cards are added to.
     */
    public void drawPrizePool(ArrayList<Card> deck, ArrayList<Card> prizePool) {
        for(int i = 0; i < 6; i++) {
            prizePool.add(deck.getFirst());
            deck.removeFirst();
        }
    }

    /**
     * Initiate Cards Function.
     * Initiates the deck, hand and prize pool for the given player's deck, hand and prize pool.
     * Checks for a mulligan and will reset the hand given necessary.
     *
     * @param deck The deck cards are created in and removed from.
     * @param hand The hand cards are added to from the deck.
     * @param prizePool The prize pool cards are added to from the deck.
     */
    public void initiateCards(ArrayList<Card> deck, ArrayList<Card> hand, ArrayList<Card> prizePool) {
        fillDeck(deck);
        drawHand(deck, hand);
        mulliganReset(deck, hand);
        drawPrizePool(deck, prizePool);
    }

    /**
     * Mulligan Reset Function.
     * Resets the hand so a Pokemon appears, thus ensuring proper play.
     *
     * @param deck The deck to shuffle back into and pull again from.
     * @param hand The hand to reset and draw into.
     */
    public void mulliganReset(ArrayList<Card> deck, ArrayList<Card> hand) {
        int count = 0;
        for(int i = 0; i < 7; i++) {
            if(hand.get(i) instanceof Pokemon) {
                count++;
            }
        }
        if(count >= 1) {
            return;
        } else {
            for(int i = 0; i < 7; i++) {
                deck.add(hand.removeFirst());
            }
            Collections.shuffle(deck);
            drawHand(deck, hand);
            mulliganReset(deck, hand);
        }
    }

}
