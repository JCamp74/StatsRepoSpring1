/*
m*n rule
Explanation
    There are 6 faces on each die.
    There are m * n possible combinations.
    m * n = (6) (6) = 36

    We can further extend to tossing a coin 3 times.
        m * n * o
        2x2x2 = 8

        record birthday of 20 ppl.
        ignore leap year and assume 365 days.
        find the number of points in sample space S for this experiment.
        If we assume that each of the possible sets of birthdays is equiprobable,
        what is the probability that each person in the 20 has a diff birthday?

        We're shooting for DIFFERENT birthdays according to problem
        Total sample space is 365, 20 times.
        Numerator is 365, first person could have any birthday
            Second person is 365, he can't have the first guy's birthday
                Third is 363, so on so forth.
            P(A) = (n(sub_a) / N) = ((365 * 364 * .... * 346) / ((365)^20 )) = .5886.
            n(sub_a) = (val * val * ..... * val)
            val in this case would be 365 (days in the year (parameter))




Permutations
    An ordered arrangement of r distinct objects is called a permutation.
    The number of ways of ordering n distinct object (total) taken r at a time
    (how many) is denoted P(hyp_n)(sub_r), and by the multiplication rule we have
    that: P(hyp_n)(sub_r) = n*(x-1)(n-2)(n-3)....(n-r + 2)(n-r + 1) = n! / (n-r)!

Multinomial Coefficients
    The number of ways of partitioning n distinct objects into k disjoint subsets of sizes.
    See theorem 2.3 in text Page 44.

    A uni Department comprises 40 faculty members. Two committees of 12 faculty members,
    and two committees of 8 faculty members, are needed. Th
 */


public class Main {
    public static void main(String[] args) {
        Person p = new Person();
        System.out.println("Percentage to find a match: " + p.birthdayCheck(100000, 20));

    }
}