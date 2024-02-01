package ImgRes

import java.util.UUID

object TestImgRes {
  var imagePathJPG: String = getClass.getResource("/images/testJpg.jpg").getPath
  var imagePathPng: String = getClass.getResource("/images/testPng.png").getPath
  var imagePathGif: String = getClass.getResource("/images/testGif.gif").getPath
  val testFolder = "src/test/scala/testFiles/"
  def getTestFile: String = testFolder + UUID.randomUUID().toString + ".txt"

}
