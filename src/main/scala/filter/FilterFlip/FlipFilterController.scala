package filter.FilterFlip

import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.RGBPixel

/**
 * Controller to apply both X and Y flips to an image.
 */
object FlipFilterController {

  def applyBoth(image: Image[RGBPixel]): Image[RGBPixel] = {
    val xFlipped = FlipXFilter(image, "x")
    FlipYFilter(xFlipped, "y")
  }
}

