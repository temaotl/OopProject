package asciiArtApp.models.image.pixelGrid.pixels

case class AsciiPixel(char: Char) extends Pixel {
  override def value: Int = char.toInt
}

