public class WeatherDriver {
    public static void main(String[] args) {
        // Create WeatherReport objects
        WeatherReport report1 = new WeatherReport("weather.txt");
        WeatherReport report2 = new WeatherReport("weather.txt");
        WeatherReport report3 = new WeatherReport("weather.txt");
        WeatherReport report4 = new WeatherReport("weather.txt");

        // Check initial sort status
        System.out.println("Initial sorted by City: " + report1.isSortedByCity());
        System.out.println("Initial sorted by High: " + report1.isSortedByHigh());

        // Sort using Collections and merge sort, timing each
        long startTime, endTime;

        startTime = System.nanoTime();
        report1.sortWithCollections("City");
        endTime = System.nanoTime();
        System.out.println("Collections sort by City: " + (endTime - startTime) + " ns, sorted: " + report1.isSortedByCity());

        startTime = System.nanoTime();
        report2.sortWithMerge("City");
        endTime = System.nanoTime();
        System.out.println("Merge sort by City: " + (endTime - startTime) + " ns, sorted: " + report2.isSortedByCity());

        startTime = System.nanoTime();
        report3.sortWithCollections("High");
        endTime = System.nanoTime();
        System.out.println("Collections sort by High: " + (endTime - startTime) + " ns, sorted: " + report3.isSortedByHigh());

        startTime = System.nanoTime();
        report4.sortWithMerge("High");
        endTime = System.nanoTime();
        System.out.println("Merge sort by High: " + (endTime - startTime) + " ns, sorted: " + report4.isSortedByHigh());
    }
}
