package lectures.part1basics

object ValuesVariablesTypes extends App {
  val x: Int = 42
  val y = 24
  
  println(x + " " + y)
  // Vals are immutable and used for functional programming
  // Compiler can infer types
  
  val aString: String = "Hello string :)"
  
  val aBoolean: Boolean = false
  val aChar: Char = 'c'
  val anInt: Int = x
  val aShort: Short = 4613
  val aLong: Long = 151345234524523452L
  val aFloat: Float = 2.2331231F
  val aDouble: Double = 2.1
  
  //variables used for side effects, are mutable
  var aVariable: Int = 4
  aVariable = 5
  
  
  
  
  
}
