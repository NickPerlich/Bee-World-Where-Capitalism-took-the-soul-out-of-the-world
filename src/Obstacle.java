import java.util.*;

import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */

public final class Obstacle extends AnimatingEntity
{

    public Obstacle(
            String id,
            Point position,
            List<PImage> images,
            int animationPeriod)
    {
        super(id, position, images, animationPeriod);
    }


}