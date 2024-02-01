package filter

import Helpers.ImageHelper
import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.RGBPixel


/**
 * Filter for scaling an RGB image by a specified factor.
 * Currently, only scaling by factors of 0.25 (downscaling), 1 (no scaling), and 4 (upscaling) are supported.
 */
object ScaleFilter extends ImageFilter[RGBPixel, Double] {
  override def apply(image: Image[RGBPixel], scale: Double): Image[RGBPixel] = scale match {
    case 1 => image
    case 0.25 | 4 => rescale(image, scale)
    case _ => throw new IllegalArgumentException(s"Unsupported scale value: $scale")
  }

  private def rescale(image: Image[RGBPixel], scale: Double): Image[RGBPixel] = {
    val newWidth = (image.getWidth * scale).toInt
    val newHeight = (image.getHeight * scale).toInt
    val (_, raster) = ImageHelper.initializeImage(newWidth, newHeight)

    for {
      x <- 0 until newWidth
      y <- 0 until newHeight
    } {
      val originalX = Math.min((x / scale).toInt, image.getWidth - 1)
      val originalY = Math.min((y / scale).toInt, image.getHeight - 1)
      val originalPixel = image.getPixel(originalX, originalY).getOrElse(throw new IllegalArgumentException("Pixel not found"))
      raster.setPixel(x, y, originalPixel)
    }
    new Image[RGBPixel](newWidth, newHeight, raster)
  }
}



