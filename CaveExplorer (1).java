import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class CaveExplorer {
    private char[][] cave;
    private int startRow, startCol;
    private int endRow, endCol;
    private String path;

    public CaveExplorer(char[][] cave, int startRow, int startCol, int endRow, int endCol) {
        this.cave = cave;
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
        this.path = "";
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
        Stack<StringBuilder> pathStack = new Stack<>();
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

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            StringBuilder pathBuilder = new StringBuilder(pathStack.isEmpty() ? "" : pathStack.pop().toString());

            if (row == endRow && col == endCol) {
                // Mark end of successful path
                pathBuilder.append('\0');
                path = pathBuilder.toString();
                return true;
            }

            int validMoves = 0;

            for (int i = 0; i < directions.length; i++) {
                int newRow = row + directions[i][0];
                int newCol = col + directions[i][1];
                String newPos = newRow + "," + newCol;

                if (isValidMove(newRow, newCol, visited)) {
                    queue.add(new int[]{newRow, newCol});
                    visited.add(newPos);

                    // Append direction to current path
                    pathBuilder.append(dirChars[i]);
                    validMoves++;
                }
            }

            // If there are multiple valid moves, push the current path onto the stack
            if (validMoves > 1) {
                pathStack.push(new StringBuilder(pathBuilder));
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
        // If path is empty, return immediately
        if (path.isEmpty()) {
            return "No path found";
        }

        // Trim the path to remove any characters beyond the successful path
        int endIndex = path.indexOf('\0'); // Find the first occurrence of '\0'
        if (endIndex != -1) {
            return path.substring(0, endIndex);
        } else {
            return path; // If '\0' is not found, return the entire path
        }
    }
}

