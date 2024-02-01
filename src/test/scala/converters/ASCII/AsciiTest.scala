package converters.ASCII

import ImgRes.TestImgRes.imagePathJPG
import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.RGBPixel
import input_output.Loader.FileImg.ConcreteStrategies.JPEGImageLoaderStrategy
import org.scalatest.FunSuite

class AsciiTest extends FunSuite {

  val loader = new JPEGImageLoaderStrategy
  val image: Image[RGBPixel] = loader.loadImage(imagePathJPG)

  test("LinearAsciiConverterTest") {
    val converter = new LinearAsciiConverter()
    val asciiArt = converter.convert(image)

    val asciiRows = asciiArt.split("\n")
    assert(asciiRows.length == image.height)
  }

  test("NonLinearAsciiConverterTest") {
    val converter = new NonLinearAsciiConverter()
    val asciiArt = converter.convert(image)

    val asciiRows = asciiArt.split("\n")
    assert(asciiRows.length == image.height)

  }

  test("LinearAsciiConverter should throw exception for null image") {
    val converter = new LinearAsciiConverter()
    assertThrows[Exception] {
      converter.convert(null)
    }
  }

  test("NonLinearAsciiConverter should throw exception for null image") {
    val converter = new NonLinearAsciiConverter()
    assertThrows[Exception] {
      converter.convert(null)
    }
  }


}
