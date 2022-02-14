import java.io.{File, FileNotFoundException}
import java.nio.file.{FileSystems, Path}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class FutureAssignment {
  def listDirectory(path: String): Future[List[String]] = {
    def readDirectory(path: String): List[Path] = {

      val directory: Path = FileSystems.getDefault.getPath(path)

      val result: List[Path] = {
        if (directory.toFile.exists() && directory.toFile.isDirectory) {
          directory +: new File(path).listFiles().toList.flatMap(each => readDirectory(s"$path/${each.getName}"))
        }
        else if (directory.toFile.isFile && directory.toFile.exists()) {
          List(directory)
        }
        else throw new FileNotFoundException()
      }
      result
    }

    Future((readDirectory("src")).map(path => path.toString))
  }
}

