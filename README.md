# watermarking-an-image

In this set we will be reading image pixels and generating a watermark and apply it on an input image. 
For generating a text and applying it in an image we will use java.awt.Graphics package. Font and color of text is applied by using java.awt.Color and java.awt.Font classes.

getGraphics() – This method is found in BufferedImage class, and it returns a 2DGraphics object out of image file.

drawImage(Image img, int x, int y, ImageObserver observer) – The x,y location specifies the position for the top-left of the image. The observer parameter notifies the application of updates to an image that is loaded asynchronously. The observer parameter is not frequently used directly and is not needed for the BufferedImage class, so it usually is null.

setFont(Font f) – This method is found in Font class of awt package and the constructor takes (FONT_TYPE, FONT_STYLE, FONT_SIZE) as arguments.

setColor(Color c) – This method is found in Color class of awt package and the constructor takes (R, G, B, A) as arguments.

drawString(String str, int x, int y) – Fond in Graphics class takes the string text and the location coordinates as x and y as arguments.
