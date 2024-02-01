package asciiArtApp.facades

import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.{AsciiPixel, RGBPixel}
import input_output.Loader.ImageLoaderStrategy

/**
 * This facade abstracts the complexity of image manipulation processes
 */
trait ImageProcessingFacade {
  /**
   * Loads an image from a given path.
   *
   * @param path the path to the image.
   * @return the loaded image.
   */
  def loadImageFromPath(path: String, loader: ImageLoaderStrategy[RGBPixel]): Image[RGBPixel]

  /**
   * Scales the image based on the previously set scaling factor.
   *
   * @param image the image to be scaled.
   * @return the scaled image.
   */
  def scale(image: Image[RGBPixel], inputScale: Double): Image[RGBPixel]

  /**
   * Rotates the image based on the previously set rotation angle.
   *
   * @param image the image to be rotated.
   * @return the rotated image.
   */
  def rotate(image: Image[RGBPixel], inputRotate: Int): Image[RGBPixel]

  /**
   * Flips the image based on the previously set flipping type.
   *
   * @param image the image to be flipped.
   * @return the flipped image.
   */
  def flip(image: Image[RGBPixel], flipType: String): Image[RGBPixel]

  /**
   * Converts the image to ASCII using linear conversion.
   *
   * @param image the image to be converted.
   * @return the ASCII representation of the image.
   */
  def linearConversion(image: Image[RGBPixel]): String

  /**
   * Converts the image to ASCII using nonlinear conversion.
   *
   * @param image the image to be converted.
   * @return the ASCII representation of the image.
   */
  def nonlinearConversion(image: Image[RGBPixel]): String

  /**
   * Saves the ASCII image into a file.
   *
   * @param image the ASCII image.
   */
  def saveIntoFile(image: Image[AsciiPixel], path: String): Unit

  /**
   * Outputs the ASCII image to the console.
   *
   * @param image the ASCII image.
   */
  def outputConsole(image: Image[AsciiPixel]): Unit
}

