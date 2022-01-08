import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Tp {
    static void watermarkImage(String imageFilePath, String message) throws IOException {
        BufferedImage image;
        File input = new File(imageFilePath);
        image = ImageIO.read(input);
        int width = image.getWidth();
        int height = image.getHeight();
        int counter = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(image.getRGB(i, j));
                if (counter < message.length()) {
                    String binaryBlue = Integer.toBinaryString(c.getBlue());
                    String newBinaryBlue = binaryBlue.substring(0, 7) + message.charAt(counter);
                    int decimalBlue = Integer.parseInt(newBinaryBlue, 2);
                    Color c2 = new Color(c.getRed(), c.getGreen(), decimalBlue);
                    image.setRGB(i, j, c2.getRGB());
                    counter++;
                }
            }
        }
        BufferedImage temp = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics graphics = temp.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        File f = new File("output.png");
        ImageIO.write(temp, "png", f);
    }

    static String convertToBinAsciiCode(String text) {
        String message = "";
        for (int i = 0; i < text.length(); i++) {
            int ascii = (int) text.charAt(i);
            String binaryAscii = Integer.toBinaryString(ascii);
            while (binaryAscii.length() < 8)
                binaryAscii = "0" + binaryAscii;
            message += binaryAscii;
        }
        return message;
    }

    static String extractWaterMark(String imagePath) throws IOException {
        BufferedImage image;
        File input = new File(imagePath);
        image = ImageIO.read(input);
        int width = image.getWidth();
        int height = image.getHeight();
        String binMessage = "";
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(image.getRGB(i, j));
                binMessage += Integer.toBinaryString(c.getBlue()).charAt(7);
                if (binToChars(binMessage).indexOf("end msg") != -1)
                    return binToChars(binMessage);
            }
        }
        return binToChars(binMessage);
    }

    static String binToChars(String binString) {
        String chars = "";
        char nextChar;
        for (int i = 0; i <= binString.length() - 8; i += 8) {
            nextChar = (char) Integer.parseInt(binString.substring(i, i + 8), 2);
            chars += nextChar;
        }
        return chars;
    }

    public static void main(String[] args) throws IOException {
        String text = "Hello end msg";
        String imageFilepath = "input.png";
        String message = convertToBinAsciiCode(text);
        watermarkImage(imageFilepath, message);
        String extractedText = extractWaterMark("output.png");
        System.out.println(extractedText);
    }
}