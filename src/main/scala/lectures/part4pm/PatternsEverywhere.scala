package lectures.part4pm

object PatternsEverywhere extends App {

  try {

  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "sth else"
  }
  //caches are actually MATCHES

  val list = List(1,2,3,4,5)
  val evenOnes = for {
    x <- list if x % 2 == 0 //generators - pattern matching again
  } yield 10 * x

  val tuples = List ((1,2), (3,4))
  val filterTuples = for {
    (first, second) <- tuples //decomposing with pattern matching
  } yield first * second
  //case classes, :: operators, ...

  val tuple = (1,2,3)
  val (a,b,c) = tuple //name binding of pattern matching
  println(b)
  println(tuple._2)

  //
  val head :: tail = list
  println(head)
  println(tail)

  //partial function based on pattern matching
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the one"
    case _ => "sth else"
  } //partial function literal

  //equivalent to
  val mappedList2 = list.map { x =>
    x match {
      case v if v % 2 == 0 => v + "is even"
      case 1 => "the one"
      case _ => "sth else"
    }
  }

  println(mappedList)



}

