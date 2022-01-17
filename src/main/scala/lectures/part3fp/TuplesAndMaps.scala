package lectures.part3fp

import scala.annotation.tailrec

object TuplesAndMaps extends App {

    //tuples = finite ordered "lists"
    val aTuple = new Tuple2(2, "Hello Scala") //type Tuple2[Int, String} = (Int, String)
    val bTuple = (3, "Hello Scala Again")

    println(aTuple._1)
    println(aTuple.copy(_2 = "Goodbye Java"))
    println(aTuple.swap) //(String, Int)

    //Maps: keys, values
    val aMap: Map[String, Int] = Map()

    val phoneBook = Map(("Jim", 555),"Daniel" -> 243, ("JIM", 5556)).withDefaultValue(-1) //syntactic sugar for tuple (A,B) A->B
    println(phoneBook)

    println(phoneBook.contains("Jim"))
    println(phoneBook("Jim"))
    // println(phoneBook("Jil")) - no such element exception thus .withDefaultValue(-1)
    println(phoneBook("Jil"))

    //add a pair
    val newPairing = "Mary" -> 23234
    val newPhonebook = phoneBook + newPairing
    println(newPhonebook)

    //functionals on maps
    //map, flatMap, filter
    //resulting keys Jim and JIM now map; data overwritten
    println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))


    //filterKeys
    println(phoneBook.view.filterKeys(_.startsWith("J")).toMap)

    //mapValues
    println(phoneBook.mapValues(number => "-12321" + number * 10).toMap)

    //conversions to other collections
    println(phoneBook.toList)
    println(List("Daniel" -> 555).toMap)
    val names = List("Bob", "James", "Angela", "Jim", "Beam")
    println(names.groupBy(name => name.charAt(0)))

    //Social network implementation
    def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
      network + (person -> Set())

    def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
      val friendsA = network(a)
      val friendsB = network(b)

      network + (a -> (friendsA + b)) + (b -> (friendsB + a))
    }

    def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
      val friendsA = network(a)
      val friendsB = network(b)

      network + (a -> (friendsA - b)) + (b -> (friendsB - a))
    }

    def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
      def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
        if (friends.isEmpty) networkAcc
        else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

      val unfriended = removeAux(network(person), network)
      unfriended - person
    }

    val empty: Map[String, Set[String]] = Map()
    val network = add(add(empty, "Mary"), "Bob")
    println(network)
    println(friend(network, "Bob", "Mary"))
    println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
    println(remove(friend(network, "Bob", "Mary"), "Bob"))

    val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
    val jimBob = friend(people, "Bob", "Jim")
    val testNet = friend(jimBob, "Bob", "Mary")

    println(testNet)

    def nFreinds(network: Map[String, Set[String]], person: String): Int = {
      if (!network.contains(person)) 0
      else network(person).size
    }

    println(nFreinds(testNet,"Bob"))

    def mostFriends(network: Map[String, Set[String]]): String =
      network.maxBy(pair => pair._2.size)._1

    println(mostFriends(testNet))

    def nPeopleWithNoFreinds(network: Map[String, Set[String]]): Int =
      network.filterKeys(k => network(k).isEmpty).size
      //network.filter(pair => pair._2.isEmpty).size
      //network.count(pair => pair._2.isEmpty)
      //network.count(_._2.isEmpty) //very shorthand

    println(nPeopleWithNoFreinds(testNet))

    def socialConecction(network: Map[String, Set[String]], a: String, b: String): Boolean = {
      @tailrec
      def breadthSearchFirst(target: String, consideredPeople: Set[String], discoverdPoeple: Set[String]): Boolean = {
        if (discoverdPoeple.isEmpty) false
        else {
          val person = discoverdPoeple.head
          if (person == target) true
          else if (consideredPeople.contains(person)) breadthSearchFirst(target, consideredPeople, discoverdPoeple.tail)
          else breadthSearchFirst(target, consideredPeople + person, discoverdPoeple.tail ++ network(person))
        }
      }
      breadthSearchFirst(b, Set(), network(a) + a)
    }

    println(socialConecction(testNet, "Mary", "Jim"))
    println(socialConecction(network, "Mary", "Bob"))
}
