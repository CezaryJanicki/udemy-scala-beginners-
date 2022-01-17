package lectures.part3fp

object TuplesAndMaps extends App {

    //tuples = finite ordered "lists"
    val aTuple = new Tuple2(2, "Hello Scala") //type Tuple2[Int, String} = (Int, String)
    val bTuple = (3, "Hello Scala Again")

    println(aTuple._1)
    println(aTuple.copy(_2 = "Goodbye Java"))
    println(aTuple.swap) //(String, Int)

    //Maps: keys, values
    val aMap: Map[String, Int] = Map()

    val phoneBook = Map(("Jim", 555),"Daniel" -> 243).withDefaultValue(-1) //syntactic sugar for tuple (A,B) A->B
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
}
