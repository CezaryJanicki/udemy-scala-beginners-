package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int = {
    if(n <= 1) 1
    else {
      println("Computing factorial of " + n + " -  I first need factorial of " + (n-1))
      val result = n * factorial(n - 1)
      println("computed factorial of " + n + "")
      return result
    }
  }

  //Example of using CALL STACK
    println(factorial(10))
    println(factorial(1000)) //call stack overflow although here zero because out of Int scope

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt =
    if (x <= 1) accumulator
    else factHelper(x - 1, x * accumulator)  //Tail recursion = use the recursive call as the LAST expression

    factHelper(n, 1)
  }
  /*
  anotherFactorial(10) = factHelper(10,1)
  = factHelper(9, 10*1)
  = factHelper(9, 9 * 10 * 1)
  ...
  = factHelper(2, 3* 4* 5 ... * 10 * 1)
  = factHelper(1, 2 * .... 10 * 1)
  = accumulator i.e. 2 * 3 * 4 * ... 10 * 1) which is factorial computation
  */

  println(anotherFactorial(2_000))

  // When you need loops, use TAIL_RECURSION

  //Greeting Tail_Recursive
  @tailrec
  def concatinateTailrec(aString: String, n: Int, accumulator: String): String =
    if (n<=0) accumulator
    else concatinateTailrec(aString, n-1, aString + accumulator)

  println(concatinateTailrec("mark ", 20_000, ""))

  //isPrime with tail recursion
  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailrec(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTailrec(t - 1, n % t != 0 && isStillPrime)

      isPrimeTailrec(n / 2, true)
  }

  println(isPrime(2639))
  println(isPrime(2003))


}

