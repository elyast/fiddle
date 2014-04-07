package org.elyast.fiddle.fp

class Handler {

  def start() {
    val port = System.getenv("PORT")
    println(s"hi ${port}")
    unfiltered.netty.Http(port.toInt).handler(TestService).run { s =>
        println(s)
      }
  }
}