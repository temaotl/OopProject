package asciiArtApp.models.image.pixelGrid

import asciiArtApp.models.image.pixelGrid.pixels.Pixel
import repositories.memory.pixels.PixelStorage

/**
 * Represents a raster of pixels, a 2D grid that can hold pixel data for an image.
 *
 * @param width the width of the raster.
 * @param height the height of the raster.
 * @param storage the storage mechanism to handle pixel data.
 */
class Raster[T <: Pixel](width: Int, height: Int, storage: PixelStorage[T]) {

  /**
   * Validates that the given coordinates are within the bounds of the raster.
   *
   * @param x the x-coordinate to validate.
   * @param y the y-coordinate to validate.
   * @throws IllegalArgumentException if the coordinates are out of bounds.
   */

  private def validateCoordinates(x: Int, y: Int): Unit = {
    if (x < 0 || x >= width || y < 0 || y >= height) {
      throw new IllegalArgumentException(s"Coordinates ($x, $y) are out of bounds.")
    }
  }

  /**
   * Retrieves the pixel at the specified coordinates from the raster.
   *
   * @param x the x-coordinate of the desired pixel.
   * @param y the y-coordinate of the desired pixel.
   * @return an option containing the pixel if it exists, or None if it doesn't.
   */
  def getPixel(x: Int, y: Int): Option[T] = {
    validateCoordinates(x, y)
    storage.read((x, y))
  }

  /**
   * Sets or modifies the pixel at the specified coordinates in the raster.
   *
   * @param x     the x-coordinate where the pixel should be placed.
   * @param y     the y-coordinate where the pixel should be placed.
   * @param pixel the pixel data to be set at the specified coordinates.
   */
  def setPixel(x: Int, y: Int, pixel: T): Unit = {
    validateCoordinates(x, y)
    storage.modify((x, y), pixel)
  }

}

