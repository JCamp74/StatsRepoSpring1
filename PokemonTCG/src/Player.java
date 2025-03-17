import java.util.*;

/**
 * Player Class.
 * Houses the deck, hand, bench, Active Card, prize pool, and discard pile for each instance.
 * Contains methods to modify the cards within the given player.
 */
public class Player {
    
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    private ArrayList<Card> prizePool;
    private Card activeCard;
    private ArrayList<Card> bench;
    private ArrayList<Card> discardPile;
    private CardSetup helper = new CardSetup();

    /**
     * Constructor for the Player Class.
     * Sets the deck, hand, prize pool, active card, bench, and discard pile up for use.
     */
    public Player() {
        deck = new ArrayList<>();
        hand = new ArrayList<>();
        prizePool = new ArrayList<>();
        activeCard = null;
        bench = new ArrayList<>();
        discardPile = new ArrayList<>();

    }

    /**
     * Get Deck Function.
     * @return The Player's deck.
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Get Hand Function.
     * @return The Player's hand.
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Get Prize Pool Function.
     * @return The Player's prize pool.
     */
    public ArrayList<Card> getPrizePool() {
        return prizePool;
    }

    /**
     * Get Active Card Function.
     * @return The Player's Active Card.
     */
    public Card getActiveCard() {
        return activeCard;
    }

    /**
     * Get Bench Function.
     * @return The Player's bench.
     */
    public ArrayList<Card> getBench() {
        return bench;
    }

    /**
     * Get Discard Pile Function.
     * @return The Player's discard pile.
     */
    public ArrayList<Card> getDiscardPile() {
        return discardPile;
    }

    /**
     * Set Active Card Function.
     * Changes the current Active Card for the Player by removing the card from their hand.
     * @param i The card of choice given the selection from the hand.
     */
    public void setActiveCard(int i) {
        activeCard = hand.remove(i - 1);
    }

    /**
     * Set Active From Bench Function.
     * Sets the Active Card for the Player from the given selection in the bench.
     * @param i The card of choice given the selection from the Bench.
     */
    public void setActiveFromBench(int i) {
        activeCard = bench.remove(i - 1);
    }

    /**
     * Nullify Active Function.
     * Nullifies the current Active Pokemon.
     */
    public void nullifyActive() {
        activeCard = null;
    }

    /**
     * Set Card To Bench Function.
     * Sets the card of choice from the Player's hand to their bench.
     * @param i The card of choice given the selection from the hand.
     */
    public void setCardToBench(int i) {
        bench.add(hand.get(i - 1));
        hand.remove((i - 1));
    }

    /**
     * Draw Card Function.
     * Draws a card from the Player's deck, and removes it from the deck at the same time.
     */
    public void drawCard() {
        hand.add(deck.removeFirst());
    }

    /**
     * Setup Function.
     * Uses the CardSetup class to initiate the deck, hand and prize pool for the Player.
     */
    public void setup() {
        helper.initiateCards(deck, hand, prizePool);
    }

    /**
     * Take Prize Card Function.
     * Adds the first card from the prize pool to the Player's hand, and removes it from the prize pool.
     */
    public void takePrizeCard() {
        hand.add(prizePool.removeFirst());
    }
}
