package repositories.memory.pixels

import asciiArtApp.models.image.pixelGrid.pixels.Pixel

/**
 * InMemoryPixelStorage is an implementation of PixelStorage that stores pixels in-memory.
 * It uses a map to store pixel entities, allowing for efficient creation, reading, modification, and deletion of pixels.
 * This class is generic and can work with any type that extends the Pixel trait.
 *
 * @tparam T The type of the pixel, which must extend the Pixel trait.
 */

class InMemoryPixelStorage[T <: Pixel] extends PixelStorage[T] {
  private var storage: Map[(Int, Int), T] = Map.empty

  override def create(id: (Int, Int), entity: T): Unit = {
    if (!storage.contains(id)) {
      storage += (id -> entity)
    } else {
      throw new IllegalArgumentException(s"Pixel at position $id already exists.")
    }
  }

  override def read(id: (Int, Int)): Option[T] = storage.get(id)

  override def modify(id: (Int, Int), entity: T): Unit = {
    storage += (id -> entity)
  }

  override def delete(id: (Int, Int)): Unit = {
    storage -= id
  }

  override def readAll(): List[T] = storage.values.toList
}
