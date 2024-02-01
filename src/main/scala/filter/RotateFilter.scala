package filter

import Helpers.ImageHelper
import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.RGBPixel

/**
 * Filter for rotating an RGB image by a specified degree.
 * Currently, only rotations by 90-degree intervals are supported.
 */
object RotateFilter extends ImageFilter[RGBPixel,Double] {

  /**
   * Applies a rotation to the provided image based on the given rotation parameter.
   * The rotation parameter should be a multiple of 90 degrees.
   *
   * @param image             The image to which the rotation will be applied.
   * @param rotationParameter The degree of rotation in multiples of 90.
   * @return A new image after the rotation operation.
   */
  override def apply(image: Image[RGBPixel], rotationParameter: Double): Image[RGBPixel] = {
    var rotate = rotationParameter.toInt
    if(rotate % 90 != 0)
      throw new Exception("Wrong degree, should be dividable by 90")
    if(rotate < 0)
      rotate = (-1) * rotate
    rotate = rotate % 360

    rotate match {
      case 0  => image
      case 90 => rotate90Degrees(image)
      case 180 => rotate90Degrees(rotate90Degrees(image))
      case 270 => rotate90Degrees(rotate90Degrees(rotate90Degrees(image)))
      case _ => throw new Exception("Unknown rotation degree")
    }
  }

  /**
   * Rotates the provided image by 90 degrees clockwise.
   *
   * @param image The image to rotate.
   * @return A new image after the 90-degree rotation.
   */
  private def rotate90Degrees(image: Image[RGBPixel]): Image[RGBPixel] = {
    val width = image.width
    val height = image.height
    val (_, raster) = ImageHelper.initializeImage(width, height)

    for(i <- 0 until width) {
      for(j <- 0 until height) {
        val originalPixel = image.getPixel(i, height - j - 1).getOrElse(throw new IllegalArgumentException("Pixel not found"))
        raster.setPixel(i, j, originalPixel)
      }
    }

    new Image[RGBPixel](width, height, raster)
  }
}

