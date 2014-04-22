package org.elyast.fiddle.http.service.internal

import com.typesafe.scalalogging.slf4j.Logging
import unfiltered.request._
import unfiltered.response._
import unfiltered.netty._
import java.net.URL
import org.elyast.fiddle.fdi.api.Environment
import scalaz.syntax.validation._
import scalaz._
import scalaz.std._

object Temperature extends async.Plan with ServerErrorResponse {
  def intent = {
    case req @ GET(Path("/request")) =>
      req.respond(ResponseString("x"))
  }
}

object Defaults {
  val CHUNK_SIZE = 1048576
  val PORT = 8080
}

case class HttpService extends Logging {
  import Defaults._
  implicit def parseInt: String => Validation[NumberFormatException, Int] = string.parseInt
  implicit def parseURL(a: String) = Validation.fromTryCatch(new URL(a))

  def extractSettings(env: Environment) = {
    (env.get[NumberFormatException, Int]("PORT", PORT),
      env.get[NumberFormatException, Int]("CHUNK_SIZE", CHUNK_SIZE),
      env.get[Throwable, URL]("RESOURCES"))
  }
  
  def start(): Environment => Unit = {
    env =>
      val (port, chunkSize, resources) = extractSettings(env)
      val baseServer = Http(port).chunked(chunkSize)
      val httpServer = resources match {
        case Some(res) => baseServer.resources(res)
        case None => baseServer
      }

      httpServer.plan(Temperature).start()
  }

  def stop() = {

  }
}