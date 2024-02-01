package repositories.memory.pixels

import asciiArtApp.models.image.pixelGrid.pixels.Pixel
import repositories.Repository

trait PixelStorage[T <: Pixel] extends Repository[(Int, Int), T] {

}
