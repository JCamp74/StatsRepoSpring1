//CARD USED: https://bulbapedia.bulbagarden.net/wiki/Potion_(Base_Set_94)
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Potion (Base Set 94)
 * Heals a Pokemon for 30 damage. Checks to see if caps base health, or if the card is not a legal index.
 */
public class Potion extends Trainer {

    /**
     * Ability Function, overridden from Trainer.
     * Heals a Pokemon for 30 damage, and checks cases accordingly to not exceed base health value.
     * @param player
     */
    @Override
    public void ability(Player player) {
        Scanner scan = new Scanner(System.in);
        int count = 1;
        ArrayList<String> options = new ArrayList<>();

        for(Card card : player.getBench()) {
            count++;
            options.add((player.getBench().indexOf(card) + 2) + ". " + card);
        }

        System.out.println("Which pokemon would you like to heal?");
        System.out.println("Active Pokemon (1): " + player.getActiveCard());
        System.out.println("Bench: " + options);

        int inp = scan.nextInt();
        if(inp == 1) {
            Pokemon activePk = (Pokemon) player.getActiveCard();
            if(activePk.getHP() < activePk.getBaseHP()) {
               if((activePk.getHP() + 30) > activePk.getBaseHP()) {
                   activePk.setHP(activePk.getBaseHP());
               } else {
                   activePk.setHP(activePk.getHP() + 30);
               }
            }
        } else if(1 < inp && inp < count) {
            Pokemon pkToHeal = (Pokemon) player.getBench().get(inp - 1);
            if(pkToHeal.getHP() < pkToHeal.getBaseHP()) {
                if((pkToHeal.getHP() + 30) > pkToHeal.getBaseHP()) {
                    pkToHeal.setHP(pkToHeal.getBaseHP());
                } else {
                    pkToHeal.setHP(pkToHeal.getHP() + 30);
                }
            }
        } else {
            System.out.println("ERROR: Not a legal card/index!");
            ability(player);
        }

    }

    /**
     * ToString Function.
     * @return The Card's name, "Potion"
     */
    @Override
    public String toString() {
        return "Potion";
    }
}
