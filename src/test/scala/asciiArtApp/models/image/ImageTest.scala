package asciiArtApp.models.image

import asciiArtApp.models.image.pixelGrid.Raster
import asciiArtApp.models.image.pixelGrid.pixels.RGBPixel
import org.scalatest.FunSuite
import repositories.memory.pixels.InMemoryPixelStorage

class ImageTest extends FunSuite {

  val width = 10
  val height = 10
  val storage = new InMemoryPixelStorage[RGBPixel]
  val raster = new Raster[RGBPixel](width, height, storage)

  test("Image should have correct dimensions") {
    val image = new Image(width, height, raster)
    assert(image.getWidth == width)
    assert(image.getHeight == height)
  }

  test("Should get and set pixel correctly") {
    val image = new Image(width, height, raster)
    val pixel = RGBPixel(255, 0, 0)
    raster.setPixel(5, 5, pixel)
    assert(image.getPixel(5, 5).contains(pixel))
  }

  test("Should handle pixel out of bounds correctly") {
    val image = new Image(width, height, raster)
    assertThrows[IllegalArgumentException] {
      image.getPixel(11, 11)
    }
  }


  test("Should throw exception when setting pixel out of bounds") {
    val image = new Image(width, height, raster)
    val pixel = RGBPixel(255, 0, 0)
    assertThrows[IllegalArgumentException] {
      raster.setPixel(11, 11, pixel)
    }
  }

  test("Should throw exception when getting pixel with negative coordinates") {
    val image = new Image(width, height, raster)
    assertThrows[IllegalArgumentException] {
      image.getPixel(-1, -1)
    }
  }

  test("Should throw exception when setting pixel with negative coordinates") {
    val image = new Image(width, height, raster)
    val pixel = RGBPixel(255, 0, 0)
    assertThrows[IllegalArgumentException] {
      raster.setPixel(-1, -1, pixel)
    }
  }

  test("Should throw exception when creating image with negative dimensions") {
    val invalidWidth = -10
    val invalidHeight = -10
    assertThrows[IllegalArgumentException] {
      new Image(invalidWidth, invalidHeight, raster)
    }
  }

}
