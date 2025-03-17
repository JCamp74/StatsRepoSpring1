//Card Used: https://bulbapedia.bulbagarden.net/wiki/Switch_(Base_Set_95)
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Switch (Base Set 95)
 * Switches your current Active Card for one of your benched Pokemon.
 */
public class Switch extends Trainer {

    /**
     * Ability Function, overridden from Trainer.
     * Switches your Active Pokemon with one of your benched Pokemon.
     * @param player
     */
    @Override
    public void ability(Player player) {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> benchOptions = new ArrayList<>();

        for(Card card : player.getBench()) {
            benchOptions.add((player.getBench().indexOf(card) + 1) + ". " + card);
        }

        System.out.println("Swap your Active Card with one of the Pokemon below!");
        System.out.println("Current Active Card: " + player.getActiveCard());

        System.out.println("Benched Pokemon: " + benchOptions);
        int inp = scan.nextInt();
        if(player.getBench().get(inp - 1) instanceof Pokemon) {
            Pokemon temp = (Pokemon) player.getActiveCard();
            player.setActiveFromBench(inp);
            player.getBench().add(temp);
        } else {
            System.out.println("ERROR: Not a Pokemon!");
            ability(player);
        }
    }

    /**
     * ToString Function.
     * @return The Card's name, "Switch"
     */
    @Override
    public String toString() {
        return "Switch";
    }
}
