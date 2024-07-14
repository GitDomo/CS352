import java.io.*;
import java.util.*;

public class WeatherReport {
    private LinkedList<Temperature> temperatureList;

    // Constructor with no parameters
    public WeatherReport() {
        temperatureList = new LinkedList<>(Arrays.asList(
            new Temperature("City1", "State1", 30, 40),
            new Temperature("City2", "State2", 25, 35),
            new Temperature("City3", "State3", 28, 38),
            new Temperature("City4", "State4", 22, 32),
            new Temperature("City5", "State5", 24, 34),
            new Temperature("City6", "State6", 26, 36),
            new Temperature("City7", "State7", 27, 37),
            new Temperature("City8", "State8", 29, 39)
        ));
    }

    // Constructor with file parameter
    public WeatherReport(String fileName) {
        temperatureList = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // Skip the header
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String city = data[1];
                String state = data[10];
                int lowTemp = Integer.parseInt(data[6]);
                int highTemp = Integer.parseInt(data[5]);
                temperatureList.add(new Temperature(city, state, lowTemp, highTemp));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isSortedByCity() {
        for (int i = 0; i < temperatureList.size() - 1; i++) {
            if (temperatureList.get(i).city.compareTo(temperatureList.get(i + 1).city) > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isSortedByHigh() {
        for (int i = 0; i < temperatureList.size() - 1; i++) {
            if (temperatureList.get(i).highTemp > temperatureList.get(i + 1).highTemp) {
                return false;
            }
        }
        return true;
    }

    public void sortWithCollections(String by) {
        if (by.equalsIgnoreCase("City")) {
            Collections.sort(temperatureList, Comparator.comparing(t -> t.city));
        } else if (by.equalsIgnoreCase("High")) {
            Collections.sort(temperatureList, Comparator.comparingInt(t -> t.highTemp));
        }
    }

    public void sortWithMerge(String by) {
        temperatureList = mergeSort(temperatureList, by.equalsIgnoreCase("City") ?
            Comparator.comparing(t -> t.city) :
            Comparator.comparingInt(t -> t.highTemp)
        );
    }

    private LinkedList<Temperature> mergeSort(LinkedList<Temperature> list, Comparator<Temperature> comparator) {
        if (list.size() <= 1) {
            return list;
        }

        LinkedList<Temperature> left = new LinkedList<>();
        LinkedList<Temperature> right = new LinkedList<>();
        boolean switchList = true;

        for (Temperature t : list) {
            if (switchList) {
                left.add(t);
            } else {
                right.add(t);
            }
            switchList = !switchList;
        }

        left = mergeSort(left, comparator);
        right = mergeSort(right, comparator);

        return merge(left, right, comparator);
    }

    private LinkedList<Temperature> merge(LinkedList<Temperature> left, LinkedList<Temperature> right, Comparator<Temperature> comparator) {
        LinkedList<Temperature> result = new LinkedList<>();

        while (!left.isEmpty() && !right.isEmpty()) {
            if (comparator.compare(left.peek(), right.peek()) <= 0) {
                result.add(left.poll());
            } else {
                result.add(right.poll());
            }
        }

        result.addAll(left);
        result.addAll(right);

        return result;
    }

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
