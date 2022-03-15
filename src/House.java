import java.util.*;

import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */

public final class House extends Entity
{

    public House(
            String id,
            Point position,
            List<PImage> images)
    {
        super(id, position, images);
    }

}