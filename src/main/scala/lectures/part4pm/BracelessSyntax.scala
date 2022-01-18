package lectures.part4pm

object BracelessSyntax {

  //if - expressions
  val anIfExpression = if (2 > 3) "bigger" else "smaller"
  //java style
  val anIfExpression2 =
    if (2>3) {
      "bigger"
    } else {
      "smaller"
    }

  //compact style
  val anIfExpression3 =
    if (2>3) "bigger"
    else "smaller"

  //scala 3
  val anIfExpression4 =
    if 2 > 3 then
      val result = "bigger"
      result //higher indentation that hte if part
    else
      val result = "smaller"
      result

  //scala 3 one-liner
  val anIfExpression5 = if 2 > 3 then "bigger" else "smaller"

  //for-comprehensions
  val aForComprehension = for {
    n <- List(1,2,3)
    s <- List("black", "white")
  } yield s"$n$s"

  //for-comprehensions scala 3
  val aForComprehension2 =
    for
      n <- List(1,2,3)
      s <- List("black", "white")
    yield s"$n$s"

  //pattern matching
  val meaningOfLife = 42
  val patternMatch = meaningOfLife match {
    case 1 => "one"
    case _ => 42
  }

  //pattern matching scala 3
  val patternMatch2 = meaningOfLife match
    case 1 => "one"
    case _ => 42

  //methods
  def computeMeaningOfLife(arg: Int): Int = {
    val partialResult = 40

    partialResult + 2
  }

  //methods without braces
  def computeMeaningOfLife2(arg: Int): Int =
    val partialResult = 40
    //still significant indentation thus code block
    partialResult + 2

  //define classes, traits, objects, enums and dataTypes with significant indentations

  class Animal: //compiler expects indentation region/ body of animal
    def eat(): Unit =
      println("I am eating")
    end eat

    def gtow(): Unit =
      println("I am growing")

  end Animal //completed def a class Animal - can be used everywhere


  //anymous classes
  val aSpecialAnimal = new Animal:
    override def eat(): Unit = println("I am special")

  //indentation - strictly larger indentation
  // 3 spaces + 2 tabs > 2 spaces + 2 tabs
  // 2 spaces + 2 tabs > 3 spaces + 1 tab
  // 3 tabs + 2 spaces ??? 2 tabs + 3 spaces


  def main(args: Array[String]): Unit = {

    println(computeMeaningOfLife2(78))
  }
}
