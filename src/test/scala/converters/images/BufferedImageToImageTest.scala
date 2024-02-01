package converters.images

import asciiArtApp.models.image.pixelGrid.pixels.RGBPixel
import converters.Images.BufferedImageToImage
import org.scalatest.FunSuite

import java.awt.Color
import java.awt.image.BufferedImage

class BufferedImageToImageTest extends FunSuite {

  test("Should correctly convert BufferedImage to Image") {
    val bufferedImage = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB)
    val image = BufferedImageToImage.bufferedImageToImage(bufferedImage)
    assert(image.getWidth == 2)
    assert(image.getHeight == 2)
  }

  test("Should correctly map BufferedImage colors to RGBPixels") {
    val bufferedImage = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB)
    bufferedImage.setRGB(0, 0, Color.RED.getRGB)
    bufferedImage.setRGB(1, 0, Color.GREEN.getRGB)
    bufferedImage.setRGB(0, 1, Color.BLUE.getRGB)
    bufferedImage.setRGB(1, 1, Color.WHITE.getRGB)

    val image = BufferedImageToImage.bufferedImageToImage(bufferedImage)

    assert(image.getPixel(0, 0).contains(RGBPixel(255, 0, 0)))
    assert(image.getPixel(1, 0).contains(RGBPixel(0, 255, 0)))
    assert(image.getPixel(0, 1).contains(RGBPixel(0, 0, 255)))
    assert(image.getPixel(1, 1).contains(RGBPixel(255, 255, 255)))
  }
}
