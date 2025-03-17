import java.util.Random;
import java.util.ArrayList;

public class Person {
    private int day;
    private double count = 0;
    ArrayList<Integer> birthdays= new ArrayList<Integer>();

    public double birthdayCheck(double runs, int index) {
        ArrayList<Integer> birthdays = new ArrayList<Integer>();
        Random rand = new Random();

        for (int i = 0; i < runs; i++) {
            birthdays.clear();
            for (int j = 0; j < index; j++) {
                day = rand.nextInt(1, 366);
                birthdays.add(j, day);
            }
            for (int k = 0; k < birthdays.size() - 1; k++) {
                for (int l = k + 1; l < birthdays.size(); l++) {
                    if (birthdays.get(k).equals(birthdays.get(l))) {
                        count++;
                        k = birthdays.size();
                        break;
                    }
                }
            }
        }
        return (double) count / runs;
    }


}
