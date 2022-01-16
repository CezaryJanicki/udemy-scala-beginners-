package lectures.part2oop

import java.nio.BufferOverflowException

object Exceptions extends App {

  val x: String = null
  //println(x.length) - NullPointerException
  //exeptions come from JVM

  // val aWeirdValue:String = throw new NullPointerException //returns nothing

  //throwable classes - Exceptiona(JVM) and Error(System)

  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you")
    else 32

  val potentialFail = try {  //anyVal as there is Int, Exception, Unit - finally does not influence return type of this expression, finally is used for side efects
    //failing code
    getInt(true)
  } catch {
    case e: RuntimeException => println("Caught RuntimeException")
  } finally {
    println("finally")
  }

  class MyException extends Exception {
      }
  val exception = new MyException

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by 0")

  object PocketCaluclator {
    def add(x: Int, y: Int): Int = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtrackt(x: Int, y: Int): Int = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int): Int = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int) = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }
  }

  //println(PocketCaluclator.add(Int.MaxValue, 10))
  println(PocketCaluclator.divide(2, 0))
}
