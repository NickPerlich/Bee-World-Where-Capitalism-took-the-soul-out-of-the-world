import java.util.*;

import processing.core.PApplet;
import processing.core.PImage;

public final class ImageStore
{
    private Map<String, List<PImage>> images;
    private List<PImage> defaultImages;

    public ImageStore(PImage defaultImage) {
        this.images = new HashMap<>();
        defaultImages = new LinkedList<>();
        defaultImages.add(defaultImage);
    }

    public List<PImage> getImageList(String key) {
        return images.getOrDefault(key, defaultImages);
    }

    public void loadImages(Scanner in, PApplet screen)
    {
        int lineNumber = 0;
        while (in.hasNextLine()) {
            try {
                Functions.processImageLine(images, in.nextLine(), screen);
            }
            catch (NumberFormatException e) {
                System.out.println(
                        String.format("Image format error on line %d",
                                lineNumber));
            }
            lineNumber++;
        }
    }

    

}
