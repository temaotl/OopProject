package Helpers

import asciiArtApp.models.image.pixelGrid.Raster
import asciiArtApp.models.image.pixelGrid.pixels.RGBPixel
import repositories.memory.pixels.InMemoryPixelStorage
/**
 * ImageHelper is a utility object that provides methods to assist with image processing tasks.
 * It currently includes a method for initializing an image with specified dimensions.
 */
object ImageHelper {

  /**
   * Initializes an image with the given dimensions and prepares it for pixel manipulation.
   * Creates a new InMemoryPixelStorage and Raster, which are essential components for image processing.
   *
   * @param width The width of the image to be initialized.
   * @param height The height of the image to be initialized.
   * @return A tuple containing an InMemoryPixelStorage and a Raster for the new image.
   */
  def initializeImage(width: Int, height: Int): (InMemoryPixelStorage[RGBPixel], Raster[RGBPixel]) = {
    val storage = new InMemoryPixelStorage[RGBPixel]()
    val raster = new Raster[RGBPixel](width, height, storage)
    (storage, raster)
  }
}
