import java.util.Random;

public class MontyHall {
    private int door;
    private int door2;
    private int win;
    private int choice;

    //Method to check probability when staying with original choice.
    public int stayChoice(int runs) {
        int count = 0; //initialize counter

        for(int i = 0; i < runs; i++) {
            Random rnd = new Random();

            win = rnd.nextInt(3); //set the winning door
            choice = rnd.nextInt(3); //set the player choice

            do {
                door = rnd.nextInt(3); //set the first dud door
            } while (win == door || door == choice);

            door2 = 3 - door - win; //set the second dud door

            if (choice == win) {
                count++; //if the choice was the car, you win and increase counter.
            }
        }
        System.out.println("Times won out of " + runs + ": " + count); //prints the wins in the given runs
        System.out.println("Percentage of winning by staying: " + ((double) count / runs)); //prints the probability of winning by keeping your choice (Monte Carlo)
        return count;
    }

    //Method to check probability to win when switching doors.
    public int switchChoice(int runs) {
        int count = 0; //Initialize counter
        for(int i = 0; i < runs; i++) { //set how many runs you perform in the loop.
            Random rnd = new Random();

            win = rnd.nextInt(3); //set the winning door
            choice = rnd.nextInt(3); //set the player choice
            do {
                door = rnd.nextInt(3); //set the first dud door
            } while (win == door || door == choice);

            door2 = 3 - door - choice; //set the second dud door

            choice = door2; //switch your door case.

            if(choice == win) {
                count++; //increase your counter if you win by switching
            }
        }
        System.out.println("Times won out of " + runs + ": " + count); //printing the number of wins out of the runs.
        System.out.println("Percentage of winning by staying: " + ((double) count / runs)); //prints the percentage of winning (Monte Carlo)
        return count;
    }
}
