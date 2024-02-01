package input_output.Loader.RandomImg

import Helpers.ImageHelper
import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.RGBPixel
import input_output.Loader.ImageLoaderStrategy




/**
 * An image loader strategy that generates a random image with specified width and height.
 * Each pixel in the generated image will be filled with a random color.
 *
 * @param width  The width of the image to be generated.
 * @param height The height of the image to be generated.
 */
class RandomImageLoaderStrategy(width: Int, height: Int) extends ImageLoaderStrategy[RGBPixel] {

  private def randomPixel(): RGBPixel = {
    val red = (Math.random() * 255).toInt
    val green = (Math.random() * 255).toInt
    val blue = (Math.random() * 255).toInt
    RGBPixel(red, green, blue)
  }

  override def loadImage(source: String): Image[RGBPixel] = {
    val (_, raster) = ImageHelper.initializeImage(width, height)

    for {
      x <- 0 until width
      y <- 0 until height
    } {
      raster.setPixel(x, y, randomPixel())
    }

    new Image[RGBPixel](width, height, raster)
  }
}

