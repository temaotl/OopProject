package asciiArtApp.console.view.pages.concrete

import asciiArtApp.console.views.pages.concrete.TransformationAppliedPage
import org.scalatest.FunSuite

class TransformationAppliedPageTest extends FunSuite {

  test("TransformationAppliedPage render returns the correct transformation in content") {
    val sampleTransformation = "rotation"

    val page = TransformationAppliedPage(sampleTransformation)
    val actualContent = page.render()

    assert(actualContent == s"Applied $sampleTransformation to the image.\n",
      "TransformationAppliedPage content did not match the provided transformation")
  }
}

