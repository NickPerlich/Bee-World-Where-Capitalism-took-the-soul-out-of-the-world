import processing.core.PImage;

import java.util.List;

public abstract class FlowerEntity extends ActingEntity {

    private int health;

    public FlowerEntity(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod, int health) {
        super(id, position, images, animationPeriod, actionPeriod);
        this.health = health;
    }

    public boolean transformFlower(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        Entity flowerStump = Factory.createFlower_Stump(this.getId(),
                this.getPosition(),
                imageStore.getImageList(Functions.STUMP_KEY));

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        world.addEntity(flowerStump);

        return true;
    }

    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {

        if (!this.transformFlower(world, scheduler, imageStore)) {

            scheduler.scheduleEvent(this,
                    Factory.createActivityAction(this, world, imageStore), this.getActionPeriod());
        }
    }

    public void setHealth(int newHealth) { health = newHealth; }

    public int getHealth() {
        return health;
    }
}

