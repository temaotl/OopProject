package filter.FilterFlip

import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.RGBPixel
import filter.{ImageFilter, ImageTransformer}

/**
 * Filter to flip an image horizontally (along X-axis).
 */
object FlipXFilter extends ImageFilter[RGBPixel, String] {
  override def apply(image: Image[RGBPixel], flipType: String): Image[RGBPixel] = {
    require(flipType == "x", "Flip type should be 'x' for this filter.")
    ImageTransformer.transform(image, (x, y, img) => img.getPixel(img.getWidth - 1 - x, y).getOrElse(throw new IllegalArgumentException("Pixel not found")))
  }
}

