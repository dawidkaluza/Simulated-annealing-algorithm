import java.util.List;

public class SimulatedAnnealing {
    private final List<Point> points;

    public SimulatedAnnealing(List<Point> points) {
        this.points = points;
        points.add(points.get(0));
        System.out.printf("Trasa do sprawdzenia: %s \n", points);
    }

    public void findBestPath() {
        Path curPath = Path.of(points);
        Path bestPath = curPath;
        double bestDistance = bestPath.calcDistance();
        printNewBestPathInfo(bestPath, bestDistance);

        double temperature = getStartingTemperature();
        int numberOfIterations = getNumberOfIterations();
        while (numberOfIterations-- > 0) {
            if (temperature <= 0.1) {
                break;
            }

            curPath.swapPoints();
            double curDistance = curPath.calcDistance();
            printNewRandomPathInfo(curPath, curDistance);
            if (curDistance < bestDistance) {
                bestPath = Path.of(curPath);
                bestDistance = curDistance;
                printNewBestPathInfo(bestPath, bestDistance);
            } else if (Math.exp((bestDistance - curDistance) / temperature) < Math.random()) {
                curPath.revertSwappingPoints();
            }

            temperature *= getCoolingRate();
            System.out.printf("Pozostała ilość iteracji: %d, temperatura: %.2f \n", numberOfIterations, temperature);
            System.out.println("==========================================================================");
        }

        printBestPathInfo(bestPath, bestDistance);
    }

    private int getNumberOfIterations() {
        return points.size() * 200;
    }

    private double getStartingTemperature() {
        return points.size() * 50;
    }

    private double getCoolingRate() {
        return 0.985;
    }

    private void printNewRandomPathInfo(Path path, double distance) {
        System.out.printf("Nowa wylosowana ścieżka to: %s \n", path.getPoints());
        System.out.printf("Długość tej ścieżki wynosi %.2f \n", distance);
    }

    private void printNewBestPathInfo(Path bestPath, double bestDistance) {
        System.out.printf("Aktualna najlepsza ścieżka to: %s \n", bestPath.getPoints());
        System.out.printf("Długość tej ścieżki wynosi %.2f \n", bestDistance);
    }

    private void printBestPathInfo(Path bestPath, double bestDistance) {
        System.out.println("==========================================================================");
        System.out.printf("Najlepsza ścieżka to: %s \n", bestPath.getPoints());
        System.out.printf("Długość tej ścieżki wynosi %.2f \n", bestDistance);
        System.out.println("==========================================================================");
    }
}
