package asciiArtApp.models.image

import asciiArtApp.models.image.pixelGrid.pixels.Pixel
import asciiArtApp.models.image.pixelGrid.Raster

/**
 * Represents an image with a grid of pixels.It also keeps track of its conversion type, which can be linear or some other type.
 *
 * @param width the width of the image.
 * @param height the height of the image.
 * @param pixelGrid the raster that holds the pixel data.
 * @tparam T the type of pixel this image holds, which must extend `Pixel`.
 */

class Image[T <: Pixel](val width: Int,
                        val height: Int,
                        val pixelGrid: Raster[T]) {

  require(height > 0 && width > 0)

  def getHeight: Int = height
  def getWidth: Int = width

  def getPixel(x: Int, y: Int): Option[T] = pixelGrid.getPixel(x, y)
}




