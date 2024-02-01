package asciiArtApp.console.views

import asciiArtApp.console.controllers.Controller
import asciiArtApp.console.views.pages.concrete.{HelpPage, ImageConverterPage, ImageLoadedPage, OutputConsolePage, SaveToFilePage, TransformationAppliedPage}
import asciiArtApp.console.views.pages.generic.{ErrorResponse, SuccessResponse}

/**
 * ConsoleView provides a command-line interface for interacting with the controller.
 *
 * @param controller An instance of Controller that provides methods to execute various commands.
 */
class ConsoleView(controller: Controller) {

  /**
   * Start the command-line interface, showing instructions and continuously listening for commands.
   */
  def run(): Unit = {
    println("Hello! If you need help with the app type '--help' for the help page.")
    val continueLoop = true
    while (continueLoop) {
      print("> ")
      val command = scala.io.StdIn.readLine()
      try {
        processCommand(command.trim)
      } catch {
        case e: Exception => println(ErrorResponse(e.getMessage).render())
      }
    }
  }

  /**
   * Processes the provided command, executing related controller actions.
   *
   * @param command A string command provided by the user.
   */
   def processCommand(command: String): Unit = {
    val tokens = command.split(" ").toList
    val commandIndices = tokens.zipWithIndex.filter { case (token, _) => token.startsWith("--") }.map(_._2)

    if (commandIndices.isEmpty) {
      println(ErrorResponse(s"Unknown command: $command").render())
      return
    }

    commandIndices.foreach { index =>
      tokens(index) match {
        case "--stop" | "--exit" =>
          controller.terminate()
          println(SuccessResponse.render())
          System.exit(0)

        case "--help" =>
          println(HelpPage.render())

        case "--image-random" =>
          controller.loadRandomImage()
          println(SuccessResponse.render())

        case "--image" =>
          if (index + 1 < tokens.length) {
            controller.loadImage(tokens(index + 1))
            println(ImageLoadedPage(tokens(index + 1)).render())
          } else {
            println(ErrorResponse("Path for --image is missing").render())
            return
          }

        case "--scale" =>
          if (index + 1 < tokens.length) {
            controller.scaleImage(tokens(index + 1).toDouble)
            println(TransformationAppliedPage("scale").render())
          } else {
            println(ErrorResponse("Value for --scale is missing").render())
            return
          }

        case "--rotate" =>
          if (index + 1 < tokens.length) {
            controller.rotateImage(tokens(index + 1).toInt)
            println(TransformationAppliedPage("rotation").render())
          } else {
            println(ErrorResponse("Degrees for --rotate is missing").render())
            return
          }

        case "--flip" =>
          if (index + 1 < tokens.length) {
            controller.flipImage(tokens(index + 1))
            println(TransformationAppliedPage("flip").render())
          } else {
            println(ErrorResponse("Flip type for --flip is missing").render())
            return
          }
        case "--nonlinear-ascii" =>
          controller.convertImageToNonLinearAscii()
          println(ImageConverterPage("Non linear").render())

        case "--custom-table" =>
          if (index + 1 < tokens.length) {
            controller.setLinearAsciiPalette(tokens(index + 1))
            println(TransformationAppliedPage("palette set").render())
          } else {
            println(ErrorResponse("Palette for --palette is missing").render())
            return
          }

        case "--linear-ascii" =>
          controller.convertImageToLinearAscii()
          println(ImageConverterPage("Linear").render())

        case "--output-console" =>
          controller.outputImageToConsole()
          println(OutputConsolePage("Image displayed in console.").render())

        case "--output-file" =>
          if (index + 1 < tokens.length) {
            controller.saveImageToFile(tokens(index + 1))
            println(SaveToFilePage(tokens(index + 1)).render())
          } else {
            println(ErrorResponse("Path for --output-file is missing").render())
            return
          }

        case _ =>
          println(ErrorResponse(s"Unknown command: ${tokens(index)}").render())
      }
    }
  }
}





