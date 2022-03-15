import java.util.*;

import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */

public final class Sapling extends PlantEntity
{
    private int healthLimit;

    public Sapling(
            String id,
            Point position,
            List<PImage> images,
            int actionPeriod,
            int animationPeriod,
            int health,
            int healthLimit)
    {
        super(id, position, images, animationPeriod, actionPeriod, health);
        this.healthLimit = healthLimit;
    }



    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        this.setHealth(this.getHealth() + 1);
        super.executeActivity(world, imageStore, scheduler);
    }



    public boolean transformPlant(WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        if (this.getHealth() <= 0) {
            super.transformPlant(world, scheduler, imageStore);
        }
        else if (this.getHealth() >= healthLimit)
        {
            PlantEntity tree = Factory.createTree("tree_" + this.getId(),
                    this.getPosition(),
                    Functions.getNumFromRange(Functions.TREE_ACTION_MAX, Functions.TREE_ACTION_MIN),
                    Functions.getNumFromRange(Functions.TREE_ANIMATION_MAX, Functions.TREE_ANIMATION_MIN),
                    Functions.getNumFromRange(Functions.TREE_HEALTH_MAX, Functions.TREE_HEALTH_MIN),
                    imageStore.getImageList(Functions.TREE_KEY));

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(tree);
            tree.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }


}