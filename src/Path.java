import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Path {
    private List<Point> points;
    private List<Point> prevPoints;

    private Path(List<Point> points, List<Point> prevPoints) {
        this.points = points;
        this.prevPoints = prevPoints;
    }

    public static Path of(List<Point> points) {
        int lastId = points.size() - 1;
        List<Point> midOfPoints = new ArrayList<>();
        for (int i = 1; i < lastId; i++) {
            midOfPoints.add(
                    points.get(i)
            );
        }

        Collections.shuffle(midOfPoints);
        List<Point> shuffledPoints = new ArrayList<>();
        shuffledPoints.add(points.get(0));
        shuffledPoints.addAll(midOfPoints);
        shuffledPoints.add(points.get(lastId));
        return new Path(
                shuffledPoints, Collections.emptyList()
        );
    }

    public static Path of(Path path) {
        return new Path(
                new ArrayList<>(path.points), Collections.emptyList()
        );
    }

    public List<Point> getPoints() {
        return points;
    }

    public void swapPoints() {
        int firstId = getRandomMiddlePointId();
        int secondId = getRandomMiddlePointId();

        prevPoints = new ArrayList<>(points);
        Point firstPoint = points.get(firstId);
        Point secondPoint = points.get(secondId);
        points.set(firstId, secondPoint);
        points.set(secondId, firstPoint);
    }

    public void revertSwappingPoints() {
        points = prevPoints;
    }

    public double calcDistance() {
        double distance = 0;
        int lastId = points.size() - 1;
        for (int i = 0; i < lastId; i++) {
            distance += points.get(i).calcDistance(points.get(i + 1));
        }
        return distance + points.get(lastId).calcDistance(points.get(0));
    }

    private int getRandomMiddlePointId() {
        return (int) (Math.random() * (points.size() - 2)) + 1;
    }
}
