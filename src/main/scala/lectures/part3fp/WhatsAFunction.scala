package lectures.part3fp

object WhatsAFunction extends App {
  //use functions as first class elements, as clean values
  //problem: OOP - java original design, you can simulate by instantializing classes anonymously or not anonymously (function simulation) - laso using generalization with generic types

  class Action {
    def execute(element: Int): String = ???
  }

  trait ActionFunction[A,B] {
    def apply(element: A): B
  }

  val doubler = new ActionFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2)) //doubler an instance like a class can be called like a function using apply method
  // function types = Function1[A,B]

  val stringToIntConverter = new Function1[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }

  //scala supports function up to 22 parameters
  println(stringToIntConverter("3") + 4)

  val adder: Function2[Int, Int, Int] = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  val adderSyntacticSugar: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  //Function types Function2[A,B,R] === (A,B) => R - its syntactic sugar so:
  //ALL SCALA FUNCTIONS ARE OBJECTS  - all instances derived from Function1-22

  //exercise - function concatenating 2 strings
  def concatenation: ((String, String) => String) = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }
  println(concatenation("hello, ",  "scala"))

  //curried function - can be called with multiple parameter list - it returns other fanctions
  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) //superAdder(3) returns function1 which then is called with parameter 4

}
