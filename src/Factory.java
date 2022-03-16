import processing.core.PImage;

import java.util.List;

public class Factory {
    public static Animation createAnimationAction(AnimatingEntity entity, int repeatCount) {
        return new Animation(entity, repeatCount);
    }

    public static Action createActivityAction(
            ActingEntity entity, WorldModel world, ImageStore imageStore)
    {
        return new Activity(entity, world, imageStore, 0);
    }

    public static House createHouse(
            String id, Point position, List<PImage> images)
    {
        return new House(id, position, images);
    }

    public static Obstacle createObstacle(
            String id, Point position, int animationPeriod, List<PImage> images)
    {
        return new Obstacle(id, position, images, animationPeriod);
    }

    public static Tree createTree(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            int health,
            List<PImage> images)
    {
        return new Tree(id, position, images, actionPeriod, animationPeriod, health);
    }

    public static Flower createFlower(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            int health,
            List<PImage> images)
    {
        return new Flower(id, position, images, actionPeriod, animationPeriod, health);
    }

    public static Stump createStump(
            String id,
            Point position,
            List<PImage> images)
    {
        return new Stump(id, position, images);
    }

    public static Flower_Stump createFlower_Stump(
            String id,
            Point position,
            List<PImage> images)
    {
        return new Flower_Stump(id, position, images);
    }

    // health starts at 0 and builds up until ready to convert to Tree
    public static Sapling createSapling(
            String id,
            Point position,
            List<PImage> images)
    {
        return new Sapling(id, position, images, Functions.SAPLING_ACTION_ANIMATION_PERIOD,
                Functions.SAPLING_ACTION_ANIMATION_PERIOD, 0, Functions.SAPLING_HEALTH_LIMIT);
    }

    public static Flower_Bud createFlower_Bud(
            String id,
            Point position,
            List<PImage> images)
    {
        return new Flower_Bud(id, position, images, Functions.SAPLING_ACTION_ANIMATION_PERIOD,
                Functions.SAPLING_ACTION_ANIMATION_PERIOD, 0, Functions.SAPLING_HEALTH_LIMIT);
    }

    public static Fairy createFairy(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            List<PImage> images)
    {
        return new Fairy(id, position, images, actionPeriod, animationPeriod);
    }

    public static Pollinator createPollinator(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            List<PImage> images)
    {
        return new Pollinator(id, position, images, actionPeriod, animationPeriod);
    }

    // need resource count, though it always starts at 0
    public static Dude_Not_Full createDudeNotFull(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            int resourceLimit,
            List<PImage> images)
    {
        return new Dude_Not_Full(id, position, images, resourceLimit, actionPeriod, animationPeriod);
    }

    // need resource count, though it always starts at 0
    public static Bee_Not_Full createBeeNotFull(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            int resourceLimit,
            List<PImage> images)
    {
        return new Bee_Not_Full(id, position, images, resourceLimit, actionPeriod, animationPeriod);
    }

    // don't technically need resource count ... full
    public static Dude_Full createDudeFull(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            int resourceLimit,
            List<PImage> images) {
        return new Dude_Full(id, position, images, resourceLimit, actionPeriod, animationPeriod);
    }

    // don't technically need resource count ... full
    public static Bee_Full createBeeFull(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            int resourceLimit,
            List<PImage> images) {
        return new Bee_Full(id, position, images, resourceLimit, actionPeriod, animationPeriod);
    }

}
