package org.elyast.fiddle.examples.web

import unfiltered.request._
import unfiltered.response._

import unfiltered.netty._

object TestService extends async.Plan
  with ServerErrorResponse {

  def intent = {
    case req @ GET(Path("/time")) => 
      req.respond(ResponseString("Test"))
  }
}