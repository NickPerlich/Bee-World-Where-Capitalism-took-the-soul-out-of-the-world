import processing.core.PImage;

import java.util.List;

public abstract class ActingEntity extends AnimatingEntity {

    private int actionPeriod;

    public ActingEntity(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod) {
        super(id, position, images, animationPeriod);
        this.actionPeriod = actionPeriod;
    }

    public int getActionPeriod() {
        return actionPeriod;
    }

    protected abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);

    public void scheduleActions(
            EventScheduler scheduler,
            WorldModel world,
            ImageStore imageStore)
    {
        super.scheduleActions(scheduler, world, imageStore);
        scheduler.scheduleEvent(this,
                Factory.createActivityAction(this, world, imageStore),
                getActionPeriod());
    }

}

