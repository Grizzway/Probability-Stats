package Assignment2;

public class Tests {

    public static void main(String[] args) {
        BirthdayCalc bdayCalc = new BirthdayCalc();

        System.out.println("The probability of 2 people out of 30 having the same birthday on an average of 10 runs: "
                + bdayCalc.CalculateProbabilitySameBirthday(30, 10));

        System.out.println("\nThe probability of 2 people out of 30 having the same birthday on an average of 1,000 runs: "
                + bdayCalc.CalculateProbabilitySameBirthday(30, 1000));

        System.out.println("\nThe probability of 2 people out of 30 having the same birthday on an average of 10,000 runs: "
                + bdayCalc.CalculateProbabilitySameBirthday(30, 10000));

        System.out.println("\nThe probability of 2 people out of 30 having the same birthday on an average of 100,000 runs: "
                + bdayCalc.CalculateProbabilitySameBirthday(30, 100000));

        System.out.println("\nThe probability of 2 people out of 15 having the same birthday on an average of 1,000 runs: "
                + bdayCalc.CalculateProbabilitySameBirthday(15, 1000));
    }
}
