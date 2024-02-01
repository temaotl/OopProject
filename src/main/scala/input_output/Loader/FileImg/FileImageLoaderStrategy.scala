package input_output.Loader.FileImg

import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.RGBPixel
import input_output.Loader.ImageLoaderStrategy



abstract class FileImageLoaderStrategy extends ImageLoaderStrategy[RGBPixel] {
  override def loadImage(source: String): Image[RGBPixel]
}

