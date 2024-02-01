package input_output.Saver

import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.AsciiPixel

/**
 * ConsoleImageSaver is an implementation of ImageSaver that outputs an ASCII image to the console.
 * It is designed to handle images composed of AsciiPixels and prints them in a formatted manner.
 */
class ConsoleImageSaver extends ImageSaver {

  /**
   * Outputs the provided ASCII image to the console.
   * Iterates through each pixel of the image and prints the corresponding ASCII character.
   *
   * @param image The ASCII image to be outputted.
   * @throws IllegalArgumentException If a pixel in the image is not found during processing.
   */

  override def saveImage(image: Image[AsciiPixel]): Unit = {
    for (y <- 0 until image.getHeight) {
      for (x <- 0 until image.getWidth) {
        val pixel = image.getPixel(x, y).getOrElse(throw new IllegalArgumentException("Pixel not found"))
        print(pixel.char)
      }
      println()
    }
  }
}
