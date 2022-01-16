package lectures.part2oop

import playground.{Cinderella => Princess, PrinceCharming} //cam use import playground._

import java.util.Date
import java.sql.{Date => sqlDate}

object PackagingAndImports extends App {
  val writer = new Writer("CJ", "Rock", 2018)

  val princess = new Princess //usefeul naming classes if needs to import more than one class with the same name - aliasing

  // package Object - access universal methods outside classes
  // package Objects - one in package package.scala
  sayHello
  println(SPEED_OF_LIGHT)

  val prince = new PrinceCharming

  val date = new Date()
  val sqlDate = new sqlDate(2012,5,2)

  //default imports
  //java.lang - String, Object, Exception
  //scala - Int, Nothing, Function,
  //scala.Predef - println, ???
}
