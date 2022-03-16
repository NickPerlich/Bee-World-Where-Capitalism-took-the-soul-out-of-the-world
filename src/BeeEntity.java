import processing.core.PImage;

import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class BeeEntity extends ActingEntity {

    private int resourceLimit;

    public BeeEntity(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod, int resourceLimit) {
        super(id, position, images, animationPeriod, actionPeriod);
        this.resourceLimit = resourceLimit;
    }

    public int getResourceLimit() { return resourceLimit; }

    public Point nextPositionBee(WorldModel world, Point destPos)
    {
        PathingStrategy pathingStrat = new AStarPathingStrategy();
        Point start = getPosition();
        Point end = destPos;
        Predicate<Point> canPassThrough = p -> world.withinBounds(p) && (!(world.isOccupied(p)) || world.getOccupancyCell(p).getClass() == Stump.class
                || world.getOccupancyCell(p).getClass() == Flower_Stump.class);
        BiPredicate<Point, Point> withinReach = Point::adjacent;
        Function<Point, Stream<Point>> potentialNeighbors = pathingStrat.CARDINAL_NEIGHBORS;
        List<Point> path = pathingStrat.computePath(start, end,canPassThrough, withinReach, potentialNeighbors);
        if (path == null || path.size() == 0)
            return getPosition();
        return path.get(0);
    }

    protected abstract boolean _moveToBeeHelper(Entity target);

    public boolean moveToBee(WorldModel world, Entity target, EventScheduler scheduler)
    {
        if (getPosition().adjacent(target.getPosition())) {
            return _moveToBeeHelper(target);
        }
        else {
            Point nextPos = this.nextPositionBee(world, target.getPosition());

            if (!getPosition().equals(nextPos)) {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent()) {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }

    public void transformBee(WorldModel world, EventScheduler scheduler, ImageStore imageStore, ActingEntity miner) {
        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        world.addEntity(miner);
        miner.scheduleActions(scheduler, world, imageStore);
    }


}

