package org.elyast.fiddle.http.service.internal

import scalaz.Validation
import scalaz.Validation.fromTryCatch
import scalaz.syntax.validation._
import scalaz.std.string._
import scalaz.syntax.bifunctor._
import java.net.URL


object SysEnv {

  implicit def parseUrlWithStringError(s: String): Validation[String, URL] = fromTryCatch(new URL(s)).bimap(_.getMessage, identity)
  
  implicit def parseIntWithStringError(s: String): Validation[String, Int] = parseInt(s).bimap(_.getMessage, identity)
  
  def env[A](key: String)(implicit f: String => Validation[String, A]): Option[A] = if (System.getenv(key) != null) {
    val value = System.getenv(key)
    f(value).toOption
  } else {
    None
  }
}