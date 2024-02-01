package converters.Images

import Helpers.ImageHelper
import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.RGBPixel


import java.awt.Color
import java.awt.image.BufferedImage

/**
 * Singleton object responsible for converting a Java BufferedImage instance into a custom Image instance.
 */
object BufferedImageToImage {

  /**
   * Converts a given BufferedImage into a custom Image instance with RGBPixel representation.
   *
   * This function extracts the RGB values from the input BufferedImage and populates the resulting
   * Image instance with RGBPixel instances.
   *
   * @param bufferedImage The Java BufferedImage instance to be converted.
   * @return An Image instance representing the BufferedImage's pixel data.
   */
  def bufferedImageToImage(bufferedImage: BufferedImage): Image[RGBPixel] = {
    val width = bufferedImage.getWidth
    val height = bufferedImage.getHeight
    val (_, raster) = ImageHelper.initializeImage(width, height)

    for {
      x <- 0 until width
      y <- 0 until height
    } {
      val color = new Color(bufferedImage.getRGB(x, y))
      val pixel = RGBPixel(color.getRed, color.getGreen, color.getBlue)
      raster.setPixel(x, y, pixel)
    }

    new Image[RGBPixel](width, height, raster)
  }
}

