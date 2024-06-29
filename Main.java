public class Main {
    public static void main(String[] args) {
        // Test Case 1: Custom Cave Layout 1 (Requires Backtracking)
        char[][] cave1 = {
            {'R', 'R', 'R', 'R', 'R', 'R'},
            {'R', '.', '.', '.', '.', 'R'},
            {'R', 'R', 'R', 'R', '.', 'R'},
            {'R', '.', '.', '.', '.', 'R'},
            {'R', 'M', 'R', 'R', 'R', 'R'}
        };
        int startRow1 = 1, startCol1 = 1;
        int endRow1 = 4, endCol1 = 1;

        CaveExplorer explorer1 = new CaveExplorer(cave1, startRow1, startCol1, endRow1, endCol1);
        System.out.println("Explorer 1 Initial Layout:");
        System.out.println(explorer1);
        boolean solved1 = explorer1.solve();
        System.out.println("Explorer 1 Path: " + (solved1 ? explorer1.getPath() : "No path found"));
        System.out.println("Explorer 1 Final Layout:");
        System.out.println(explorer1);

        System.out.println("---------------------------------------");

        // Test Case 2: Custom Cave Layout 2 (No Path)
        char[][] cave2 = {
            {'R', 'R', 'R', 'R', 'R', 'R'},
            {'R', '.', '.', '.', '.', 'R'},
            {'R', 'R', 'R', 'R', '.', 'R'},
            {'R', '.', 'S', '.', '.', 'R'},
            {'R', 'M', 'R', 'R', 'R', 'R'}
        };
        int startRow2 = 3, startCol2 = 2;
        int endRow2 = 4, endCol2 = 1;

        CaveExplorer explorer2 = new CaveExplorer(cave2, startRow2, startCol2, endRow2, endCol2);
        System.out.println("Explorer 2 Initial Layout:");
        System.out.println(explorer2);
        boolean solved2 = explorer2.solve();
        System.out.println("Explorer 2 Path: " + (solved2 ? explorer2.getPath() : "No path found"));
        System.out.println("Explorer 2 Final Layout:");
        System.out.println(explorer2);
    }
}
