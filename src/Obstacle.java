import java.util.*;

import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */

public final class Obstacle extends AnimatingEntity implements Changeable
{

    public Obstacle(
            String id,
            Point position,
            List<PImage> images,
            int animationPeriod)
    {
        super(id, position, images, animationPeriod);
    }

    @Override
    public void change(WorldModel world, EventScheduler eventScheduler, ImageStore images)
    {
        Obstacle voidWater = Factory.createObstacle(getId(),
                getPosition(),
                getAnimationPeriod(),
                images.getImageList("void"));

        world.removeEntity(this);
        eventScheduler.unscheduleAllEvents(this);
        world.addEntity(voidWater);
        voidWater.scheduleActions(eventScheduler, world, images);
    }
}