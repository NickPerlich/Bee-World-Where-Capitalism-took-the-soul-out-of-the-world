import java.util.List;
import java.util.Optional;

/**
 * A simple class representing a location in 2D space.
 */
public final class Point
{
    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean equals(Object other) {
        return other instanceof Point && ((Point)other).x == this.x
                && ((Point)other).y == this.y;
    }

    public int hashCode() {
        int result = 17;
        result = result * 31 + x;
        result = result * 31 + y;
        return result;
    }

    public boolean adjacent(Point p2) {
        return (x == p2.x && Math.abs(y - p2.y) == 1) || (y == p2.y
                && Math.abs(x - p2.x) == 1);
    }

    public int distanceSquared(Point p2) {
        int deltaX = x - p2.x;
        int deltaY = y - p2.y;

        return deltaX * deltaX + deltaY * deltaY;
    }

    public Optional<Entity> nearestEntity(List<Entity> entities)
    {
        if (entities.isEmpty()) {
            return Optional.empty();
        }
        else {
            Entity nearest = entities.get(0);
            int nearestDistance = nearest.getPosition().distanceSquared(this);

            for (Entity other : entities) {
                int otherDistance = other.getPosition().distanceSquared(this);

                if (otherDistance < nearestDistance) {
                    nearest = other;
                    nearestDistance = otherDistance;
                }
            }

            return Optional.of(nearest);
        }
    }

    public double distance(Point end) {
        return Math.sqrt(Math.pow(end.x - x, 2) + Math.pow(end.y - y, 2));
    }

}
