package filter

import Helpers.ImageHelper
import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.RGBPixel

/**
 * ImageTransformer is a utility object providing a method to apply transformations to images.
 * It operates on images with RGB pixels and applies a specified pixel transformation function.
 */
object ImageTransformer {

  /**
   * Transforms an image using a provided pixel transformation function.
   * The function defines how each pixel in the image should be transformed.
   *
   * @param image The image to transform.
   * @param pixelTransform A function that takes the x and y coordinates of a pixel, along with the image,
   *                       and returns a new RGBPixel representing the transformed pixel.
   * @return A new Image object representing the transformed image.
   */
  def transform(image: Image[RGBPixel], pixelTransform: (Int, Int, Image[RGBPixel]) => RGBPixel): Image[RGBPixel] = {
    val width = image.getWidth
    val height = image.getHeight
    val (_, raster) = ImageHelper.initializeImage(width, height)

    for (x <- 0 until width; y <- 0 until height) {
      val newPixel = pixelTransform(x, y, image)
      raster.setPixel(x, y, newPixel)
    }

    new Image[RGBPixel](width, height, raster)
  }
}

