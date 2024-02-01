package input_output


import ImgRes.TestImgRes.{getTestFile, imagePathJPG, imagePathPng}
import converters.ASCII.LinearAsciiConverter
import converters.Images.AsciiStringToAsciiImageConverter
import input_output.Loader.FileImg.ConcreteStrategies.{JPEGImageLoaderStrategy, PNGImageLoaderStrategy}
import input_output.Saver.{ConsoleImageSaver, FileImageSaver}
import org.scalatest.FunSuite
import java.nio.file.{Files, Paths}

class SaveTest extends FunSuite{

  test("Load, convert to ASCII, and save a JPG image using ImageSaver trait") {

    val loader = new JPEGImageLoaderStrategy
    val image = loader.loadImage(imagePathJPG)

    val converter = new LinearAsciiConverter()
    val asciiContent = converter.convert(image)

    val asciiImage = AsciiStringToAsciiImageConverter.asciiContentToImage(asciiContent)

    val consSaver = new ConsoleImageSaver()
    consSaver.saveImage(asciiImage)

    val savePath: String = getTestFile

    val saver = new FileImageSaver(savePath)
    saver.saveImage(asciiImage)

    assert(Files.exists(Paths.get(savePath)), s"File $savePath does not exist")

    assert(Files.size(Paths.get(savePath)) > 0, s"File $savePath is empty")

  }
  test("Load, convert to ASCII, and save a PNG image using ImageSaver trait") {

    val loader = new PNGImageLoaderStrategy
    val image = loader.loadImage(imagePathPng)

    val converter = new LinearAsciiConverter()
    val asciiContent = converter.convert(image)

    val asciiImage = AsciiStringToAsciiImageConverter.asciiContentToImage(asciiContent)

    val consSaver = new ConsoleImageSaver()
    consSaver.saveImage(asciiImage)

    val savePath: String = getTestFile

    val saver = new FileImageSaver(savePath)
    saver.saveImage(asciiImage)

    assert(Files.exists(Paths.get(savePath)), s"File $savePath does not exist")

    assert(Files.size(Paths.get(savePath)) > 0, s"File $savePath is empty")

  }

  test("FileImageSaver should throw exception for invalid save path") {
    val loader = new JPEGImageLoaderStrategy
    val image = loader.loadImage(imagePathJPG)
    val converter = new LinearAsciiConverter()
    val asciiContent = converter.convert(image)
    val asciiImage = AsciiStringToAsciiImageConverter.asciiContentToImage(asciiContent)

    val invalidPath = "/invalid/path/image.jpg"
    val saver = new FileImageSaver(invalidPath)
    assertThrows[Exception] {
      saver.saveImage(asciiImage)
    }
  }

  test("FileImageSaver should throw exception for saving null image") {
    val savePath: String = getTestFile
    val saver = new FileImageSaver(savePath)
    assertThrows[Exception] {
      saver.saveImage(null)
    }
  }

  test("ConsoleImageSaver should throw exception for null image") {
    val consSaver = new ConsoleImageSaver()
    assertThrows[Exception] {
      consSaver.saveImage(null)
    }
  }
  
}
