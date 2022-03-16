import processing.core.PImage;

import java.util.List;

public class TeleporterEntrance extends ActingEntity
{
    public TeleporterEntrance(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod)
    {
        super(id, position, images, animationPeriod, actionPeriod);
    }

    @Override
    protected void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {

    }
}
