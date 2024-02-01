package converters.ASCII

import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.RGBPixel
import appConfig.DefaultAsciiMapping

/**
 * NonLinearAsciiConverter converts an image to ASCII art using a non-linear mapping of greyscale values to ASCII characters.
 * The conversion strategy is based on a custom mapping.
 */
class NonLinearAsciiConverter(mapping: Map[Char, (Double, Double)] = DefaultAsciiMapping.getMapping) extends AsciiConverter[RGBPixel] {

  private def getCharForGreyscale(value: Double): Char = {
    mapping.find { case (_, (min, max)) => value >= min && value <= max }.map(_._1).getOrElse(' ')
  }

  override def convert(image: Image[RGBPixel]): String = {
    val stringBuilder = new StringBuilder()

    for {
      y <- 0 until image.height
      x <- 0 until image.width
    } {
      val pixel = image.getPixel(x, y).get
      val grey = GreyscaleUtil.toGreyscale(pixel)
      val char = getCharForGreyscale(grey)
      stringBuilder.append(char)
      if (x == image.width - 1) stringBuilder.append("\n")
    }

    stringBuilder.toString()
  }
}



