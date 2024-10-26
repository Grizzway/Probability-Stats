package Assignment2;

import Assignment1.StatsLibrary;

import java.util.ArrayList;
import java.util.Random;

public class BirthdayCalc {

    private final StatsLibrary statLib = new StatsLibrary();
    private final Random random = new Random();

    /**
     * Generates a list of Person objects, each assigned a random birthday.
     *
     * @param amountOfPeople The number of Person objects to generate.
     * @return An ArrayList of Person objects, each with a random birthday between 1 and 365.
     */
    private ArrayList<Person> GeneratePeople(int amountOfPeople){
        ArrayList<Person> people = new ArrayList<>();

        for(int i = 0; i < amountOfPeople; i++)
            people.add(new Person(random.nextInt(365) + 1));

        return people;
    }

    /**
     * Checks if there is a shared birthday among a list of Person objects.
     *
     * @param people An ArrayList of Person objects to check for shared birthdays.
     * @return True if at least two people share the same birthday, otherwise false.
     */
    private boolean HasSharedBirthday(ArrayList<Person> people){
        int[] birthdayCounts = new int[365];

        for(Person person : people){
            int birthday = person.GetBirthday();
            if(birthdayCounts[birthday - 1] > 0)
                return true; //return true if there is someone with this birthday
            birthdayCounts[birthday - 1]++;
        }
        return false; //no shared birthdays
    }

    /**
     * Calculates the probability of at least two people sharing the same birthday
     * in a group of a specified size over multiple trials.
     *
     * @param amountOfPeople The number of people in each trial.
     * @param timesToRun The number of trials to run for probability calculation.
     * @return The probability of a shared birthday as a double.
     */
    public double CalculateProbabilitySameBirthday(int amountOfPeople, int timesToRun) {
        ArrayList<Integer> probabilities = new ArrayList<>();

        for (int i = 0; i < timesToRun; i++) {
            ArrayList<Person> people = GeneratePeople(amountOfPeople);

            if (HasSharedBirthday(people))
                probabilities.add(1); // Shared birthday found in this run
            else
                probabilities.add(0); // No shared birthday
        }
        return statLib.Mean(probabilities);
    }
}
