package Assignment3;

import Assignment1.StatsLibrary;

import java.util.ArrayList;
import java.util.Random;

public class MontyHallSimulator {
    private StatsLibrary statLib = new StatsLibrary();
    private Random random = new Random();

    private int MontyHall(boolean changeDoor){
        int prizeDoor = random.nextInt(3);
        int playerChoice = random.nextInt(3);

        int doorToOpen;
        do {
           doorToOpen = random.nextInt(3);
        } while (doorToOpen == prizeDoor || doorToOpen == playerChoice);

        if(changeDoor)
            playerChoice = 3 - playerChoice - doorToOpen;

        return playerChoice == prizeDoor ? 1 : 0;
    }

    public double Simulate(int timesToRun, boolean changeDoor){
        ArrayList<Integer> results = new ArrayList<>();

        for(int i = 0; i < timesToRun; i++)
            results.add(MontyHall(changeDoor));

        return  statLib.Mean(results);
    }
}
