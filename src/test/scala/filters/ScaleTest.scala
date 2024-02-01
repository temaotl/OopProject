package filters

import ImgRes.TestImgRes.imagePathJPG
import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.RGBPixel
import filter.ScaleFilter
import input_output.Loader.FileImg.ConcreteStrategies.JPEGImageLoaderStrategy
import org.scalatest.FunSuite

class ScaleTest extends FunSuite{
  val strategy = new JPEGImageLoaderStrategy
  val image: Image[RGBPixel] = strategy.loadImage(imagePathJPG)

  test("ScaleFilter scales up correctly") {

    val scaledImage = ScaleFilter.apply(image, 4)

    assert(scaledImage.getWidth == image.getWidth * 4)
    assert(scaledImage.getHeight == image.getHeight * 4)

  }

  test("ScaleFilter scales down correctly") {
    val scaledImage = ScaleFilter.apply(image, 0.25)

    assert(scaledImage.getWidth == (image.getWidth * 0.25).toInt)
    assert(scaledImage.getHeight == (image.getHeight * 0.25).toInt)

  }

  test("ScaleFilter does not modify original image when scale is 1") {
    val scaledImage = ScaleFilter.apply(image, 1)

    assert(scaledImage.getWidth == image.getWidth)
    assert(scaledImage.getHeight == image.getHeight)
  }

  test("ScaleFilter should throw exception for scale factor zero") {
    assertThrows[Exception] {
      ScaleFilter.apply(image, 0)
    }
  }

  test("ScaleFilter should throw exception for negative scale factor") {
    assertThrows[Exception] {
      ScaleFilter.apply(image, -1)
    }
  }

  test("ScaleFilter should throw exception for excessively large scale factor") {
    assertThrows[Exception] {
      ScaleFilter.apply(image, 100)
    }
  }

  test("ScaleFilter should throw exception for null image") {
    assertThrows[Exception] {
      ScaleFilter.apply(null, 2)
    }
  }

}
