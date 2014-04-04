package org.elyast.fiddle.fp

import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext
import org.slf4j.LoggerFactory

class Activator2 extends BundleActivator {

  val LOG = LoggerFactory.getLogger("Simple")
  
  def start(context: BundleContext) {
    print("start")
    LOG.error("Starting up...")
  }
  
  def stop(context: BundleContext) {
    
  }  
}