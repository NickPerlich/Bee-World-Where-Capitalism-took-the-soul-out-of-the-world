import processing.core.PImage;

import java.util.List;

public abstract class PlantEntity extends ActingEntity {

    private int health;

    public PlantEntity(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod, int health) {
        super(id, position, images, animationPeriod, actionPeriod);
        this.health = health;
    }

    public boolean transformPlant(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        Entity stump = Factory.createStump(this.getId(),
                this.getPosition(),
                imageStore.getImageList(Functions.STUMP_KEY));

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        world.addEntity(stump);

        return true;
    }

    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {

        if (!this.transformPlant(world, scheduler, imageStore)) {

            scheduler.scheduleEvent(this,
                    Factory.createActivityAction(this, world, imageStore), this.getActionPeriod());
        }
    }

    public void setHealth(int newHealth) { health = newHealth; }

    public int getHealth() {
        return health;
    }
}

