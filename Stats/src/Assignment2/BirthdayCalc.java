package Assignment2;

import Assignment1.StatsLibrary;

import java.util.ArrayList;
import java.util.Random;

public class BirthdayCalc {

    private final StatsLibrary statLib = new StatsLibrary();
    private final Random random = new Random();

    private ArrayList<Person> GeneratePeople(int amountOfPeople){
        ArrayList<Person> people = new ArrayList<>();

        for(int i = 0; i < amountOfPeople; i++)
            people.add(new Person(random.nextInt(365) + 1));

        return people;
    }

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
