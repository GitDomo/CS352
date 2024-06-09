/*Domonic Lee
20240609
Purpose: practice with writing a larger program that manipulates an ArrayList in various ways.*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

class Contestant extends Person {
    private String contestantId;
    private ArrayList<Integer> positions;

    public Contestant(String name, int age, String contestantId) {
        super(name, age);
        this.contestantId = contestantId;
        this.positions = new ArrayList<>();
    }

    public String getContestantId() {
        return contestantId;
    }

    public void addPosition(int position) {
        positions.add(position);
    }

    public double getAveragePosition() {
        int total = 0;
        for (int position : positions) {
            total += position;
        }
        return positions.size() > 0 ? (double) total / positions.size() : 0;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Contestant ID: " + contestantId + ", Average Position: " + getAveragePosition());
    }
}

public class ContestSimulation {

    public static void main(String[] args) {
        // Create contestants and run initial test
        int numContestants = 10; // Start with 10 contestants
        ArrayList<Contestant> contestants = new ArrayList<>();

        // Create contestants
        for (int i = 0; i < numContestants; i++) {
            contestants.add(new Contestant("Contestant" + (i + 1), 20 + i, "C" + (i + 1)));
        }

        // Run simulation
        runContest(contestants, 12);

        // Display results
        for (Contestant contestant : contestants) {
            contestant.display();
        }

        // Timing tests
        int[] numContestantsArray = {43, 45000};
        int[] numRoundsArray = {10, 100, 1000, 10000};

        for (int numContestantsTest : numContestantsArray) {
            for (int numRounds : numRoundsArray) {
                ArrayList<Contestant> testContestants = new ArrayList<>();

                // Create contestants
                for (int i = 0; i < numContestantsTest; i++) {
                    testContestants.add(new Contestant("Contestant" + (i + 1), 20 + i, "C" + (i + 1)));
                }

                // Run simulation and measure time
                long startTime = System.currentTimeMillis();
                runContest(testContestants, numRounds);
                long endTime = System.currentTimeMillis();

                System.out.println("Num Contestants: " + numContestantsTest + ", Num Rounds: " + numRounds + ", Time: " + (endTime - startTime) + " ms");
            }
        }
    }

    public static void runContest(ArrayList<Contestant> contestants, int numRounds) {
        Random rand = new Random();

        for (int round = 0; round < numRounds; round++) {
            ArrayList<Contestant> newLine = new ArrayList<>(contestants.size());

            for (Contestant contestant : contestants) {
                boolean answerCorrect = rand.nextBoolean();
                if (answerCorrect) {
                    newLine.add(0, contestant); // Move to front
                } else {
                    newLine.add(contestant); // Move to back (end of the line)
                }
            }

            // Update contestants with new positions
            contestants.clear();
            contestants.addAll(newLine);

            // Record positions
            for (int i = 0; i < contestants.size(); i++) {
                contestants.get(i).addPosition(i);
            }
        }
    }
}
