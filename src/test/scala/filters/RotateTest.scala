package filters

import ImgRes.TestImgRes.imagePathJPG
import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.RGBPixel
import filter.RotateFilter
import input_output.Loader.FileImg.ConcreteStrategies.JPEGImageLoaderStrategy
import org.scalatest.FunSuite

class RotateTest extends FunSuite {
  val strategy = new JPEGImageLoaderStrategy
  val image: Image[RGBPixel] = strategy.loadImage(imagePathJPG)


  test("RotateFilter rotates 90 degrees correctly") {
    val rotateImage = RotateFilter.apply(image, 90)

    assert(image.width == rotateImage.height)
    assert(image.height == rotateImage.width)

    val topLeftOriginal = image.getPixel(0, 0).get
    val topLeftRotated = rotateImage.getPixel(0, rotateImage.height - 1).get

    assert(topLeftOriginal == topLeftRotated)

    val bottomLeftOriginal = image.getPixel(0, image.height - 1).get
    val bottomLeftRotated = rotateImage.getPixel(0, 0).get

    assert(bottomLeftOriginal == bottomLeftRotated)
  }

  test("RotateFilter rotates 180 degrees correctly") {
    val rotateImage = RotateFilter.apply(image, 180)

    assert(image.width == rotateImage.width)
    assert(image.height == rotateImage.height)

    val topLeftOriginal = image.getPixel(0, 0).get
    val topLeftRotated = rotateImage.getPixel(rotateImage.width - 1, rotateImage.height - 1).get

    assert(topLeftOriginal == topLeftRotated)

    val bottomLeftOriginal = image.getPixel(0, image.height - 1).get
    val bottomLeftRotated = rotateImage.getPixel(rotateImage.width - 1, 0).get

    assert(bottomLeftOriginal == bottomLeftRotated)
  }

  test("RotateFilter rotates 270 degrees correctly") {
    val rotateImage = RotateFilter.apply(image, 270)

    assert(image.width == rotateImage.height)
    assert(image.height == rotateImage.width)

    val topLeftOriginal = image.getPixel(0, 0).get
    val topLeftRotated = rotateImage.getPixel(rotateImage.width - 1, 0).get

    assert(topLeftOriginal == topLeftRotated)

    val bottomLeftOriginal = image.getPixel(0, image.height - 1).get
    val bottomLeftRotated = rotateImage.getPixel(rotateImage.width - 1, rotateImage.height - 1).get

    assert(bottomLeftOriginal == bottomLeftRotated)
  }

  test("RotateFilter should throw exception for null image") {
    assertThrows[Exception] {
      RotateFilter.apply(null, 90)
    }
  }

  test("RotateFilter should throw exception for invalid angle") {
    assertThrows[Exception] {
      RotateFilter.apply(image, 48)
    }
  }



  test("RotateFilter should throw exception for angle greater than 360") {
    assertThrows[Exception] {
      RotateFilter.apply(image, 370)
    }
  }

}

