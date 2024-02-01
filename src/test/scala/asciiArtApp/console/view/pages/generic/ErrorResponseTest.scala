package asciiArtApp.console.view.pages.generic

import asciiArtApp.console.views.pages.generic.ErrorResponse
import org.scalatest.FunSuite

class ErrorResponseTest extends FunSuite {

  test("ErrorResponse render returns the provided error message") {
    val sampleMessage = "Sample Error Message"

    val response = ErrorResponse(sampleMessage)
    val actualContent = response.render()

    assert(actualContent == s"$sampleMessage\n",
      "ErrorResponse content did not match the provided error message")
  }

  test("ErrorResponse render returns the default error message if none is provided") {
    val response = ErrorResponse()
    val actualContent = response.render()

    assert(actualContent == "Error\n",
      "ErrorResponse content did not match the default error message")
  }
}

