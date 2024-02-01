package appConfig

/**
 * Singleton object representing the default ASCII mapping for image-to-ASCII conversion.
 *
 * The object provides a predefined mapping from ASCII characters to specific grayscale ranges.
 * Each character represents a range of grayscale values that it can map to.
 */
object DefaultAsciiMapping {
  private val mapping: Map[Char, (Double, Double)] = Map(
    ' ' -> (0.0, 50.0),
    '.' -> (51.0, 70.0),
    '-' -> (71.0, 90.0),
    ':' -> (91.0, 110.0),
    '+' -> (111.0, 130.0),
    '*' -> (131.0, 150.0),
    '=' -> (151.0, 170.0),
    '#' -> (171.0, 190.0),
    '%' -> (191.0, 210.0),
    '@' -> (211.0, 230.0),
    'A' -> (231.0, 255.0)
  )

  /**
   * Provides access to the predefined ASCII mapping.
   *
   * @return A map where each key is an ASCII character and its value is a grayscale range.
   */
  def getMapping: Map[Char, (Double, Double)] = mapping
}

