package asciiArtApp.models.image

sealed trait ConversionType
object ConversionType {
  case object Linear extends ConversionType
  case object NonLinear extends ConversionType
}
