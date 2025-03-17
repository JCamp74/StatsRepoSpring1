import java.util.ArrayList;
import java.util.Random;

/**
 * Card Game Monte Carlo Class.
 * Constructs the deck, hand, and prizepool for simulation.
 * Not used anywhere else aside from running simulations.
 */
public class CardGameMonteCarlo {

    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    private ArrayList<Card> prizePool;

    /**
     * Constructor that sets the deck, hand and prize pool as new ArrayLists.
     */
    public CardGameMonteCarlo() {
        deck  = new ArrayList<>();
        hand = new ArrayList<>();
        prizePool = new ArrayList<>();
    }

    /**
     * Fill Deck Function.
     * Fills the deck with Pokemon, rare candies, and energy.
     *
     * @param pkmAdd The amount of Pokemon to add to the deck.
     * @param candyAdd The amount of Rare Candies to add to the deck.
     */
    public void fillDeck(int pkmAdd, int candyAdd) {
        for(int i = 0; i < pkmAdd; i++) {
            deck.add(new Pokemon());
        }
        for(int i = 0; i < candyAdd; i++) {
            deck.add(new RareCandy());
        }
        for(int i = 0; i < 60 - pkmAdd - candyAdd; i++) {
            deck.add(new Energy());
        }
    }

    /**
     * Draw Hand Function.
     * Draws the first seven cards from the deck and adds them to your hand.
     */
    public void drawHand() {
        Random rng = new Random();
        for(int i = 0; i < 7; i++) {
            int cardToTakeIndex = rng.nextInt(deck.size());
            hand.add(deck.getFirst());
            deck.removeFirst();
        }
    }

    /**
     * Draw Prize Pool Function.
     * Draws the next 6 cards to add to the prize pool, after your hand was created.
     */
    public void drawPrizePool() {
        for(int i = 0; i < 6; i++) {
            prizePool.add(deck.get(i));
            deck.removeFirst();
        }
        
    }

    /**
     * Has Pokemon Function.
     * Returns a true or false value if any given card in your hand is a Pokemon.
     *
     * @return The boolean value if your hand contains a Pokemon or not.
     */
    public boolean hasPokemon() {
        for(Card singleCard : hand) {
            if(singleCard instanceof Pokemon) {
                return true;
            }
        }
        return false;
    }

    /**
     * Bricked Function.
     * Searches your prize pool to check if your entire amount of Rare Candies
     * are within your prizes. If so, returns true as your deck is bricked.
     *
     * @param currentCandyAmount The current Rare Candy amount for testing.
     * @return The boolean value if your deck is bricked.
     */
    public boolean bricked(int currentCandyAmount) {
        int rareCandyCount = 0;
        for(Card card : prizePool) {
            if(card instanceof RareCandy) {
                rareCandyCount++;
            }
        }
        return rareCandyCount == currentCandyAmount;
    }

    /**
     * Hand and Prize Function.
     * Draws the hand after deck creation, and then draws the prize pool.
     */
    public void handAndPrize() {
        drawHand();
        drawPrizePool();
    }

    /**
     * Monte Carlo Function.
     * Runs the Monte Carlo simulation to see if you need to
     * perform a mulligan, and then subsequently checks to see if your deck is bricked.
     * Prints the results after each trial.
     */
    public void monteCarlo() {
        float count = 0;
        float prizeCount = 0;

        for(int a = 1; a < 5; a++) {
            System.out.println("Testing with " + a + " candies in deck");
            for(int i = 1; i < 61; i++) {
                for(int x = 0; x < 100000; x++) {
                    fillDeck(i, a);
                    handAndPrize();

                    if(hasPokemon()) {
                        count++;
                    }
                    if(bricked(a)) {
                        prizeCount++;
                    }
                    deck.clear();
                    hand.clear();
                    prizePool.clear();
                }
                System.out.println("Probability of Pokemon: " + (count / 100000));
                System.out.println("Probability of all Rare Candy in Prize Pool: " + (prizeCount / 100000));
                System.out.println("\n");
                count = 0;
                prizeCount = 0;

            }
        }
    }
}

