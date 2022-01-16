package exercises

abstract class MyGenericList[+A] {

  def head: A
  def tail: MyGenericList[A]
  def isEmpty: Boolean
  def add[B >:A](element: B): MyGenericList[B]
  def printElements: String
  //polymorphic call
  override def toString: String = "[" + printElements + "]"
}


object EmptyG extends MyGenericList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyGenericList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyGenericList[B] = new ConsG(element, EmptyG)
  override def printElements: String = ""
}

class ConsG[+A](h: A, t: MyGenericList[A]) extends MyGenericList[A] {
  def head: A = h
  def tail: MyGenericList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyGenericList[B] = new ConsG(element, this)
  def printElements: String =  {
    if(t.isEmpty) "" + h
    else "" + h + " " + t.printElements
  }
}

object ListTestG extends App {
  val listOfIntegers: MyGenericList[Int] = new ConsG(1, new ConsG(2, new ConsG(3, EmptyG)))
  val listofStrings: MyGenericList[String] = new ConsG("hello", new ConsG(" scala", EmptyG))

  println(listOfIntegers)
  println(listofStrings)

}