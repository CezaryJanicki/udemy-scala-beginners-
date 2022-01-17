package exercises

abstract class MyGenericList[+A] {

  def head: A
  def tail: MyGenericList[A]
  def isEmpty: Boolean
  def add[B >:A](element: B): MyGenericList[B]
  def printElements: String
  //polymorphic call
  override def toString: String = "[" + printElements + "]"

  //higher order functions - either receive functions as parameters or return functions - functions as first class values
  def map[B](transformer: A => B): MyGenericList[B]
  def flatMap[B](transformer: A => MyGenericList[B]): MyGenericList[B]
  def filter(predicate: A => Boolean): MyGenericList[A]
  def ++[B >: A](list: MyGenericList[B]): MyGenericList[B]

  //HOFs
  def foreach(f: A => Unit): Unit = ()
  def sort(compare: (A, A) => Int): MyGenericList[A]
  def zipWith[B,C](list: MyGenericList[B], zip: (A, B) => C): MyGenericList[C]
  def fold[B](start: B)(operator: (B, A) => B): B
}


case object EmptyG extends MyGenericList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyGenericList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyGenericList[B] = new ConsG(element, EmptyG)
  override def printElements: String = ""

  def map[B](transformer: Nothing => B): MyGenericList[B] = EmptyG
  def flatMap[B](transformer: Nothing => MyGenericList[B]): MyGenericList[B] = EmptyG
  def filter(predicate: Nothing => Boolean): MyGenericList[Nothing] = EmptyG
  def ++[B >: Nothing](list: MyGenericList[B]): MyGenericList[B] = list

  //HOFs
  override def foreach(f: Nothing => Unit): Unit = ()
  def sort(compare: (Nothing, Nothing) => Int) = EmptyG
  def zipWith[B,C](list: MyGenericList[B], zip: (Nothing, B) => C): MyGenericList[C] =
    if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else EmptyG
  def fold[B](start: B)(operator: (B, Nothing) => B): B = start

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

  def map[B](transformer: A => B): MyGenericList[B] =
    new ConsG(transformer(h), t.map(transformer))
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

  def flatMap[B](transformer: A => MyGenericList[B]): MyGenericList[B] =
    transformer.apply(h) ++ t.flatMap(transformer)
  /*
  [1.2]].flatMap(n => [n, n+1])
  = [1,2] ++  [2].flatMap(n => [n, n+1])
  = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
  = [1,2] ++ [2,3] ++ Empty
  */


  def filter(predicate: A => Boolean): MyGenericList[A] = {
    if (predicate.apply(h)) new ConsG(h, t.filter(predicate)) //or predicate(h) rather than apply
    else t.filter(predicate)
  }
  /*
  [1.2.3].filter(n % 2 == 0) =
    [2.3.].filter(n % 2 == 0) =
    new Cons(2, [3].filter(n & 2 == 0) =
    new Cons(2, Empty.filter(n % 2 == 0 ) =
    new Cons(2, Empty)
  */

  //HOFs
  override def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(compare: (A, A) => Int): MyGenericList[A] = {
    def insert(x: A, sortedList: MyGenericList[A]): MyGenericList[A] =
      if(sortedList.isEmpty) new ConsG(x, EmptyG)
      else if (compare(x, sortedList.head) <= 0) new ConsG(x, sortedList)
      else new ConsG(sortedList.head, insert(x,sortedList.tail))
    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  def zipWith[B, C](list: MyGenericList[B], zip: (A, B) => C): MyGenericList[C] = {
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else return ConsG(zip(h, list.head), t.zipWith(list.tail, zip))
  }

  def fold[B](start: B)(operator: (B,A) => B): B = {
    t.fold(operator(start, h))(operator)
  }
}

//trait MyPredicate[-T] { //T => Boolean
//  def test(elem: T): Boolean
//}
//
//trait MyTransformer[-A, B] { // A => B
//  def transform(elem: A): B
//}

object ListTestG extends App {
  val listOfIntegers: MyGenericList[Int] = new ConsG(1, new ConsG(2, new ConsG(3, EmptyG)))
  val clonedListOfIntegers: MyGenericList[Int] = new ConsG(1, new ConsG(2, new ConsG(3, EmptyG)))
  val anotherListOfIntegers: MyGenericList[Int] = new ConsG(4, new ConsG(5, EmptyG))
  val listofStrings: MyGenericList[String] = new ConsG("hello", new ConsG("scala", EmptyG))

  println(listOfIntegers)
  println(listofStrings)

//  println(listOfIntegers.map(new Function1[Int, Int] {
//    override def apply(elem: Int): Int = elem * 2
//  })).toString
  println(listOfIntegers.map(elem => elem * 2).toString)
  println(listOfIntegers.map(_ * 2).toString)

//  println(listOfIntegers.filter(new Function1[Int, Boolean] {
//    override def apply(elem: Int): Boolean = elem % 2 == 0
//  }).toString)
  
  println(listOfIntegers.filter(elem => elem % 2 == 0).toString)
  println(listOfIntegers.filter(_ % 2 == 0).toString)
  
  println((listOfIntegers ++ anotherListOfIntegers).toString)

//  println(listOfIntegers.flatMap(new Function1[Int, MyGenericList[Int]] {
//    override def apply(elem: Int): MyGenericList[Int] = new ConsG(elem, new ConsG(elem +1, EmptyG))
//  })).toString
    //no underscore here as 2 elem used
    println(listOfIntegers.flatMap(elem => new ConsG(elem, new ConsG(elem +1, EmptyG))).toString)

  //due to CC alread equals, toString, hashCode, serializing implemented - otherwise should use a recursive equals method for a list
  println(clonedListOfIntegers == listOfIntegers)

  //Hofs
  listOfIntegers.foreach(println)
  println(listOfIntegers.sort((x, y) => y - x))
  println(anotherListOfIntegers.zipWith[String, String](listofStrings, _ + "-" + _))
  println(listOfIntegers.fold(0)(_ + _))
}