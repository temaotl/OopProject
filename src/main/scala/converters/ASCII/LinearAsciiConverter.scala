package converters.ASCII

import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.RGBPixel

/**
 * LinearAsciiConverter converts an image to ASCII art using a linear mapping of greyscale values to ASCII characters.
 * The conversion uses a predefined palette of ASCII characters.
 *
 * @param palette A string representing the ASCII characters used for conversion. Defaults to a standard set.
 */
class LinearAsciiConverter(palette: String = "@%#*+=-:. ") extends AsciiConverter[RGBPixel] {
  private val charSizeOnPalette: Double = 255.0 / (palette.length - 1)

  override def convert(image: Image[RGBPixel]): String = {
    val stringBuilder = new StringBuilder()

    for {
      y <- 0 until image.height
      x <- 0 until image.width
    } {
      val pixel = image.getPixel(x, y).get
      val grey = GreyscaleUtil.toGreyscale(pixel)
      val charIndex = (grey / charSizeOnPalette).toInt
      stringBuilder.append(palette(charIndex))
      if (x == image.width - 1) stringBuilder.append("\n")
    }

    stringBuilder.toString()
  }
}


