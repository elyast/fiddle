package org.elyast.fiddle.fdi.api

import scalaz.Validation

trait Environment {

  def get[E, A](key: String)(implicit f: String => Validation[E, A]): Option[A]
  
  def get[E, A](key: String, defaultValue: A)(implicit f: String => Validation[E, A]): A
}