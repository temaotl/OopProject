package asciiArtApp.console.views.pages.concrete

import asciiArtApp.console.views.pages.TextPage

case class ImageLoadedPage(path: String) extends TextPage {
  override def render(): String = s"Image loaded from $path.\n"
}
