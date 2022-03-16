import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TeleporterEntrance extends ActingEntity
{
    private TeleporterExit exit;
    public TeleporterEntrance(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod, TeleporterExit exit)
    {
        super(id, position, images, animationPeriod, actionPeriod);
        this.exit = exit;
    }

    @Override
    protected void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        //find all nearby entities (within 1 range)
        List<ActingEntity> nearby = PathingStrategy.CARDINAL_NEIGHBORS.apply(getPosition()).filter(
                world::withinBounds).filter(world::isOccupied).map(world::getOccupancyCell).filter(
                        entity -> entity instanceof ActingEntity).map(entity -> (ActingEntity)entity).collect(Collectors.toList());
        //deal with entities:
        for (ActingEntity entity : nearby)
        {
            //dudes are deleted
            if (entity instanceof DudeEntity)
            {
                world.removeEntity(entity);
                scheduler.unscheduleAllEvents(entity);
            }
            //bees and pollinators are teleported
            else if(entity instanceof BeeEntity || entity instanceof Pollinator)
            {
                List<Point> output_location = PathingStrategy.CARDINAL_NEIGHBORS.apply(exit.getPosition()).filter(world::withinBounds).filter(p->!world.isOccupied(p)).limit(1).collect(Collectors.toList());
                if (output_location.size() > 0)
                {
                    world.moveEntity(entity, output_location.get(0));
                }
            }
        }

        scheduler.scheduleEvent(this,
                Factory.createActivityAction(this, world, imageStore), getActionPeriod());
    }
}
