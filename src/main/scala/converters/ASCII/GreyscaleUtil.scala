package converters.ASCII

import asciiArtApp.models.image.pixelGrid.pixels.RGBPixel

object GreyscaleUtil {
  def toGreyscale(pixel: RGBPixel): Double = {
    (0.3 * pixel.red) + (0.59 * pixel.green) + (0.11 * pixel.blue)
  }
}

