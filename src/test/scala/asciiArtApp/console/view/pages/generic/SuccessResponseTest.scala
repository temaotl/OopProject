package asciiArtApp.console.view.pages.generic

import asciiArtApp.console.views.pages.generic.SuccessResponse
import org.scalatest.FunSuite

class SuccessResponseTest extends FunSuite {

  test("SuccessResponse render returns 'Success' message") {
    val actualContent = SuccessResponse.render()

    assert(actualContent == "Success\n",
      "SuccessResponse content did not match the expected success message")
  }
}

