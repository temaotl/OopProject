package asciiArtApp.facades

import ImgRes.TestImgRes.{getTestFile, imagePathJPG}
import appConfig.UserSettings
import converters.Images.AsciiStringToAsciiImageConverter
import input_output.Loader.FileImg.ConcreteStrategies.JPEGImageLoaderStrategy
import input_output.Loader.RandomImg.RandomImageLoaderStrategy
import input_output.Saver.FileImageSaver
import org.scalatest.FunSuite

import java.io.File
import scala.io.Source

class ImageProcessingFacadeTest extends FunSuite {

  val userSettings = new UserSettings()
  val facade: ImageProcessingFacade = new DefaultImageProcessingFacade(userSettings)

  test("Facade should load JPEG image correctly") {
    val loader = new JPEGImageLoaderStrategy()
    val img = facade.loadImageFromPath(imagePathJPG, loader)
    assert(img.getWidth > 0)
    assert(img.getHeight > 0)
  }

  test("Facade should generate a random image correctly") {
    val width = 100
    val height = 100
    val loader = new RandomImageLoaderStrategy(width, height)
    val img = facade.loadImageFromPath("", loader)
    assert(img.getWidth == width)
    assert(img.getHeight == height)
  }

  test("Facade should save the image correctly to file") {
    val loader = new JPEGImageLoaderStrategy()
    val image = facade.loadImageFromPath(imagePathJPG, loader)
    val path = getTestFile

    val asciiString = facade.linearConversion(image)
    val asciiImage = AsciiStringToAsciiImageConverter.asciiContentToImage(asciiString)

    val saver = new FileImageSaver(path)
    facade.saveIntoFile(asciiImage, path)

    val source = Source.fromFile(path)
    try {
      assert(new File(path).exists())
      assert(source.getLines().nonEmpty)
    } finally {
      source.close()
    }
  }

  test("Facade should throw exception when loading image from nonexistent path") {
    val loader = new JPEGImageLoaderStrategy()
    val nonexistentPath = "nonexistent/path/image.jpg"
    assertThrows[Exception] {
      facade.loadImageFromPath(nonexistentPath, loader)
    }
  }

  test("Facade should throw exception when saving image to invalid path") {
    val loader = new JPEGImageLoaderStrategy()
    val image = facade.loadImageFromPath(imagePathJPG, loader)
    val invalidPath = "/invalid/path/image.jpg"
    val asciiString = facade.linearConversion(image)
    val asciiImage = AsciiStringToAsciiImageConverter.asciiContentToImage(asciiString)
    val saver = new FileImageSaver(invalidPath)
    assertThrows[Exception] {
      facade.saveIntoFile(asciiImage, invalidPath)
    }
  }

  test("Facade should throw exception when converting invalid image to ASCII") {
    val invalidImage: Null = null
    assertThrows[Exception] {
      facade.linearConversion(invalidImage)
    }
  }

}
