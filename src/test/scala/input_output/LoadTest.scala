package input_output

import ImgRes.TestImgRes.{imagePathGif, imagePathJPG, imagePathPng}
import input_output.Loader.FileImg.ConcreteStrategies.{GIFImageLoaderStrategy, JPEGImageLoaderStrategy, PNGImageLoaderStrategy}
import org.scalatest.FunSuite

class LoadTest extends FunSuite{


  test("JPEGImageLoaderStrategy loads JPEG image correctly") {
    val strategy = new JPEGImageLoaderStrategy
    val image = strategy.loadImage(imagePathJPG)
    assert(image.width > 0)
    assert(image.height > 0)
  }

  test("PNGImageLoaderStrategy loads PNG image correctly") {
    val strategy = new PNGImageLoaderStrategy
    val image = strategy.loadImage(imagePathPng)
    assert(image.width > 0)
    assert(image.height > 0)
  }

  test("GIFImageLoaderStrategy loads GIF image correctly") {
    val strategy = new GIFImageLoaderStrategy
    val image = strategy.loadImage(imagePathGif)

    assert(image.width > 0)
    assert(image.height > 0)
  }

  test("JPEGImageLoaderStrategy should throw exception for nonexistent JPEG path") {
    val strategy = new JPEGImageLoaderStrategy
    val nonexistentPath = "nonexistent/path/image.jpg"
    assertThrows[Exception] {
      strategy.loadImage(nonexistentPath)
    }
  }

  test("PNGImageLoaderStrategy should throw exception for nonexistent PNG path") {
    val strategy = new PNGImageLoaderStrategy
    val nonexistentPath = "nonexistent/path/image.png"
    assertThrows[Exception] {
      strategy.loadImage(nonexistentPath)
    }
  }

  test("JPEGImageLoaderStrategy should throw exception for invalid JPEG format") {
    val strategy = new JPEGImageLoaderStrategy
    val invalidImagePath = "path/to/invalid/format/image.png"
    assertThrows[Exception] {
      strategy.loadImage(invalidImagePath)
    }
  }

  test("PNGImageLoaderStrategy should throw exception for invalid PNG format") {
    val strategy = new PNGImageLoaderStrategy
    val invalidImagePath = "path/to/invalid/format/image.jpg"
    assertThrows[Exception] {
      strategy.loadImage(invalidImagePath)
    }
  }

  test("JPEGImageLoaderStrategy should throw exception for corrupted JPEG image") {
    val strategy = new JPEGImageLoaderStrategy
    val corruptedImagePath = "path/to/corrupted/image.jpg"
    assertThrows[Exception] {
      strategy.loadImage(corruptedImagePath)
    }
  }

  test("PNGImageLoaderStrategy should throw exception for corrupted PNG image") {
    val strategy = new PNGImageLoaderStrategy
    val corruptedImagePath = "path/to/corrupted/image.png"
    assertThrows[Exception] {
      strategy.loadImage(corruptedImagePath)
    }
  }
  
}
