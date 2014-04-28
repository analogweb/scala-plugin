package org.analogweb.scala

import org.analogweb.core.httpserver._
import java.net.URI
import org.analogweb.core._

object Run  {
  def main(args:Array[String]):Unit = {
    HttpServers.create(URI.create("http://localhost:8080"),new AnalogHandler(new WebApplication())).start()
  }
}

