package asciiArtApp.facades

import appConfig.UserSettings
import asciiArtApp.models.image.Image
import asciiArtApp.models.image.pixelGrid.pixels.{AsciiPixel, RGBPixel}
import converters.ASCII.{LinearAsciiConverter, NonLinearAsciiConverter}
import filter.FilterFlip.{FlipFilterController, FlipXFilter, FlipYFilter}
import filter.{RotateFilter, ScaleFilter}
import input_output.Loader.ImageLoaderStrategy
import input_output.Saver.{ConsoleImageSaver, FileImageSaver}

/**
 * DefaultImageProcessingFacade provides a concrete implementation of the ImageProcessingFacade.
 * It handles various image processing operations such as loading, scaling, rotating, flipping, and converting images.
 *
 * @param userSettings An instance of UserSettings to access user-specific settings like ASCII palettes.
 */
class DefaultImageProcessingFacade(userSettings: UserSettings) extends ImageProcessingFacade {


  override def loadImageFromPath(path: String, loader: ImageLoaderStrategy[RGBPixel]): Image[RGBPixel] = {
    loader.loadImage(path)
  }

  override def scale(image: Image[RGBPixel], inputScale: Double): Image[RGBPixel] = {
    ScaleFilter.apply(image, inputScale)
  }

  override def rotate(image: Image[RGBPixel], inputRotate: Int): Image[RGBPixel] = {
    RotateFilter.apply(image, inputRotate)
  }

  override def flip(image: Image[RGBPixel], flipType: String): Image[RGBPixel] = {
    flipType match {
      case "x" => FlipXFilter.apply(image, "x")
      case "y" => FlipYFilter.apply(image, "y")
      case "xy" => FlipFilterController.applyBoth(image)
      case _ => throw new IllegalArgumentException(s"Unsupported flip type: $flipType")
    }
  }

  override def linearConversion(image: Image[RGBPixel]): String = {
    val palette = userSettings.getAsciiPalette
    val converter = palette.map(new LinearAsciiConverter(_)).getOrElse(new LinearAsciiConverter())
    converter.convert(image)
  }

  override def nonlinearConversion(image: Image[RGBPixel]): String = {
    val converter = new NonLinearAsciiConverter()
    converter.convert(image)
  }

  override def saveIntoFile(image: Image[AsciiPixel], path: String): Unit = {
    val fileSaver = new FileImageSaver(path)
    fileSaver.saveImage(image)
  }

  override def outputConsole(image: Image[AsciiPixel]): Unit = {
    val consoleSaver = new ConsoleImageSaver()
    consoleSaver.saveImage(image)
  }
}


