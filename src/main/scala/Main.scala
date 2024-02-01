package Main

import appConfig.UserSettings
import asciiArtApp.console.controllers.{ConsoleController, Controller}
import asciiArtApp.console.views.ConsoleView
import asciiArtApp.facades.{DefaultImageProcessingFacade, ImageProcessingFacade}

object Main extends App {

  val userSettings = new UserSettings()
  val facade: ImageProcessingFacade = new DefaultImageProcessingFacade(userSettings)
  val controller: Controller = new ConsoleController(facade,userSettings)
  private val view = new ConsoleView(controller)
  view.run()
}