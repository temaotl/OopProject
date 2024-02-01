package input_output.Loader

import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.Pixel

/**
 * Represents a strategy for loading images from a source.
 * The loaded image will have pixels of type T, where T is a subtype of Pixel.
 *
 * @tparam T  The pixel type of the loaded image.
 */
trait ImageLoaderStrategy[T <: Pixel] {

  /**
   * Loads an image from the specified source.
   *
   * Implementations should handle the specifics of reading the image
   * and converting it to the Image type with pixels of type T.
   *
   * @param source The path or identifier for the image source.
   * @return The loaded image with pixels of type T.
   */

  def loadImage(source: String): Image[T]
}

