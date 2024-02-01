package asciiArtApp.console.views.pages.concrete

import asciiArtApp.console.views.pages.TextPage

case class ImageConverterPage(conversation: String) extends TextPage {
  override def render(): String = s"Image conversation by $conversation.\n"
}

