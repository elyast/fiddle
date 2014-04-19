package org.elyast.fiddle.http.service.internal

import com.typesafe.scalalogging.slf4j.Logging
import SysEnv._
import unfiltered.request._
import unfiltered.response._
import unfiltered.netty._
import java.net.URL

object Temperature extends async.Plan with ServerErrorResponse {
  def intent = {
    case req @ GET(Path("/request")) =>
      req.respond(ResponseString("x"))
  }
}

class HttpService extends Logging {

  val port: Int = SysEnv.env[Int]("PORT").getOrElse(8080)
  val chunkSize: Int = SysEnv.env[Int]("CHUNK_SIZE").getOrElse(1048576)
  val staticResources: Option[URL] = SysEnv.env("STATIC_RESOURCES")
  
  def activate() {
    logger.trace(s"HTTP service starting on port $port")
    val baseServer = Http(port).chunked(chunkSize)
    val httpServer = staticResources match {
      case Some(res) => baseServer.resources(res)
      case None => baseServer
    }
    
    httpServer.plan(Temperature).start()
    //.resources(staticResources)
    logger.trace(s"Leaving acticate method")
  }
}