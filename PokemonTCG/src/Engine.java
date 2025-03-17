import java.util.*;

/**
 * Engine Class.
 * Houses everything the game needs to run.
 * Initiates each player's deck, hand and prize pool, and sets up their active and starting bench.
 * Handles each turn, and checks for cases that would otherwise give an error and handles it appropriately.
 * The game runs using the "run" method, and run contains the loop needed for the game to run properly.
 */
public class Engine {

    /**
     * Player one creation.
     */
    Player p1 = new Player();
    /**
     * Player two creation.
     */
    Player p2 = new Player();

    /**
     * Engine constructor. Sets up the cards for player 1 and player 2 using the "Setup" function in the Player Class.
     */
    public Engine() {
        p1.setup();
        p2.setup();
    }

    /**
     * Turn Order Function.
     * Handles each turn, drawing a card at the start, and thus moving through the different turn options afterward.
     * Once done with the secondary options, the turn will move to the attacking phase and then repeat for player 2.
     */
    private void turnOrder() {
        System.out.println("PLAYER ONE TURN. Drawing card...");
        System.out.println("Card Drawn: " + p1.getDeck().getFirst());
        p1.drawCard();
        System.out.print("Hand: " + p1.getHand());
        turnOptions(p1, false);
        attackingPhase(p1, p2);

        System.out.println("PLAYER TWO TURN. Drawing card...");
        System.out.println("Card Drawn: " + p2.getDeck().getFirst());
        p1.drawCard();
        System.out.print("Hand: " + p2.getHand());
        turnOptions(p2, false);
        attackingPhase(p2, p1);
    }

    /**
     * Turn Options Helper Function.
     * Uses a switch case to display and subsequently run each option before moving to the attacking phase.
     * Used in turnOrder() to display and run options after drawing a card.
     */
    private void turnOptions(Player player, boolean energyUsed) {
        Scanner scan = new Scanner(System.in);

        System.out.println("What would you like to do?\n1. Add to bench\n2. Attach Energy\n3. Play Trainer Cards\n4. Retreat Active Pokemon\n5. Go to attack phase");
        int inp1 = scan.nextInt();
        switch(inp1) {
            case 1:
                benchSetter(player.getHand(), player);
                turnOptions(player, energyUsed);
                break;
            case 2:
                if(energyUsed) {
                    System.out.println("Energy already attached this turn!");
                    turnOptions(player, true);
                }
                energyAttach(player);
                turnOptions(player, true);
                break;
            case 3:
                playTrainerCard(player, false);
                turnOptions(player, energyUsed);
                break;
            case 4:
                retreatActive(player.getBench(), player);
                turnOptions(player, energyUsed);
                break;
            case 5:
                break;
        }
    }

    /**
     * Run Function.
     * Gives the user the option to quit if they do not want to play.
     * Otherwise, the game starts, and will run until one player collects all of their prize cards.
     */
    public void run() {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Welcome to TCG! - To play, enter character 'y'. To quit, enter character 'n'.");
        String yn = scan.nextLine();
        if(yn.equals("y")) {
            initActiveAndBench();
            while(!p1.getPrizePool().isEmpty() && !p2.getPrizePool().isEmpty()) {
                turnOrder();
            }
        } else if(yn.equals("n")) {
            System.out.println("Goodbye.");
            System.exit(0);
        } else {
            System.out.println("INCORRECT INPUT. TRY AGAIN");
            run();
        }
    }

    /**
     * Energy Attach Function.
     * Allows the player to attach an energy to a given Pokemon of choice.
     * @param player The player to select which Pokemon receives an energy.
     */
    private void energyAttach(Player player) {
        int count = 0;

        for(Card card : player.getHand()) {
            if(card instanceof Energy) {
                count++;
            }
        }
        if(count == 0) {
            System.out.println("No energy to use!");
            return;
        }

        Scanner scan = new Scanner(System.in);
        Pokemon pkActive = (Pokemon) player.getActiveCard();

        ArrayList<String> pkmnOptions = new ArrayList<>();
        for(int i = 0; i < player.getBench().size(); i++) {
            pkmnOptions.add(((i+1) + ". " + player.getBench().get(i)));
        }
        System.out.println("Which Pokemon do you wish to add energy to?");
        System.out.println("1. " + pkActive);
        System.out.println("Bench: " + pkmnOptions);

        int inp = scan.nextInt();
        if(inp == 1) {
            pkActive.addEnergy(1);
        } else {
            Pokemon temp = (Pokemon) player.getBench().get(inp - 1);
            temp.addEnergy(1);
        }
        for(Card card : player.getHand()) {
            if(card instanceof Energy) {
                player.getHand().remove(card);
                break;
            }
        }

    }

