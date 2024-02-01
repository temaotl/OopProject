package asciiArtApp.console.views.pages.concrete

import asciiArtApp.console.views.pages.TextPage

case class OutputConsolePage(imageData: String) extends TextPage {
  override def render(): String = imageData
}

