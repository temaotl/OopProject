package input_output.Loader.FileImg.ConcreteStrategies

import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.RGBPixel
import converters.Images.BufferedImageToImage.bufferedImageToImage
import input_output.Loader.FileImg.FileImageLoaderStrategy

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**
 * An image loader strategy specifically for loading JPG and JPEG images from a file source.
 */
class JPEGImageLoaderStrategy extends FileImageLoaderStrategy {


  /**
   * Loads an Image[RGBPixel] from the given file source.
   * If the file does not exist or is not valid, an exception is thrown.
   *
   * @param source The path to the JPG file.
   * @return The loaded Image of type RGBPixel.
   * @throws IllegalArgumentException if the file does not exist or is not valid.
   */
  override def loadImage(source: String): Image[RGBPixel] = {
    val file = new File(source)
    if (!file.exists() || !file.isFile) {
      throw new IllegalArgumentException(s"Invalid file path: $source")
    }

    val extension = getFileExtension(file)
    if (extension != "jpg" && extension != "jpeg") {
      throw new UnsupportedOperationException(s"File format $extension is not supported by JPEGImageLoaderStrategy")
    }

    val bufferedImage: BufferedImage = ImageIO.read(file)
    bufferedImageToImage(bufferedImage)
  }

  private def getFileExtension(file: File): String = {
    val name = file.getName
    val lastIndex = name.lastIndexOf(".")
    if (lastIndex > 0) name.substring(lastIndex + 1).toLowerCase else ""
  }
}

