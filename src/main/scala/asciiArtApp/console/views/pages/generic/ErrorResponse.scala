package asciiArtApp.console.views.pages.generic

import asciiArtApp.console.views.pages.TextPage

case class ErrorResponse(message: String = "Error") extends TextPage
{
  override def render(): String = message + "\n"
}

