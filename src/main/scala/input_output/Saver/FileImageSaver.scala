package input_output.Saver

import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.AsciiPixel

import java.io.{File, PrintWriter}

/**
 * FileImageSaver is an implementation of the ImageSaver interface that saves an ASCII image to a file.
 * It takes a file path as a parameter and writes the ASCII representation of the image to this file.
 *
 * @param path The file path where the ASCII image will be saved.
 */
class FileImageSaver(path: String) extends ImageSaver {

  /**
   * Saves the provided ASCII image to a file.
   * Iterates through each pixel of the image and writes the corresponding ASCII character to the file.
   *
   * @param image The ASCII image to be saved.
   * @throws IllegalArgumentException If a pixel in the image is not found during processing.
   */
  override def saveImage(image: Image[AsciiPixel]): Unit = {
    val writer = new PrintWriter(new File(path))
    for (y <- 0 until image.getHeight) {
      for (x <- 0 until image.getWidth) {
        val pixel = image.getPixel(x, y).getOrElse(throw new IllegalArgumentException(s"Pixel $x $y not found"))
        writer.print(pixel.char)
      }
      writer.println()
    }
    writer.close()
  }
}

