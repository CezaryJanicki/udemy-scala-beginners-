package lectures.part3fp

object MapFlatmapFilterFor extends App {
  val list = List(1, 2, 3)
  //list
  println(list)
  println(list.head)
  println(list.tail)

  //map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  //filter
  println(list.filter(_ % 2 == 0))

  //flatMap
  val toPair = (x: Int) => List(x, x+1)
  println(list.flatMap(toPair))

  //print combinations between two lists
  val number = List(1,2,3,4)
  val chars = List('A', 'B', 'C', 'D')
  val colors = List("black", "white")

  //loops to functional code
  val combinations = number.flatMap(n => chars.map(c => "" + c + n))
  println(combinations)
  //3 way loop - iterations
  val combinationsColors = number.flatMap(n => chars.flatMap(c => colors.map(d => "" + c + n + d)))
  println(combinationsColors)

  //foreach
  list.foreach(println)

  //shorthand for chains of functions: for-comprehensions
  val forCombinations = for {
    n <- number if n % 2 == 0 //flatMap plus filtering - goes before ... number.flatMap(..)
    c <- chars  //flatMap
    d <- colors //map
  } yield ("" + c + n + d)

  println(forCombinations)

  for {
    n <- number
  } println(n) //equivalent to number.foreach(println)

  //syntax overload
  println(list.map { x =>
    x * 2
  })

}
