package org.elyast.fiddle.fdi.api.internal

import org.elyast.fiddle.fdi.api.FDIContext
import org.elyast.fiddle.fdi.api.Environment

case class Context extends FDIContext[Unit] {
	
	def env: Environment = SysEnv()
}