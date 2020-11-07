import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            List<Point> points = loadPoints();
            SimulatedAnnealing annealing = new SimulatedAnnealing(points);
            annealing.findBestPath();
        } catch (InputMismatchException | IllegalArgumentException e) {
            System.out.println("Podano nieprawidłowe dane.");
        }
    }

    private static List<Point> loadPoints() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj, ile punktów chcesz wprowadzić: ");
        int pointsNum = scanner.nextInt();
        if (pointsNum <= 0) {
            throw new IllegalArgumentException();
        }

        System.out.printf("Ilość punktów do wprowadzenia: %d \n", pointsNum);
        System.out.println("Podaj teraz współrzędne punktów oddzielone spacją.");
        System.out.println("Po wprowadzeniu każdego punktu kliknij ENTER.");
        System.out.println("Np.: Wpisz '3 4' i kliknij ENTER.");
        System.out.println("Pierwszy wprowadzony punkt będzie punktem startowym.");

        List<Point> points = new ArrayList<>();
        while (pointsNum-- > 0) {
            Point point = new Point(scanner.nextInt(), scanner.nextInt());
            points.add(
                    point
            );

            System.out.printf("Wczytano punkt: %s \n", point);
            System.out.printf("Pozostało do wprowadzenia: %d \n", pointsNum);
        }

        return points;
    }
}
