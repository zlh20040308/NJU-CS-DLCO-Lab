// import Mill dependency
import mill._
import mill.scalalib._
import mill.scalalib.scalafmt.ScalafmtModule
import mill.bsp._

val sourceDir = sys.env.getOrElse("SRC_DIR", "lab1/src")          // 从环境变量读取源代码路径

object Lab extends ScalaModule with ScalafmtModule {
  override def scalaVersion = "2.13.12"

  val chiselVersion = "6.5.0"

  override def ivyDeps = Agg(
    ivy"org.chipsalliance::chisel:$chiselVersion"
  )

  override def scalacPluginIvyDeps = Agg(
    ivy"org.chipsalliance:::chisel-plugin:$chiselVersion"
  )

  override def sources = T.sources {
    os.pwd / os.RelPath(sourceDir) // 动态设置源代码路径
  }

  def repositoriesTask = T.task {
    Seq(
      coursier.MavenRepository("http://mirrors.cloud.tencent.com/nexus/repository/maven-public"),
      coursier.MavenRepository(
        "https://repo.scala-sbt.org/scalasbt/maven-releases"
      )
    ) ++ super.repositoriesTask()
  }
}
