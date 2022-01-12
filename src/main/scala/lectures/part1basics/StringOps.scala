package lectures.part1basics

object StringOps extends App {
  
  val str: String = "Hello, I am learning Scala"

  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-" ))
  println(str.toLowerCase())
  println(str.toUpperCase())
  println(str.length)
  
  //Scala specific
  val aNumberString = "45"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z') //appending and prepending scala operators
  println(str.reverse)
  println(str.take(2))
  
  //Scala-specific: String interpolators. 
  
  //S-interpolators
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and I am $age years old"
  val greeting2 = s"Hello, my name is $name and I WILL be turning ${age+1} years old"
  println(greeting)
  println(greeting2)
  
  //F-interpolators receives formats printf
  val speed = 1.2f
  val myth = f"$name%s can eat $speed%2.2f burgers per minute"
  println(myth)
  
  //raw-interpolator
  println(raw"This is a \n new line") //escaped
  val escaped = "This is a \n new line"
  println(raw"$escaped") //not escaped
  
}
