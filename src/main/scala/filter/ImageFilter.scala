package filter

import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.Pixel

/**
 * A trait that represents a filter to be applied on an image.
 *
 * @tparam T The type of pixel that the image contains. Must be a subtype of `Pixel`.
 * @tparam P The type of parameter that the filter requires to process the image.
 */
trait ImageFilter[T <: Pixel, P] {


  /**
   * Apply the filter to a given image based on the provided parameter.
   *
   * @param image     The image to which the filter will be applied.
   * @param parameter The parameter based on which the filter processes the image.
   * @return A new image after the filter has been applied.
   */
  def apply(image: Image[T], parameter: P): Image[T]
}



