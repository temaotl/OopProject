package input_output.Saver

import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.AsciiPixel

/**
 * A trait defining the interface for saving images. Implementations of this trait
 * should provide mechanisms to save an image to a specified destination.
 */
trait ImageSaver {
  /**
   * Saves the provided image to the specified destination. The exact format and
   * method of saving might differ depending on the implementation of this trait.
   *
   * @param image       The image of type AsciiPixel to be saved.
   */
  def saveImage(image: Image[AsciiPixel]): Unit
}


