package exercises

abstract class MyGenericList[+A] {

  def head: A
  def tail: MyGenericList[A]
  def isEmpty: Boolean
  def add[B >:A](element: B): MyGenericList[B]
  def printElements: String
  //polymorphic call
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: MyTransformer[A,B]): MyGenericList[B]
  def flatMap[B](transformer: MyTransformer[A, MyGenericList[B]]): MyGenericList[B]
  def filter(predicate: MyPredicate[A]): MyGenericList[A]
  def ++[B >: A](list: MyGenericList[B]): MyGenericList[B]
}


case object EmptyG extends MyGenericList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyGenericList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyGenericList[B] = new ConsG(element, EmptyG)
  override def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing,B]): MyGenericList[B] = EmptyG
  def flatMap[B](transformer: MyTransformer[Nothing, MyGenericList[B]]): MyGenericList[B] = EmptyG
  def filter(predicate: MyPredicate[Nothing]): MyGenericList[Nothing] = EmptyG
  def ++[B >: Nothing](list: MyGenericList[B]): MyGenericList[B] = list
}

case class ConsG[+A](h: A, t: MyGenericList[A]) extends MyGenericList[A] {
  def head: A = h
  def tail: MyGenericList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyGenericList[B] = new ConsG(element, this)
  def printElements: String =  {
    if(t.isEmpty) "" + h
    else "" + h + " " + t.printElements
  }

  def map[B](transformer: MyTransformer[A,B]): MyGenericList[B] =
    new ConsG(transformer.transform(h), t.map(transformer))
  /*
  [1,2,3].map(n+2) = new Cons(2, [2,3].map(n*2)) = new Cons(2, new Cons(4, [3].map(n*2)))
  = new Cons(2, new Cons(4, new Cons(6, Empty.map(n*2)))
  = new Cons(2, new Cons(4, new Cons(6, Empty)))
  */

  //concatenation
  /*
  [1,2] ++ [3,4,5] =
  new Cons(1, [2] ++ [3,4,5])
  = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
  = new Cons(1, new Cons(2, [3,4,5]))
  */
  def ++[B >: A](list: MyGenericList[B]): MyGenericList[B] = new ConsG(h, t ++ list)

  def flatMap[B](transformer: MyTransformer[A, MyGenericList[B]]): MyGenericList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)
  /*
  [1.2]].flatMap(n => [n, n+1])
  = [1,2] ++  [2].flatMap(n => [n, n+1])
  = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
  = [1,2] ++ [2,3] ++ Empty
  */


  def filter(predicate: MyPredicate[A]): MyGenericList[A] = {
    if (predicate.test(h)) new ConsG(h, t.filter(predicate))
    else t.filter(predicate)
  }
  /*
  [1.2.3].filter(n % 2 == 0) =
    [2.3.].filter(n % 2 == 0) =
    new Cons(2, [3].filter(n & 2 == 0) =
    new Cons(2, Empty.filter(n % 2 == 0 ) =
    new Cons(2, Empty)
  */

}

trait MyPredicate[-T] {
  def test(elem: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(elem: A): B
}

object ListTestG extends App {
  val listOfIntegers: MyGenericList[Int] = new ConsG(1, new ConsG(2, new ConsG(3, EmptyG)))
  val clonedListOfIntegers: MyGenericList[Int] = new ConsG(1, new ConsG(2, new ConsG(3, EmptyG)))
  val anotherListOfIntegers: MyGenericList[Int] = new ConsG(4, new ConsG(5, EmptyG))
  val listofStrings: MyGenericList[String] = new ConsG("hello", new ConsG(" scala", EmptyG))

  println(listOfIntegers)
  println(listofStrings)

  println(listOfIntegers.map(new MyTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * 2
  })).toString

  println(listOfIntegers.filter(new MyPredicate[Int] {
    override def test(elem: Int): Boolean = elem % 2 == 0
  }).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(listOfIntegers.flatMap(new MyTransformer[Int, MyGenericList[Int]] {
    override def transform(elem: Int): MyGenericList[Int] = new ConsG(elem, new ConsG(elem +1, EmptyG))
  })).toString

  //due to CC alread equals, toString, hashCode, serializing implemented - otherwise should use a recursive equals method for a list
  println(clonedListOfIntegers == listOfIntegers)
}