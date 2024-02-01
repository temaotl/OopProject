package appConfig

/**
 * The UserSettings class is responsible for managing user-specific settings in the application.
 * It currently handles settings related to ASCII art generation, such as the ASCII palette.
 */
class UserSettings {
  private var asciiPalette: Option[String] = None
  /**
   * Sets the ASCII palette to be used for ASCII art generation.
   * Validates the input to ensure that the palette string is not empty and contains no duplicate characters.
   *
   * @param palette A string representing the ASCII palette.
   * @throws IllegalArgumentException If the palette is empty or contains duplicate characters.
   */
  def setAsciiPalette(palette: String): Unit = {
    require(palette.nonEmpty, "Palette should not be empty")
    require(palette.toSeq.distinct.unwrap.length == palette.length, "Palette should not have duplicate characters")
    asciiPalette = Some(palette)
  }

  /**
   * Retrieves the currently set ASCII palette.
   *
   * @return An Option containing the ASCII palette string if set, or None if not set.
   */
  def getAsciiPalette: Option[String] = asciiPalette
}

