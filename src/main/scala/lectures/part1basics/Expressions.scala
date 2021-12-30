package lectures.part1basics

object Expressions extends App {
  val x = 1+2 //expresions, evaluated, type inference

  println(x)

  println(2+3*4) //school order + - / & | << >> >>> (right shift with zero extension)

  println(1 == x) //== != > >= <= <

  println(!(1==x)) //unary operator !, binary operators && ||

  var aVariable = 2
  aVariable += 3 // also works with -= *= /+ ... works with side effects

  //Instructions (DO - Java mainly) vs Expressions (Value, Type) every bit of code in functional p. creates value
  //Instructions are executed(Java), expressions are evaluated(Scala)
  
  //If expression

  val aCondition = true
  val aCondinionedValue = if(aCondition) 5 else 3 //If expression - gives a value no reassigns
  println(aCondinionedValue)
  println(if(aCondition) 5 else 3)

  //Loops are not much used in Scala, do not use imperative code in Scala

  var i=0
  val aWhile = while(i<10) {
    println(i)
    i += 1
  }

  //Everything in Scala is an Expression!

  val aWeirdValue = (aVariable = 3)
  //Type of aWeirdValue is Unit Unit == Void
  println(aWeirdValue)

  //side effects: println(), whiles, reassigning of var - resulting type is UNIT, imperative programming

  //Code blocks is an expression, value is the value of the last expression, scope to block
  val aCodeBlock = {
    val y = 2
    val z = y + 1
    if(z >2) "hello" else "goodbye"
  }

}
