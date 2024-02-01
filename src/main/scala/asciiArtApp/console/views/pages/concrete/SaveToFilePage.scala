package asciiArtApp.console.views.pages.concrete

import asciiArtApp.console.views.pages.TextPage

case class SaveToFilePage(path: String) extends TextPage {
  override def render(): String = s"Image saved to $path.\n"
}

