import java.util.*;

import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */

public final class Dude_Full extends DudeEntity
{

    public Dude_Full(
            String id,
            Point position,
            List<PImage> images,
            int resourceLimit,
            int actionPeriod,
            int animationPeriod)
    {
        super(id, position, images, animationPeriod, actionPeriod, resourceLimit);
    }
    
    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        Optional<Entity> fullTarget =
                world.findNearest(getPosition(), new ArrayList<Class>(Arrays.asList(House.class)));

        if (fullTarget.isPresent() && this.moveToDude(world,
                fullTarget.get(), scheduler))
        {
            this.transformFull(world, scheduler, imageStore);
        }
        else {
            scheduler.scheduleEvent(this,
                    Factory.createActivityAction(this, world, imageStore), getActionPeriod());
        }
    }

    public void transformFull(
            WorldModel world,
            EventScheduler scheduler,
            ImageStore imageStore)
    {
        ActingEntity miner = Factory.createDudeNotFull(getId(),
                getPosition(), getActionPeriod(),
                getAnimationPeriod(),
                getResourceLimit(),
                getImages());

        this.transformDude(world, scheduler, imageStore, miner);
    }

    public void change(
            WorldModel world,
            EventScheduler scheduler,
            ImageStore imageStore)
    {
        ActingEntity miner = Factory.createBeeFull(getId(),
                getPosition(), getActionPeriod(),
                getAnimationPeriod(),
                getResourceLimit(),
                imageStore.getImageList("bee"));

        this.transformDude(world, scheduler, imageStore, miner);
    }

    public boolean _moveToDudeHelper(Entity target) {
        return true;
    }

}