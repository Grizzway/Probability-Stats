package Assignment3;

public class Tests {
    public static void main(String[] args) {
        MontyHallSimulator MHS = new MontyHallSimulator();
        System.out.println("The probability of picking the correct door for 10,000 runs without changing the door: "
                + MHS.Simulate(10000, false));

        System.out.println("The probability of picking the correct door for 10,000 runs with changing the door: "
                + MHS.Simulate(10000, true));

        /*
        * Answer to question A:
        * The sample space can be assumed to be S = {G, D1, D2} where G is the prize and D1 and D2 are the dud prizes.
        * Each outcome has an equal probability of 1/3
        * So selecting the prize on the first try has a probability of 1/3.
        *
        * Answer to question B:
        * After the contestant is given the option to switch the door, there are one of two scenarios presented.
        * By staying with their original choice, the probability is still 1/3, given the original probability
        * By choosing the other door, the odds are increased to 2/3, since the host has revealed one of the duds.
        * The results of the program reflect these probabilities.
        *
        */
    }
}
