package org.elyast.fiddle.fdi.api.internal

import org.elyast.fiddle.fdi.api.Environment
import scalaz.Validation

case class SysEnv extends Environment {

  def get[E, A](key: String)(implicit f: String => Validation[E, A]): Option[A] = {
    if (System.getenv(key) != null) {
      val value = System.getenv(key)
      f(value).toOption
    } else if (System.getProperty(key) != null) {
      val value = System.getProperty(key)
      f(value).toOption
    } else {
      None
    }
  }

  def get[E, A](key: String, defaultValue: A)(implicit f: String => Validation[E, A]): A = {
    get(key)(f).getOrElse(defaultValue)
  }

}