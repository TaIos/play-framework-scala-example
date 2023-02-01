name := """play-framework-scala-example"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.10"

val slickVersion = "3.4.1"

libraryDependencies ++= Seq(
  guice,
  evolutions,
  jdbc,
  "com.typesafe.slick" %% "slick" % slickVersion,
  "com.typesafe.slick" %% "slick-codegen" % slickVersion,
  "com.typesafe.play" %% "play-slick" % "5.1.0",
  "org.postgresql" % "postgresql" % "42.5.1",
  "ch.qos.logback" % "logback-classic" % "1.4.5",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
)

// https://github.com/slick/slick-codegen-example/blob/main/build.sbt
//(Compile / sourceGenerators) += slick.taskValue // Automatic code generation on build

lazy val slick = taskKey[Seq[File]]("Generate Tables.scala")
slick := {
  val conf = com.typesafe.config.ConfigFactory
    .parseFile(new File("conf/application.conf"))
    .resolve()

  val url = conf.getString("slick.dbs.default.db.url")
  val username = conf.getString("slick.dbs.default.db.user")
  val password = conf.getString("slick.dbs.default.db.password")

  val outputDir = new File(conf.getString("slick.codeGenerator.outputDir"))
  val jdbcDriver = conf.getString("slick.codeGenerator.jdbcDriver")
  val slickDriver = conf.getString("slick.codeGenerator.slickDriver")

  val pkg = "models"

  val cp = (Compile / dependencyClasspath).value
  val s = streams.value

  runner.value
    .run(
      "slick.codegen.SourceCodeGenerator",
      cp.files,
      Array(
        slickDriver,
        jdbcDriver,
        url,
        outputDir.getPath,
        pkg,
        username,
        password
      ),
      s.log
    )
    .failed foreach (sys error _.getMessage)

  val file = outputDir / pkg / "Tables.scala"

  Seq(file)
}
