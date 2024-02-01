package filter.FilterFlip

import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.RGBPixel
import filter.{ImageFilter, ImageTransformer}


/**
 * Filter to flip an image vertical (along Y-axis).
 */
object FlipYFilter extends ImageFilter[RGBPixel, String] {
  override def apply(image: Image[RGBPixel], flipType: String): Image[RGBPixel] = {
    require(flipType == "y", "Flip type should be 'y' for this filter.")
    ImageTransformer.transform(image, (x, y, img) => img.getPixel(x, img.getHeight - 1 - y).getOrElse(throw new IllegalArgumentException("Pixel not found")))
  }
}
