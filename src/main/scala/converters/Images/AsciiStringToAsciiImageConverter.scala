package converters.Images

import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.AsciiPixel
import asciiArtApp.models.image.pixelGrid.Raster
import repositories.memory.pixels.InMemoryPixelStorage


/**
 * Singleton object responsible for converting ASCII art content to an image representation.
 */
object AsciiStringToAsciiImageConverter {
  /**
   * Converts a string containing ASCII art content into an Image instance using AsciiPixel representation.
   *
   * The function assumes that ASCII art content uses newline characters ('\n') to denote new rows,
   * and that all rows are of equal width. Each character in the ASCII content corresponds to a pixel
   * in the resulting image.
   *
   * @param asciiContent A string representing the ASCII art content to be converted.
   * @return An Image instance that contains the representation of the ASCII content.
   */
  def asciiContentToImage(asciiContent: String): Image[AsciiPixel] = {
    val width = if(asciiContent.indexOf('\n') == -1) asciiContent.length else asciiContent.indexOf('\n')
    val height = asciiContent.split("\n").length

    val storage = new InMemoryPixelStorage[AsciiPixel]()
    val raster = new Raster[AsciiPixel](width, height, storage)

    var index = 0
    for (y <- 0 until height; x <- 0 until width) {
      if (asciiContent.charAt(index) == '\n') {
        index += 1
      }
      raster.setPixel(x, y,AsciiPixel(asciiContent.charAt(index)))
      index += 1
    }
    new Image[AsciiPixel](width, height, raster)
  }
}

