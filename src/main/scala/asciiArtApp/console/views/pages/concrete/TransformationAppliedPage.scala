package asciiArtApp.console.views.pages.concrete

import asciiArtApp.console.views.pages.TextPage

case class TransformationAppliedPage(transformation: String) extends TextPage {
  override def render(): String = s"Applied $transformation to the image.\n"
}

