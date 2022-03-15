import processing.core.PImage;

import java.util.List;

public abstract class AnimatingEntity extends Entity {

    private int animationPeriod;

    public AnimatingEntity(String id, Point position, List<PImage> images, int animationPeriod) {
        super(id, position, images);
        this.animationPeriod = animationPeriod;
    }

    public int getAnimationPeriod() {
        return animationPeriod;
    }

    public void nextImage() {
        this.setImageIndex((this.getImageIndex() + 1) % this.getImages().size());
    }

    public void scheduleActions(
            EventScheduler scheduler,
            WorldModel world,
            ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
                Factory.createAnimationAction(this, 0),
                this.getAnimationPeriod());
    }
}

