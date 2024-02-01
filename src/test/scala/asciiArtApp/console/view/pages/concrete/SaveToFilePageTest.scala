package asciiArtApp.console.view.pages.concrete

import asciiArtApp.console.views.pages.concrete.SaveToFilePage
import org.scalatest.FunSuite

class SaveToFilePageTest extends FunSuite {

  test("SaveToFilePage render returns the correct path in content") {
    val samplePath = "/sample/path/to/image.jpg"

    val page = SaveToFilePage(samplePath)
    val actualContent = page.render()

    assert(actualContent == s"Image saved to $samplePath.\n", "SaveToFilePage content did not match the provided path")
  }
}
