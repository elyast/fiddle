package org.elyast.fiddle.fdi.api

trait FDIContext[A] extends ModuleContext[A] {

  def env: Environment
}