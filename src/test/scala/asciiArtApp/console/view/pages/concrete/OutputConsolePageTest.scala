package asciiArtApp.console.view.pages.concrete

import asciiArtApp.console.views.pages.concrete.OutputConsolePage
import org.scalatest.FunSuite

class OutputConsolePageTest extends FunSuite {

  test("OutputConsolePage render returns the correct imageData content") {
    val sampleImageData = "SampleImageData"

    val page = OutputConsolePage(sampleImageData)
    val actualImageData = page.render()

    assert(actualImageData == sampleImageData, "OutputConsolePage content did not match the provided imageData")
  }
}
