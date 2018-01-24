import java.net.URLClassLoader

import com.typesafe.config.{Config, ConfigFactory}


val myKey: InputKey[String] = inputKey[String]("desc")


scalaVersion := "2.12.4"

myKey := {

  val urls = ( fullClasspath in Compile).value.map(f => f.data.toURI.toURL).toArray

  val configLoader: ClassLoader = new URLClassLoader(urls, getClass.getClassLoader)


  val info =
    s"""
       |###############################
       |sbtVersion = ${sbtVersion.value}
       |configLoader = $configLoader
       |urls =
       |${urls.mkString("\n")}
       |###############################
  """.stripMargin

  streams.value.log.info(info)

  val config: Config = ConfigFactory.load(configLoader)
  val m = config.getString("App.module1.x")

  val s2: String =
    s"""
       |@@@@@@@@@@@@@@@@@@@@@@@
       |$m
       |@@@@@@@@@@@@@@@@@@@@@@@
      """.stripMargin

  streams.value.log.info(s2)
  m
}



