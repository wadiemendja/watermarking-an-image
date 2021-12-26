
// Java code for watermarking an image

// For setting color of the watermark text
import java.awt.Color;

// For setting font of the watermark text
import java.awt.Font;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class Pixel {
    BufferedImage image;
    int width;
    int height;

    public Pixel() {
        try {
            File input = new File("input.png");
            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();

            int count = 0;

            for (int i = 0; i < height; i++) {

                for (int j = 0; j < width; j++) {

                    count++;
                    Color c = new Color(image.getRGB(j, i));
                    System.out.println("S.No: " + count + " Red: " + c.getRed() + "  Green: " + c.getGreen() + " Blue: "
                            + c.getBlue());
                }
            }

        } catch (Exception e) {
        }
    }
}

public class WaterMark {
    public static void main(String[] args) {
        // read image pixels
        new Pixel();
        // watermarking
        BufferedImage img = null;
        File f = null;

        // Read image
        try {
            f = new File("input.png");
            img = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println(e);
        }

        // create BufferedImage object of same width and
        // height as of input image
        BufferedImage temp = new BufferedImage(img.getWidth(),
                img.getHeight(), BufferedImage.TYPE_INT_RGB);

        // Create graphics object and add original
        // image to it
        Graphics graphics = temp.getGraphics();
        graphics.drawImage(img, 0, 0, null);

        // Set font for the watermark text
        graphics.setFont(new Font("Arial", Font.PLAIN, 20));
        graphics.setColor(new Color(255, 0, 0, 40));

        // Setting watermark text
        String watermark = "02/01/2022";

        // Add the watermark text at (width/5, height/3)
        // location
        graphics.drawString(watermark, img.getWidth() / 10,
                img.getHeight() - 20);

        // releases any system resources that it is using
        graphics.dispose();

        f = new File("output.png");
        try {
            ImageIO.write(temp, "png", f);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}