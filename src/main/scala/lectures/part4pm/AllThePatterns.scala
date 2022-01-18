package lectures.part4pm

import exercises.{ConsG, EmptyG, MyGenericList}

object AllThePatterns extends App {
  // constants
  val x: Any = "Scala"

  val constans = x match {
    case 1 => "a number"
    case "Scala" => "The scala"
    case true => "The truth"
    case AllThePatterns => "A single object"
  }

  //match anything
  //1.wildcard

  val matchAnytning = x match {
    case _ => ""
  }

  //2. variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  //tuples
  val aTuple = (1,2)
  val matchATuple = aTuple match {
    case (1,1) => ""
    case(something, 2) => s"I've found $something"
  }

  //can be nested
  val nestedTuple = (1, (1,3))
  val matchANestedTuple = nestedTuple match {
    case (_, (1, v)) => ""
  }

  //case classes - constructor pattern
  //can be nested with case classes
  val aList: MyGenericList[Int] = ConsG(1, ConsG(2, EmptyG))
  val matchAList = aList match {
    case EmptyG => ""
    case ConsG(head, tail) => ""
    case ConsG(head, ConsG(subhead, subtail)) => ""
  }

  //list patterns
  val standardList = List(1,2,3,42)
  val standardListMatching = standardList match {
    case List(1,_,_,_) => //extractor
    case List(1, _*) => //list of arbitrary length
    case 1 :: List(_) => //infix pattern
    case List(1,2,3) :+ 42 => //infix pattern
  }

  //type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => //explicit type specifier
    case _ =>
  }

  //name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ ConsG(_, _) => //name binding => use the name later(here)
    case ConsG(1, rest @ ConsG(1, _)) => //name binding inside ndested patterns
  }

  //multi-patterns
  val multipattern = aList match {
    case EmptyG | ConsG(1, _) => //compound pattern (multi-pattern)
  }

  //if-guards
  val secondElementSpecial = aList match {
    case ConsG(_, ConsG(specialElement, _)) if specialElement % 2 == 0 =>
  }

  //
  val numbers = List(1,2,3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int] => "a list of Numbers"
    case _ => ""
  }
  //jvm problem - however first in line is Strings as numbers Type is erased due to Java5/1 :List[Type erasure]
  println(numbers)
  println(numbersMatch)
}
