name := "maze-scala"
 
version := "1.0"
  
scalaVersion := "2.10.2"

scalacOptions ++= Seq("-deprecation", "-feature")

   
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.10.1"

libraryDependencies += "junit" % "junit" % "4.10" % "test"


