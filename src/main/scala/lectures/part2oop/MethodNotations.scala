package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {

      class Person(val name: String, favouriteMovie: String, val age: Int = 0) {
        def likes(movie:String): Boolean =  movie == favouriteMovie
        def hangOutWith(person: Person): String = s"${this.name} is hanging aout with ${person.name}"
        def +(person: Person): String = s"${person.name} is hanging out with ${this.name}"
        def +(nickname: String): Person = new Person(s"$name ($nickname)", favouriteMovie)
        def unary_+ : Person = new Person(name, favouriteMovie, age + 1)
        def unary_! : String = s"$name, what the fork?!" //needs space otherwise : as part of method name
        def isAlive: Boolean = true
        def apply(): String = s"Hi, my name is $name and I like $favouriteMovie" //needs ()
        def learns(subject: String) = s"$name learns $subject"
        def learnsScala = this learns "Scala"
        def apply(n: Int): String = s"$name watched $favouriteMovie $n times"
      }

      val mary = new Person("Mary", "Inception")
      println(mary.likes("Inception"))
      println(mary likes "Inceptiion") //infix notation = operator notation = Object  Method Parameter (1 arg) = syntactic sugars

      //"operators" in  Scala
      val tom = new Person("Tom", "Fight Clug")
      println(mary hangOutWith tom) //acts out as an operator
      println(mary + tom) // fun
      println(tom.+(mary))

      println(1 + 2)
      println(1.+(2))
      //All operators are METHODS
      //Akka actors have ! ?

      //prefix notation - unary operators

      val x = -1
      val y = 1.unary_- //equivalent; unary_ prefix works with - + ~ !

      println(!mary)
      println(mary.unary_!)

      //postfix notation - can use methods with no parameters
      println(mary.isAlive)
      println(mary isAlive) //sugar syntax but not much used due to ./space char

      //apply method
      println(mary.apply())
      println(mary()) //calling mary object as a function - looks for apply method
      //OOP and FP bridge

      println((mary + "the Rockstar")())
      println((mary + "the Rockstar").apply())

      println((+mary).age)

      println(mary learnsScala)

      println(mary(10))
      println(mary())
}

