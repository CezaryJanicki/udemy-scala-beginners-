package lectures.part3fp

object AnonymousFunctions extends App {

  //still OOP
  val doubler = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 * 2
  }

  //scala way - instantiates Function1 - anonymous class - LAMBDA
  //val doublerFunction: Int => Int = (v1: Int) => v1 * 2
  //shorhand notation - Compiler knows (Int => Int) as return
  val doublerFunction: Int => Int = v1 => v1 * 2

  //multiple parameters in a lambda (in paranthesis)
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  //no params lambda
  val justDoSth: () => Int = () => 3

  //prints function itself
  println(justDoSth)
  //prints value - rather than as using functions, needs () - call
  println(justDoSth())

  //curly braces with lambda - in practice
  val stringToInt = { (str: String) =>
    str.toInt
  }

  //MOAR syntactisc sugar
  // var niceIncrementer: Int => Int = (x: Int) => x + 1
  val niceIncrementer: Int => Int = _ + 1 //equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int =  _ + _ //equivalent to (a.b) => a + b

  //curried function with lambda
  val superAdder = (x: Int) => (y: Int) => x + y
  println(superAdder(3)(4))
}