    /**
     * Play Trainer Card Function.
     * Plays any amount of Trainer Cards from the given player, unless the card is a Supporter.
     * If Supporter, the boolean will be set to true as only one Supporter can be played per turn.
     * @param player The player who will use Trainer Cards.
     * @param supporterUsed The boolean value of if a Supporter Card was used.
     */
    private void playTrainerCard(Player player, boolean supporterUsed) {
        int count = 0;

        for(Card card : player.getHand()) {
            if(card instanceof Trainer) {
                count++;
            }
        }
        if(count == 0) {
            System.out.println("No Trainer cards to use!");
            return;
        }
        Scanner scan = new Scanner(System.in);

        ArrayList<String> trainerOptions = new ArrayList<>();
        for(int i = 0; i < player.getBench().size(); i++) {
            trainerOptions.add(((i+1) + ". " + player.getHand().get(i)));
        }
        System.out.println("Which Trainer card do you wish to use? 0 to exit menu.");
        System.out.println("Cards in hand: " + trainerOptions);

        int inp = scan.nextInt();
        if(inp == 0) {
            return;
        }
        if(player.getHand().get(inp - 1) instanceof ProfOak) {
            if(supporterUsed) {
                System.out.println("Only one Supporter card can be used per turn!");
                playTrainerCard(player, supporterUsed);
            }
            supporterUsed = true;
            ((ProfOak) player.getHand().get(inp - 1)).ability(player);
            player.getDiscardPile().add(player.getHand().remove(inp-1));
            playTrainerCard(player, supporterUsed);
        } else {
            ((Trainer) player.getHand().get(inp - 1)).ability(player);
            player.getDiscardPile().add(player.getHand().remove(inp-1));
            playTrainerCard(player, supporterUsed);
        }
    }

    /**
     * Init Active and Bench method.
     * Sets the initial Active Card and Bench for both players, and is used at the start of a game.
     */
    private void initActiveAndBench() {
        System.out.println("Set the active Pokemon for player 1.");
        System.out.print("P1 Hand: " + p1.getHand());
        System.out.println("Select 1 up to 7(max hand) to choose.");
        initActiveCard(p1.getHand(), p1);
        System.out.println("Current Active Pokemon P1: " + p1.getActiveCard());

        System.out.println("Set any Pokemon to player 1's bench.");
        System.out.print("P1 Hand: ");
        benchSetter(p1.getHand(), p1);

        System.out.println("Set the active Pokemon for player 2.");
        System.out.print("P2 Hand: " + p2.getHand());
        initActiveCard(p2.getHand(), p2);

        System.out.println("Set any Pokemon to player 2's bench.");
        System.out.print("P2 Hand: " + p2.getHand());
        benchSetter(p2.getHand(), p2);
    }

    /**
     * Init Active Card Helper Function.
     * Sets the initial Active Card for the given player, pulling from their original hand.
     * Uses the SetActiveCard Function in the Player Class.
     * Used in InitActiveAndBench to set the Active Cards.
     * @param array The given hand to pull from.
     * @param player The player to set the Active Card for.
     */
    private void initActiveCard(ArrayList<Card> array, Player player) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Select card number to set as active card. Must be a Pokemon!");
        int inp = scan.nextInt();

