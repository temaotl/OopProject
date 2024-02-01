package converters.ASCII

import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.Pixel

/**
 * AsciiConverter trait defines a common interface for converting images into ASCII strings.
 * It is generic over a type T which extends the Pixel trait.
 */
trait AsciiConverter[T <: Pixel] {

  /**
   * Converts an image of type T to its ASCII string representation.
   *
   * @param image The image to convert.
   * @return The ASCII string representation of the image.
   */
  def convert(image: Image[T]): String
}
