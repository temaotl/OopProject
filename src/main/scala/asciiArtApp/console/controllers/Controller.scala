package asciiArtApp.console.controllers

trait Controller {


  /**
   * Loads a random image using a predefined strategy. The image is stored as the current image.
   */
  def loadRandomImage(): Unit

  /**
   * Loads an image from the specified path.
   *
   * @param path The path to the image file.
   */
  def loadImage(path: String): Unit

  /**
   * Scales the currently loaded image by the specified factor.
   *
   * @param scale The scaling factor. E.g., 2.0 means the image will be scaled to 200% of its original size.
   */
  def scaleImage(scale: Double): Unit

  /**
   * Rotates the currently loaded image by the specified degrees.
   *
   * @param degrees The number of degrees to rotate the image.
   */
  def rotateImage(degrees: Int): Unit

  /**
   * Flips the currently loaded image based on the given flip type.
   *
   * @param flipType The type of flip operation. Expected values might include "x", "y", "xy" etc.
   */
  def flipImage(flipType: String): Unit

  /**
   * Outputs the currently loaded image to the console in  ASCII.
   */
  def outputImageToConsole(): Unit

  /**
   * convert into ASCII and saves the currently loaded image to the specified file path.
   *
   * @param path The destination path for the saved image file.
   */
  def saveImageToFile(path: String): Unit

  /**
   * terminate program
   */
  def terminate(): Unit

  /**
   * Change strategy of conversation to Non-Linear
   */
  def convertImageToNonLinearAscii(): Unit

  /**
   * Change strategy of conversation to Linear
   */
  def convertImageToLinearAscii(): Unit

  /**
   * Sets the ASCII palette for linear conversions.
   *
   * @param palette A string representing the ASCII characters to be used in the order of intensity.
   */
  def setLinearAsciiPalette(palette: String): Unit
}


