package org.elyast.fiddle.examples.web

class Handler {

  def start() {
    val port = System.getenv("PORT")
    println(s"hi ${port}")
    val netty = unfiltered.netty.Http(port.toInt)
    netty.handler(TestService)
    unfiltered.netty.Http(port.toInt).handler(TestService).run { s =>
        println(s)
      }
  }
}