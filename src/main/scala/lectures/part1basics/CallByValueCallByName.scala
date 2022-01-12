package lectures.part1basics

object CallByValueCallByName extends App {

  def callByValue(x: Long): Unit = {
    println("By value + " + x)
    println("By value + " + x)
  }

  def callByName(x: => Long): Unit = {
    println("By name + " + x)
    println("By name + " + x)
  }
  //expression passed, and everytime its evaluated


  callByValue(System.nanoTime())

  callByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()

  def printFirst(x: Int, y: => Int) = println(x)

  //printFirst(infinite(), 34) crashes evaluated first - mem stack overflow
  printFirst(34, infinite()) // - not evaluated

}
