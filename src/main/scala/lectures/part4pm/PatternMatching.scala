package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {
  //switch on steroids
  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
  case 1 =>"ONE"
  case 2 =>"TWO"
  case 3 =>"THREE"
  case _ =>"STH ELSE" // _ = WILDCARD
  }
  println(x)
  println(description)

  //Decomposing values
  //cases are matched in order
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi my name is $n and I am still very young" //guard
    case Person(n, a) => s"Hi my name is $n and I am $a years old"
    case _ => 42 //return type is unified type of all cases
  }
  println(greeting)

  //PM on seales hierarchies

  class Animal //sealed classes will not work
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = new Dog("Terra")
   animal match {
     case Dog(someBreed) => println(s"Matched a dog of $someBreed")
   }

  //can match everything - overkill
  val isEven = x % 2 == 0
  val isEvenMatched = x match {
    case n if n % 2 == 0 => true
    case _ => false
  }

  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Product(e1: Expr, e2: Expr) extends Expr

  def show(e: Expr) : String = e match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => show(e1) + " + " + show(e2)
    case Product(e1, e2) => {
      def maybeShowParantheses(e: Expr) = e match {
        case Product(_, _) => show(e)
        case Number(_) => show(e)
        case _ => "(" + show(e) + ")"
      }
      maybeShowParantheses(e1) + " * " + maybeShowParantheses(e2)
    }
  }

  println(show(Sum(Number(2), Number(3))))
  println(show(Sum(Sum(Number(2), Number(3)), Number(4))))
  println(show(Product(Sum(Number(2), Number(1)), Number(3))))
  println(show(Sum(Product(Number(2), Number(1)), Number(3))))


}
