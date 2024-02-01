package asciiArtApp.console.controllers

import appConfig.UserSettings
import asciiArtApp.facades.ImageProcessingFacade
import asciiArtApp.models.image.{ConversionType, Image}
import asciiArtApp.models.image.pixelGrid.pixels.{AsciiPixel, RGBPixel}
import converters.Images.AsciiStringToAsciiImageConverter
import input_output.Loader.FileImg.ConcreteStrategies.{GIFImageLoaderStrategy, JPEGImageLoaderStrategy, PNGImageLoaderStrategy}
import input_output.Loader.ImageLoaderStrategy
import input_output.Loader.RandomImg.RandomImageLoaderStrategy
/**
 * ConsoleController manages the interaction between the user interface and the image processing functionalities.
 * It handles image loading, conversion, and output operations, interfacing with an ImageProcessingFacade and UserSettings.
 *
 * @param facade An instance of ImageProcessingFacade used for image processing tasks.
 * @param userSettings An instance of UserSettings for managing user preferences.
 */
class ConsoleController(facade: ImageProcessingFacade,userSettings: UserSettings) extends Controller {

  private var currentImage: Option[Image[RGBPixel]] = None
  private var current_converter: Option[ConversionType] =None


  override def loadRandomImage(): Unit = {
    val loader = new RandomImageLoaderStrategy(800, 600)
    currentImage = Some(facade.loadImageFromPath("", loader))
  }

  /**
   * Determines the appropriate image loader strategy based on the file extension.
   *
   * @param filePath The file path of the image to be loaded.
   * @return An ImageLoaderStrategy specific to the image file type.
   * @throws UnsupportedOperationException If the file extension is unsupported.
   */
  private def determineLoaderStrategy(filePath: String): ImageLoaderStrategy[RGBPixel] = {
    val extension = filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase
    extension match {
      case "gif" => new GIFImageLoaderStrategy
      case "jpg" | "jpeg" => new JPEGImageLoaderStrategy
      case "png" => new PNGImageLoaderStrategy
      case _ => throw new UnsupportedOperationException(s"Unsupported file extension: $extension")
    }
  }

  /**
   * Converts the currently loaded image using the specified conversion strategy.
   *
   * @param conversionStrategy The strategy to use for image conversion.
   * @throws Exception If no image is currently loaded.
   */
  private def convertImageWithStrategy(conversionStrategy: ConversionType): Unit = {
    currentImage match {
      case Some(_) => current_converter=Some(conversionStrategy)
      case None => throw new Exception("Image not loaded!")
    }
  }

  override def convertImageToNonLinearAscii(): Unit = {
    convertImageWithStrategy(ConversionType.NonLinear)
  }

  override def convertImageToLinearAscii(): Unit = {
    convertImageWithStrategy(ConversionType.Linear)
  }

  override def setLinearAsciiPalette(palette: String): Unit = {
    userSettings.setAsciiPalette(palette)
  }

  override def loadImage(path: String): Unit = {
    val loaderStrategy = determineLoaderStrategy(path)
    currentImage = Some(facade.loadImageFromPath(path, loaderStrategy))
  }

  override def scaleImage(scale: Double): Unit = {
    currentImage match {
      case Some(img) =>
        currentImage = Some(facade.scale(img,scale))
      case None => throw new Exception("Image not loaded!")
    }
  }

  override def rotateImage(degrees: Int): Unit = {
    currentImage match {
      case Some(img) =>
        currentImage = Some(facade.rotate(img,degrees))
      case None => throw new Exception("Image not loaded!")
    }
  }

  override def flipImage(flipType: String): Unit = {
    currentImage match {
      case Some(img) =>
        currentImage = Some(facade.flip(img, flipType))
      case None => throw new Exception("Image not loaded!")
    }
  }

  /**
   * Converts the currently loaded image into an ASCII string representation using the currently set conversion type.
   *
   * @param image The image to be converted to ASCII.
   * @return The ASCII string representation of the image.
   */
  private def convertImageToAsciiString(image: Image[RGBPixel]): String = {
    current_converter.getOrElse(ConversionType.Linear) match {
      case ConversionType.Linear => facade.linearConversion(image)
      case ConversionType.NonLinear => facade.nonlinearConversion(image)
    }
  }

  /**
   * Converts an ASCII string representation of an image back into an ASCII image.
   *
   * @param imageOption The optional image to be converted.
   * @return An Image object containing ASCII pixels.
   * @throws Exception If no image is currently loaded.
   */
  private def getAsciiConvertedImage(imageOption: Option[Image[RGBPixel]]): Image[AsciiPixel] = {
    imageOption match {
      case Some(img) =>
        val asciiString = convertImageToAsciiString(img)
        AsciiStringToAsciiImageConverter.asciiContentToImage(asciiString)
      case None => throw new Exception("Image not loaded!")
    }
  }

  override def outputImageToConsole(): Unit = {
    val asciiImage = getAsciiConvertedImage(currentImage)
    facade.outputConsole(asciiImage)
  }

  override def saveImageToFile(path: String): Unit = {
    currentImage match {
      case Some(img) =>
        val asciiImage = AsciiStringToAsciiImageConverter.asciiContentToImage(facade.linearConversion(img))
        facade.saveIntoFile(asciiImage, path)
      case None => throw new Exception("Image not loaded!")
    }
  }


  override def terminate(): Unit = {
    println("Terminating application.")
    System.exit(0)
  }
}


