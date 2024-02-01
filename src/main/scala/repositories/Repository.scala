package repositories

/**
 * A generic trait defining the basic operations for a repository.
 * A repository is essentially an in-memory database that offers basic CRUD operations.
 *
 * @tparam ID The type representing the unique identifier for entities in the repository.
 * @tparam T  The type of entities stored in the repository.
 */
trait Repository[ID, T] {
  def create(id: ID, entity: T): Unit
  def read(id: ID): Option[T]
  def modify(id: ID, entity: T): Unit
  def delete(id: ID): Unit
  def readAll(): List[T]
}
