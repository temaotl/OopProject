package filters

import ImgRes.TestImgRes.imagePathJPG
import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.RGBPixel
import filter.FilterFlip.FlipFilterController.applyBoth
import filter.FilterFlip.{FlipXFilter, FlipYFilter}
import input_output.Loader.FileImg.ConcreteStrategies.JPEGImageLoaderStrategy
import org.scalatest.FunSuite

class FlipFilterTest extends FunSuite {


  val strategy = new JPEGImageLoaderStrategy
  val testImage: Image[RGBPixel] = strategy.loadImage(imagePathJPG)

  test("FlipXFilter should flip image on x-axes correctly") {
    val flippedImage = FlipXFilter.apply(testImage, "x")

    assert(flippedImage.width == testImage.width)
    assert(flippedImage.height == testImage.height)


    assert(flippedImage.getPixel(0, 0) == testImage.getPixel(0, testImage.height - 1))


  }

  test("FlipYFilter should flip image on y-axes correctly") {
    val flippedImage = FlipYFilter.apply(testImage, "y")

    assert(flippedImage.width == testImage.width)
    assert(flippedImage.height == testImage.height)

    assert(flippedImage.getPixel(0, 0) == testImage.getPixel(testImage.width - 1, 0))
  }

  def comparePixels(pixel1: RGBPixel, pixel2: RGBPixel): Boolean = {
    pixel1.red == pixel2.red && pixel1.green == pixel2.green && pixel1.blue == pixel2.blue
  }

  test("Apply both X and Y filters to an image and compare with applyBoth") {
    val xFlipped = FlipXFilter(testImage, "x")

    val xyFlipped = FlipYFilter(xFlipped, "y")

    val applyBothImage = applyBoth(testImage)

    var allPixelsAreSame = true
    for (x <- 0 until testImage.getWidth; y <- 0 until testImage.getHeight) {
      if (!comparePixels(xyFlipped.getPixel(x, y).getOrElse(throw new Exception("Pixel not found")),
        applyBothImage.getPixel(x, y).getOrElse(throw new Exception("Pixel not found")))) {
        allPixelsAreSame = false
      }
    }

    assert(allPixelsAreSame, "The images are not the same after applying filters sequentially vs using applyBoth")
  }


  test("FlipXFilter should throw exception for null image") {
    assertThrows[Exception] {
      FlipXFilter.apply(null, "x")
    }
  }

  test("FlipYFilter should throw exception for null image") {
    assertThrows[Exception] {
      FlipYFilter.apply(null, "y")
    }
  }

  test("FlipXFilter should throw exception for invalid axis") {
    assertThrows[Exception] {
      FlipXFilter.apply(testImage, "invalid_axis")
    }
  }

  test("FlipYFilter should throw exception for invalid axis") {
    assertThrows[Exception] {
      FlipYFilter.apply(testImage, "invalid_axis")
    }
  }


}
