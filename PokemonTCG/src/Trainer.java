/**
 * Trainer Class.
 * Sets up the functions for the specific Trainer card to use.
 * Functions created here are empty, and simply need to be overridden.
 */
public class Trainer extends Card {

    /**
     * Ability Function.
     * Sets up the shell for the Trainer Card ability to be programmed.
     *
     * @param player The given Player to be affected.
     */
    public void ability(Player player) {}

    /**
     * ToString Function.
     * @return The name "Trainer"
     */
    @Override
    public String toString() {
        return "Trainer";
    }
}
