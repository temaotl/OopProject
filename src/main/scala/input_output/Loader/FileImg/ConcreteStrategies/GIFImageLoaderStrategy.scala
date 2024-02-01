package input_output.Loader.FileImg.ConcreteStrategies

import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.RGBPixel
import converters.Images.BufferedImageToImage.bufferedImageToImage
import input_output.Loader.FileImg.FileImageLoaderStrategy

import javax.imageio.ImageIO
import java.io.File

/**
 * An image loader strategy specifically for loading GIF images from a file source.
 * only first frame
 */
class GIFImageLoaderStrategy extends FileImageLoaderStrategy {

  /**
   * Loads an Image[RGBPixel] from the given file source.
   * If the file does not exist or is not valid, an exception is thrown.
   *
   * @param source The path to the GIF file.
   * @return The loaded Image of type RGBPixel.
   * @throws IllegalArgumentException if the file does not exist or is not valid.
   */
  override def loadImage(source: String): Image[RGBPixel] = {
    val file = new File(source)
    if (!file.exists() || !file.isFile) {
      throw new IllegalArgumentException(s"File $source does not exist or is not a valid file.")
    }
    val bufferedImage = ImageIO.read(file)
    bufferedImageToImage(bufferedImage)
  }
}

