import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */

public final class Fairy extends ActingEntity
{

    public Fairy(
            String id,
            Point position,
            List<PImage> images,
            int actionPeriod,
            int animationPeriod)
    {
        super(id, position, images, animationPeriod, actionPeriod);
    }

    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        Optional<Entity> fairyTarget =
                world.findNearest(getPosition(), new ArrayList<Class>(Arrays.asList(Stump.class)));

        if (fairyTarget.isPresent()) {
            Point tgtPos = fairyTarget.get().getPosition();

            if (this.moveToFairy(world, fairyTarget.get(), scheduler)) {
                ActingEntity sapling = Factory.createSapling("sapling_" + getId(), tgtPos,
                        imageStore.getImageList(Functions.SAPLING_KEY));

                world.addEntity(sapling);
                sapling.scheduleActions(scheduler, world, imageStore);
            }
        }

        scheduler.scheduleEvent(this,
                Factory.createActivityAction(this, world, imageStore), getActionPeriod());
    }

    public Point nextPositionFairy(WorldModel world, Point destPos)
    {
        PathingStrategy pathingStrat = new AStarPathingStrategy();
        Point start = getPosition();
        Point end = destPos;
        Predicate<Point> canPassThrough = p -> world.withinBounds(p) && !(world.isOccupied(p));
        BiPredicate<Point, Point> withinReach = Point::adjacent;
        Function<Point, Stream<Point>> potentialNeighbors = pathingStrat.CARDINAL_NEIGHBORS;
        List<Point> path = pathingStrat.computePath(start, end,canPassThrough, withinReach, potentialNeighbors);
        if (path == null || path.size() == 0)
            return getPosition();
        return path.get(0);
    }

    public boolean moveToFairy(
            WorldModel world,
            Entity target,
            EventScheduler scheduler)
    {
        if (getPosition().adjacent(target.getPosition())) {
            world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);
            return true;
        }
        else {
            Point nextPos = this.nextPositionFairy(world, target.getPosition());

            if (!this.getPosition().equals(nextPos)) {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent()) {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }


}