        if(player.getHand().get(inp - 1) instanceof Pokemon) {
            player.setActiveCard(inp);
        } else {
            System.out.println("ERROR: Not a Pokemon! Try again!");
            initActiveCard(array, player);
        }
    }

    /**
     * Active From Bench Function.
     * Sets a new Active Card from the Bench using the SetActiveFromBench Function in the Player Class.
     * @param bench
     * @param player
     */
    private void activeFromBench(ArrayList<Card> bench, Player player) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Select card number to set as active card. Must be a Pokemon!");
        int inp = scan.nextInt();

        if(player.getHand().get(inp - 1) instanceof Pokemon) {
            player.setActiveFromBench(inp);
        } else {
            System.out.println("ERROR: Not a Pokemon! Try again!");
            activeFromBench(bench, player);
        }
    }

    /**
     * Retreat Active Function.
     * Retreats the Active Pokemon given enough energy, removes said energy, and then replaces
     * the former Active Pokemon with a new one from the Bench.
     * @param bench The Bench to pull the Pokemon from and add to.
     * @param player The given Player to set the Active Pokemon for.
     */
    private void retreatActive(ArrayList<Card> bench, Player player) {
        Pokemon pk1 = (Pokemon) player.getActiveCard();

        if(pk1 instanceof Bidoof) {
            if(pk1.energyCheck(3)) {
                pk1.removeEnergy(3);
                player.nullifyActive();
                activeFromBench(player.getBench(), player);
                player.getBench().add(pk1);
            } else {
                System.out.println("Cannot retreat! Not enough energy!");
            }
        } else {
            if(pk1.energyCheck(2)) {
                pk1.removeEnergy(2);
                player.nullifyActive();
                initActiveCard(player.getBench(), player);
                player.getBench().add(pk1);
            } else {
                System.out.println("Cannot retreat! Not enough energy!");
            }
        }
    }

    /**
     * Bench Setter Helper Function.
     * Sets the bench for a player, and is used to initially set the beginning Bench.
     * Uses the SetCardToBench function in the Player Class.
     * Used in InitActiveAndBench Function to set the bench.
     * @param array The Bench to set Pokemon to.
     * @param player The given Player to use the function.
     */
    private void benchSetter(ArrayList<Card> array, Player player) {
        if(player.getBench().size() == 5) {
            System.out.println("Bench is full!!");
            return;
        }
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Select card number to add to bench, up to 5 Pokemon. Select 0 to finish.");
        System.out.println(player.getHand());
        int inp = scan.nextInt();

        if(inp == 0) {
            return;
        }

        if(player.getHand().get(inp - 1) instanceof Pokemon) {
            player.setCardToBench(inp);
            benchSetter(array, player);
        } else if(inp > player.getHand().size()) {
            System.out.println("ERROR: Number is outside of current hand size!");
            benchSetter(array, player);
        } else {
            System.out.println("ERROR: Not a Pokemon! Try again!");
            benchSetter(array, player);
        }
    }

    /**
     * Attacking Phase Function.
     * Handles the entirety of the attacking phase.
     * Checks for energy, checks for proper usage, and will also handle knock-outs.
     * @param p1 The given Player attacking.
     * @param p2 The given Player being attacked.
     */
    private void attackingPhase(Player p1, Player p2) {
        Pokemon pk1 = (Pokemon) p1.getActiveCard();
        Pokemon pk2 = (Pokemon) p2.getActiveCard();
        if(!pk1.energyCheck(pk1.getAtk1Val())) {
            System.out.println("Not enough energy! Cannot attack! Turn ended!!");
            return;
        }

        System.out.println("Current Active Card: " + p1.getActiveCard());
        System.out.println("Active Card HP: " + pk1.getHP());
        System.out.println("Opponent's Active Card: " + p2.getActiveCard());
        System.out.println("Opponent Active Card HP: " + pk2.getHP());

        System.out.println("What would you like to do?");
        if(pk1 instanceof Lugia) {
            System.out.println("1. " + pk1.getAttack1() + ", 2. " + pk1.getAttack2());
        } else {
            System.out.println("1. " + pk1.getAttack1());
        }

        attackHandler(p1, p2);
        if(pk2.getHP() <= 0) {
            System.out.println("Opponent's " + pk2 + " has been knocked out! You earned a prize card!");
            p1.takePrizeCard();
            if(p1.getPrizePool().isEmpty()) {
                System.out.println("Game Over! You win!");
                System.exit(0);
            }
            p2.getDiscardPile().add(pk2);
            p2.nullifyActive();
            System.out.println("Opponent must set new active Pokemon!");
            initActiveCard(p2.getBench(), p2);
        }
    }

    /**
     * Attack Handler Helper Function.
     * Handles the energy checks and actual moves of Pokemon, and whether the attack is correct.
     * @param p1
     * @param p2
     */
    private void attackHandler(Player p1, Player p2) {
       Scanner scan = new Scanner(System.in);
       Pokemon pk1 = (Pokemon) p1.getActiveCard();
       Pokemon pk2 = (Pokemon) p2.getActiveCard();

       int inp = scan.nextInt();
       if(pk1 instanceof Lugia) {
           if(inp != 1 && inp != 2) {
               System.out.println("ERROR: Not a correct attack!");
               attackHandler(p1, p2);
           }

           switch(inp) {
               case 1:
                   pk1.attack(pk2);
                   System.out.println("Opponent's HP: " + pk2.getHP());
                   break;
               case 2:
                   if(!pk1.energyCheck(pk1.getAtk2Val())) {
                       System.out.println("Not enough energy for Aeroblast!");
                       attackHandler(p1, p2);
                   }
                   pk1.attack2(pk2);
                   System.out.println("Opponent's HP: " + pk2.getHP());
                   break;
           }
       } else {
           if(inp != 1) {
               System.out.println("ERROR: Not a correct attack!");
               attackHandler(p1, p2);
           }
           if(pk1 instanceof Drampa) {
               pk1.attack(pk2, p1);
               System.out.println("Opponent's HP: " + pk2.getHP());
           } else {
               pk1.attack(pk2);
               System.out.println("Opponent's HP: " + pk2.getHP());
           }
       }
    }

}