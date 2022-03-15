import processing.core.PApplet;
import processing.core.PImage;

import java.util.Optional;

public final class WorldView
{
    private PApplet screen;
    private WorldModel world;
    private int tileWidth;
    private int tileHeight;
    private Viewport viewport;

    public WorldView(
            int numRows,
            int numCols,
            PApplet screen,
            WorldModel world,
            int tileWidth,
            int tileHeight)
    {
        this.screen = screen;
        this.world = world;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.viewport = new Viewport(numRows, numCols);
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void drawEntities() {
        for (Entity entity : world.getEntities()) {
            Point pos = entity.getPosition();

            if (viewport.contains(pos)) {
                Point viewPoint = viewport.worldToViewport(pos.getX(), pos.getY());
                screen.image(entity.getCurrentImage(),
                        viewPoint.getX() * tileWidth,
                        viewPoint.getY() * tileHeight);
            }
        }
    }

    public void shiftView(int colDelta, int rowDelta) {
        int newCol = Functions.clamp(this.viewport.getCol() + colDelta, 0,
                this.world.getNumCols() - this.viewport.getNumCols());
        int newRow = Functions.clamp(this.viewport.getRow() + rowDelta, 0,
                this.world.getNumRows() - this.viewport.getNumRows());

        this.viewport.shift(newCol, newRow);
    }

    public void drawBackground() {
        for (int row = 0; row < viewport.getNumRows(); row++) {
            for (int col = 0; col < viewport.getNumCols(); col++) {
                Point worldPoint = viewport.viewportToWorld(col, row);
                Optional<PImage> image =
                        world.getBackgroundImage(worldPoint);
                if (image.isPresent()) {
                    screen.image(image.get(), col * tileWidth,
                            row * tileHeight);
                }
            }
        }
    }

    public void drawViewport() {
        this.drawBackground();
        this.drawEntities();
    }

}
