import java.util.ArrayList;

public class Contestant extends Person {
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
