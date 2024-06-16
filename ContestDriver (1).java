import java.util.LinkedList;
import java.util.Random;

public class ContestDriver {

    public static void main(String[] args) {
        // Create contestants and run initial test
        int numContestants = 10; // Start with 10 contestants
        LinkedList<Contestant> contestants = new LinkedList<>();

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
                LinkedList<Contestant> testContestants = new LinkedList<>();

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

    public static void runContest(LinkedList<Contestant> contestants, int numRounds) {
        Random rand = new Random();

        for (int round = 0; round < numRounds; round++) {
            LinkedList<Contestant> newLine = new LinkedList<>();

            for (Contestant contestant : contestants) {
                boolean answerCorrect = rand.nextBoolean();
                if (answerCorrect) {
                    newLine.addFirst(contestant); // Move to front
                } else {
                    newLine.addLast(contestant); // Move to back (end of the line)
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
