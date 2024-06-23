import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class CaveExplorer {
    private char[][] cave;
    private int startRow, startCol;
    private int endRow, endCol;
    private String path;

    public CaveExplorer() {
        cave = new char[][] {
            {'R', 'R', 'R', 'R', 'R', 'R'},
            {'R', '.', '.', 'S', 'R', 'R'},
            {'R', '.', 'R', 'R', 'R', 'R'},
            {'R', '.', 'M', 'R', 'R', 'R'},
            {'R', 'R', 'R', 'R', 'R', 'R'}
        };
        startRow = 1;
        startCol = 3;
        endRow = 3;
        endCol = 2;
        path = "";
    }

    public CaveExplorer(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            int rows = scanner.nextInt();
            int cols = scanner.nextInt();
            scanner.nextLine(); // Consume the rest of the line
            cave = new char[rows][cols];
            
            for (int i = 0; i < rows; i++) {
                String line = scanner.nextLine();
                cave[i] = line.toCharArray();
                if (line.contains("S")) {
                    startRow = i;
                    startCol = line.indexOf('S');
                }
                if (line.contains("M")) {
                    endRow = i;
                    endCol = line.indexOf('M');
                }
            }
            path = "";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : cave) {
            for (char cell : row) {
                sb.append(cell);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public boolean solve() {
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(new int[]{startRow, startCol});
        visited.add(startRow + "," + startCol);

        // Direction vectors for North, South, West, East
        int[][] directions = {
            {-1, 0}, // North
            {1, 0},  // South
            {0, -1}, // West
            {0, 1}   // East
        };
        char[] dirChars = {'n', 's', 'w', 'e'};
        StringBuilder pathBuilder = new StringBuilder();

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            if (row == endRow && col == endCol) {
                path = pathBuilder.toString();
                return true;
            }

            for (int i = 0; i < directions.length; i++) {
                int newRow = row + directions[i][0];
                int newCol = col + directions[i][1];
                String newPos = newRow + "," + newCol;

                if (isValidMove(newRow, newCol, visited)) {
                    queue.add(new int[]{newRow, newCol});
                    visited.add(newPos);
                    pathBuilder.append(dirChars[i]);
                }
            }
        }

        return false;
    }

    private boolean isValidMove(int row, int col, Set<String> visited) {
        if (visited.contains(row + "," + col)) {
            return false;
        }
        if (row < 0 || row >= cave.length || col < 0 || col >= cave[0].length) {
            return false;
        }
        if (cave[row][col] == 'R' || cave[row][col] == 'S') {
            return false;
        }
        return true;
    }

    public String getPath() {
        return path;
    }

    public static void main(String[] args) {
        // Test Case 1: Default Constructor
        CaveExplorer explorer1 = new CaveExplorer();
        System.out.println("Explorer 1 Initial Layout:");
        System.out.println(explorer1);
        boolean solved1 = explorer1.solve();
        System.out.println("Explorer 1 Path: " + (solved1 ? explorer1.getPath() : "No path found"));
        System.out.println("Explorer 1 Final Layout:");
        System.out.println(explorer1);

        // Test Case 2: File Constructor
        CaveExplorer explorer2 = new CaveExplorer("cave.txt");
        System.out.println("Explorer 2 Initial Layout:");
        System.out.println(explorer2);
        boolean solved2 = explorer2.solve();
        System.out.println("Explorer 2 Path: " + (solved2 ? explorer2.getPath() : "No path found"));
        System.out.println("Explorer 2 Final Layout:");
        System.out.println(explorer2);
    }
}
