package asciiArtApp.console.view

import ImgRes.TestImgRes.{getTestFile, imagePathJPG}
import appConfig.UserSettings
import asciiArtApp.console.controllers.ConsoleController
import asciiArtApp.console.views.ConsoleView
import asciiArtApp.console.views.pages.concrete.{HelpPage, ImageConverterPage, ImageLoadedPage, OutputConsolePage, SaveToFilePage, TransformationAppliedPage}
import asciiArtApp.console.views.pages.generic.{ErrorResponse, SuccessResponse}
import asciiArtApp.facades.{DefaultImageProcessingFacade, ImageProcessingFacade}
import org.scalatest.FunSuite

class ConsoleViewTest extends FunSuite {

  val userSettings = new UserSettings()
  val facade: ImageProcessingFacade = new DefaultImageProcessingFacade(userSettings)
  val controller = new ConsoleController(facade,userSettings)

  private def captureOutput(command: String): String = {
    val outputCapture = new java.io.ByteArrayOutputStream()
    Console.withOut(outputCapture) {
      val view = new ConsoleView(controller)
      view.processCommand(command)
    }
    outputCapture.toString.trim
  }


  test("--output-file without path should display error") {
    val output = captureOutput("--output-file")
    assert(output == ErrorResponse("Path for --output-file is missing").render().trim())
  }

  test("--output-file with path should save image to file") {
    captureOutput(s"--image $imagePathJPG")
    val path = getTestFile
    val output = captureOutput(s"--output-file $path")
    assert(output == SaveToFilePage(path).render().trim())
  }
  test("--unknown-command should display error") {
    val unknownCommand = "--unknown-command"
    val output = captureOutput(unknownCommand)
    assert(output == ErrorResponse(s"Unknown command: $unknownCommand").render().trim())
  }

  test("--help should display help page") {
    val output = captureOutput("--help")
    assert(output.trim == HelpPage.render().trim())
  }
  test("--image-random should load a random image with success message") {
    val output = captureOutput("--image-random")
    assert(output == SuccessResponse.render().trim())
  }
  test("--image without path should display error") {
    val output = captureOutput("--image")
    assert(output == ErrorResponse("Path for --image is missing").render().trim())
  }

  test("--image with path should load image") {

    val output = captureOutput(s"--image $imagePathJPG")
    assert(output == ImageLoadedPage(imagePathJPG).render().trim())
  }

  test("--scale without value should display error") {
    val output = captureOutput("--scale")
    assert(output == ErrorResponse("Value for --scale is missing").render().trim())
  }
  test("--rotate without degrees should display error") {
    val output = captureOutput("--rotate")
    assert(output == ErrorResponse("Degrees for --rotate is missing").render().trim())
  }
  test("--rotate with degrees should apply rotation") {
    captureOutput(s"--image $imagePathJPG")
    val output = captureOutput("--rotate 90")
    assert(output == TransformationAppliedPage("rotation").render().trim())
  }
  test("--flip without type should display error") {
    val output = captureOutput("--flip")
    assert(output == ErrorResponse("Flip type for --flip is missing").render().trim())
  }

  test("--nonlinear-ascii should convert image to non-linear ASCII") {
    captureOutput(s"--image $imagePathJPG")
    val output = captureOutput("--nonlinear-ascii")
    assert(output == ImageConverterPage("Non linear").render().trim())
  }
  test("--custom-table without palette should display error") {
    val output = captureOutput("--custom-table")
    assert(output == ErrorResponse("Palette for --palette is missing").render().trim())
  }
  test("--custom-table with palette should set palette") {
    val palette = "@%#*+=-:. "
    val output = captureOutput(s"--custom-table $palette")
    assert(output == TransformationAppliedPage("palette set").render().trim())
  }

  test("--linear-ascii should convert image to linear ASCII") {
    captureOutput(s"--image $imagePathJPG")
    val output = captureOutput("--linear-ascii")
    assert(output == ImageConverterPage("Linear").render().trim())
  }
  test("--output-console should display image in console") {
    captureOutput(s"--image $imagePathJPG")
    val output = captureOutput("--output-console")
    assert(output.nonEmpty, "Output for --output-console should not be empty")
  }

  test("--image with nonexistent path should throw exception") {
    val nonexistentPath = "nonexistent/image/path.jpg"
    assertThrows[Exception] {
      captureOutput(s"--image $nonexistentPath")
    }
  }

  test("--rotate with invalid degrees should throw exception") {
    captureOutput(s"--image $imagePathJPG")
    assertThrows[Exception] {
      captureOutput("--rotate not_a_number")
    }
  }

  test("--scale with invalid value should throw exception") {
    captureOutput(s"--image $imagePathJPG")
    assertThrows[Exception] {
      captureOutput("--scale not_a_number")
    }
  }

  test("--flip with invalid type should throw exception") {
    captureOutput(s"--image $imagePathJPG")
    assertThrows[Exception] {
      captureOutput("--flip not_a_valid_type")
    }
  }

  test("--output-file to invalid path should throw exception") {
    captureOutput(s"--image $imagePathJPG")
    val invalidPath = "/invalid/path/image.jpg"
    assertThrows[Exception] {
      captureOutput(s"--output-file $invalidPath")
    }
  }



}
