
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Resizes an image down by a factor of two.
 */
public class JavaResize {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: JavaResize in.png out.png");
            System.exit(1);
        }

        String inputPathname = args[0];
        String outputPathname = args[1];

        System.out.printf("Resizing %s to %s.\n", inputPathname, outputPathname);

        BufferedImage inputImage = ImageIO.read(new File(inputPathname));

        // Target size.
        int width = inputImage.getWidth()/2;
        int height = inputImage.getHeight()/2;

        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = outputImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(inputImage, 0, 0, width, height, null);
        graphics2D.dispose();

        ImageIO.write(outputImage, "png", new File(outputPathname));
    }
}

