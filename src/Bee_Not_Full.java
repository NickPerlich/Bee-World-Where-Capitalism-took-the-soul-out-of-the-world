import java.util.*;

import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */

public final class Bee_Not_Full extends BeeEntity
{
    private int resourceCount;

    public Bee_Not_Full(
            String id,
            Point position,
            List<PImage> images,
            int resourceLimit,
            int actionPeriod,
            int animationPeriod)
    {
        super(id, position, images, animationPeriod, actionPeriod, resourceLimit);
        this.resourceCount = 0;
    }

    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        Optional<Entity> target =
                world.findNearest(getPosition(), new ArrayList<Class>(Arrays.asList(Flower.class, Flower_Bud.class)));

        if (!target.isPresent() || !this.moveToBee(world,
                target.get(),
                scheduler)
                || !this.transformNotFull(world, scheduler, imageStore))
        {
            scheduler.scheduleEvent(this,
                    Factory.createActivityAction(this, world, imageStore), getActionPeriod());
        }
    }

    public boolean transformNotFull(
            WorldModel world,
            EventScheduler scheduler,
            ImageStore imageStore)
    {
        if (resourceCount >= getResourceLimit()) {
            ActingEntity miner = Factory.createBeeFull(getId(),
                    getPosition(), getActionPeriod(),
                    getAnimationPeriod(),
                    getResourceLimit(),
                    getImages());

            this.transformBee(world, scheduler, imageStore, miner);

            return true;
        }

        return false;
    }


    public boolean _moveToBeeHelper(Entity target) {
        resourceCount += 1;
        ((FlowerEntity) target).setHealth(((FlowerEntity)target).getHealth()-1);
        return true;
    }


}