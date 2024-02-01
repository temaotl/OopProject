package asciiArtApp.console.controllers

import ImgRes.TestImgRes.{getTestFile, imagePathJPG}
import appConfig.UserSettings
import asciiArtApp.facades.{DefaultImageProcessingFacade, ImageProcessingFacade}
import org.scalatest.FunSuite
import org.scalatest.Matchers.{be, noException}

import java.io.{ByteArrayOutputStream, File, PrintStream}
import scala.io.Source

class ConsoleControllerTest extends FunSuite {


  val userSettings = new UserSettings()
  val facade: ImageProcessingFacade = new DefaultImageProcessingFacade(userSettings)
  val controller: Controller = new ConsoleController(facade, userSettings)
  
  test("Load Random Image") {
    noException should be thrownBy controller.loadRandomImage()
  }

  test("Convert Image To NonLinear Ascii") {
    controller.loadRandomImage()
    noException should be thrownBy controller.convertImageToNonLinearAscii()
  }

  test("Convert Image To Linear Ascii") {
    controller.loadRandomImage()
    noException should be thrownBy controller.convertImageToLinearAscii()
  }

  test("Set Linear Ascii Palette") {
    noException should be thrownBy controller.setLinearAsciiPalette("@%#*+=-:. ")
  }

  test("Load Image") {

    noException should be thrownBy controller.loadImage(imagePathJPG)
  }

  test("Load Nonexistent Image") {
    val nonExistentPath = "nonexistent/image/path.jpg"
    assertThrows[IllegalArgumentException] {
      controller.loadImage(nonExistentPath)
    }
  }

  test("Scale Image Without Loading") {
    assertThrows[Exception] {
      controller.scaleImage(0.5)
    }
  }

  test("Scale Image") {
    controller.loadRandomImage()
    noException should be thrownBy controller.scaleImage(0.25)
  }

  test("Rotate Image") {
    controller.loadRandomImage()
    noException should be thrownBy controller.rotateImage(90)
  }

  test("Flip Image") {
    controller.loadRandomImage()
    noException should be thrownBy controller.flipImage("x")
  }

  test("Flip Image With Invalid Parameter") {
    controller.loadRandomImage()
    assertThrows[Exception] {
      controller.flipImage("invalidParameter")
    }
  }
  test("Rotate Image Without Loading") {
    assertThrows[Exception] {
      controller.rotateImage(45)
    }
  }


  test("Save Image To File") {
    controller.loadImage(imagePathJPG)
    val savePath: String = getTestFile
    controller.saveImageToFile(savePath)
    val file = new File(savePath)
    assert(file.exists(), s"File was not created at path $savePath")
  }

}
