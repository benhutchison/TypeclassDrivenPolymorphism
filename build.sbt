name := "typeclass-driven-polymorphism"

version := "1.0"

scalaVersion := "2.10.1"

resolvers ++= Seq(
  "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/",
  "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
)

libraryDependencies ++= Seq(
  "com.chuusai" % "shapeless_2.10" % "1.2.4"
)

EclipseKeys.withSource := true