package asciiArtApp.models.image.pixelGrid.pixels

/**
 * Trait that represents a general pixel abstraction.
 */
trait  Pixel
{
  /**
   * Retrieves the value of the pixel.
   *
   * @return an integer representing the pixel's value.
   */
  def value: Int
}
