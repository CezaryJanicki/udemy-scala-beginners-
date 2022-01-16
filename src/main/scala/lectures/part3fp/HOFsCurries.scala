package lectures.part3fp

object HOFsCurries extends App {
  // return type (Int => Int) - function taking Int returning Int
  // input 2 variables - (Int, (function)) return type => Int
  //this is a Higher Order Function (flat, flatMap, filter in Mylist)
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

  //task: function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x))) how to implement
  // nTimes(f, 2, f(x))
  // nTimes(f, 1, f(f(x))
  // so recursion of nTimes(f, n-1, f(x))
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if(n <= 0) x
    else nTimes(f, n-1, f(x))

  val plusOne = (x: Int) => x + 1

  println(nTimes(plusOne, 10, 1))

  //other way to implement nTimes
  //ntb(f,n) = x => f(f(f...(x)))) - return lambda for other values
  //incement10 = ntb(plusOne, 0) = x => plusOne(plusOne...(x))
  // val y = increment10(1)
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) = {
    if(n <= 0) (x: Int) => x //return lamba of n <=0
    else (x: Int) => nTimesBetter(f, n-1)(f(x)) //nTimesBetter returns a function which then will be applied to f(x)
  }

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

  //curried functions
  //type of adder is (Int => Int => Int) arrow is right associative so receive int and result in (Int => Int)
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) //y => 3 + y
  println(add3(10))
  println(superAdder(3)(10))

  // functions with mutliple parameter lists
  //curreid formatter
  def curriedFormatter(c: String)(x:Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f") //have to specify the format (Double => String) for smaller functions
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))
}
