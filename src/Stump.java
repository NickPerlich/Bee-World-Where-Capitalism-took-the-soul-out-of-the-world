import java.util.*;

import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */

public final class Stump extends Entity implements Changeable
{
    public Stump(
            String id,
            Point position,
            List<PImage> images)
    {
        super(id, position, images);
    }

    public void change(
            WorldModel world,
            EventScheduler scheduler,
            ImageStore imageStore)
    {
        Entity flrstmp = Factory.createFlower_Stump(getId(), getPosition(),
                    imageStore.getImageList("flrstmp"));
        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        world.addEntity(flrstmp);
    }

}