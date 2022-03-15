/**
 * An action that can be taken by an entity
 */
public final class Animation implements Action
{
    private AnimatingEntity entity;
    private int repeatCount;

    public Animation(
            AnimatingEntity entity,
            int repeatCount)
    {
        this.entity = entity;
        this.repeatCount = repeatCount;
    }

    public void executeAction(EventScheduler scheduler) {
        entity.nextImage();

        if (repeatCount != 1) {
            scheduler.scheduleEvent(entity,
                    Factory.createAnimationAction(entity,
                            Math.max(repeatCount - 1,
                                    0)),
                    entity.getAnimationPeriod());
        }
    }

}
