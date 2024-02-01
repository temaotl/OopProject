package converters.images

import asciiArtApp.models.image.pixelGrid.pixels.AsciiPixel
import converters.Images.AsciiStringToAsciiImageConverter
import org.scalatest.FunSuite

class AsciiStringToAsciiImageConverterTest extends FunSuite {

  test("Should correctly convert ASCII string to image") {
    val asciiContent = "AB\nCD"
    val image = AsciiStringToAsciiImageConverter.asciiContentToImage(asciiContent)
    assert(image.getWidth == 2)
    assert(image.getHeight == 2)
  }

  test("Should correctly map ASCII characters to pixels") {
    val asciiContent = "AB\nCD"
    val image = AsciiStringToAsciiImageConverter.asciiContentToImage(asciiContent)
    assert(image.getPixel(0, 0).contains(AsciiPixel('A')))
    assert(image.getPixel(1, 0).contains(AsciiPixel('B')))
    assert(image.getPixel(0, 1).contains(AsciiPixel('C')))
    assert(image.getPixel(1, 1).contains(AsciiPixel('D')))
  }
  test("Should handle different line lengths correctly") {
    val asciiContent = "A\nBC\nDEF"
    val image = AsciiStringToAsciiImageConverter.asciiContentToImage(asciiContent)
    assert(image.getWidth == 1)
    assert(image.getHeight == 3)
  }

  test("Should handle string without newline correctly") {
    val asciiContent = "ABC"
    val image = AsciiStringToAsciiImageConverter.asciiContentToImage(asciiContent)
    assert(image.getWidth == 3)
    assert(image.getHeight == 1)
  }

}
