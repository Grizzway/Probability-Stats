package Assignment3;

import StatsLibrary.StatsLibrary;

import java.util.ArrayList;
import java.util.Random;

public class MontyHallSimulator {
    private StatsLibrary statLib = new StatsLibrary();
    private Random random = new Random();

    /**
     * Simulates a single run of the Monty Hall problem, where the player chooses a door,
     * a non-prize door is revealed, and the player has the option to switch their choice.
     *
     * @param changeDoor A boolean indicating if the player switches to the remaining door.
     *                   If true, the player switches; if false, they keep their original choice.
     * @return 1 if the player wins by choosing the prize door, otherwise 0.
     */
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

    /**
     * Simulates multiple runs of the Monty Hall problem and calculates the player's win rate.
     *
     * @param timesToRun The number of simulations to run.
     * @param changeDoor A boolean indicating if the player switches to the remaining door in each simulation.
     *                   If true, the player switches; if false, they keep their original choice.
     * @return The average win rate as a double, representing the probability of winning under the chosen strategy.
     */
    public double Simulate(int timesToRun, boolean changeDoor){
        ArrayList<Integer> results = new ArrayList<>();

        for(int i = 0; i < timesToRun; i++)
            results.add(MontyHall(changeDoor));

        return  statLib.Mean(results);
    }
}
