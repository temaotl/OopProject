package asciiArtApp.console.view.pages.concrete

import asciiArtApp.console.views.pages.concrete.ImageConverterPage
import org.scalatest.FunSuite

class ImageConverterPageTest extends FunSuite {

  test("ImageConverterPage render produces the expected content") {
    var none_linear =  ImageConverterPage("Non linear").render()
    assert(none_linear=="Image conversation by Non linear.\n")
  }
}
