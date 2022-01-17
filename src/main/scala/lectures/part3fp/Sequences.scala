package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  //Seq
  val aSequence = Seq(1,2,3,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(7,6,5))
  println(aSequence.sorted)


  //Ranges
  val aRange: Seq[Int] = 1 to 10 //until - right exclusive
  aRange.foreach(println)

  (1 to 10).foreach(x => println("Hello"))

  //Lists
  val aList = List(1,2,3)
  val pended = 42 +: aList :+ 90
  val prepended = 42 :: aList //syntactic sugar for ::.apply
  val appended = aList :+ 42
  println(pended)
  println(prepended)
  println(appended)

  //curried f
  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(aList.mkString("-=-"))

  //arrays

  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3)
  println(numbers)
  println(threeElements)
  threeElements.foreach(println) //have default values

  //mutation
  numbers(2) = 0 //syntax sugar for numbers.update(2,0)
  println(numbers.mkString(" "))

  //arrays and seq
  val numbersSeq: Seq[Int] = numbers //wrapped array - implicit conversion
  println(numbersSeq)

  //vectors
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  //vestors vs lists
  val maxRuns = 1000
  val maxCapacity = 1_000_000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns //average time of collection to be updateda at random index
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  //keeps reference to tail
  //updating element in middle takes time
  println(getWriteTime(numbersList))
  // 32 branch tree - depth is small
  //it needs to replace entire 32 element chunk
  println(getWriteTime(numbersVector))
}
