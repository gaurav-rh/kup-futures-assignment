import org.scalatest.flatspec.AnyFlatSpec

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class FutureAssignmentTest extends AnyFlatSpec {

  val futureDirectory: FutureAssignment = new FutureAssignment
  val path: String = "src"
  val result: List[String] = List("src", "src/test", "src/test/scala", "src/test/scala/FutureAssignmentTest.scala", "src/main", "src/main/scala", "src/main/scala/FutureAssignment.scala")
  val callFunction: List[String] = Await.result(futureDirectory.listDirectory(path), 2 seconds)

  "futureDirectory" should "be valid in multithreaded " in {
    assert(callFunction == result)
  }
}
