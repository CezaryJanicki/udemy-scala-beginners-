package lectures.part2oop

object CaseClasses extends App {
  //equals, hasCode, toString - solution for defaults
  case class Person(name: String, age: Int) {
  }
  //1. .class parameters are promoted to fields
  val jim = new Person("jim", 34)
  println(jim.name)
  //2. sensible toString
  println(jim.toString)
  println(jim) //already delegated to toString - syntactic sugar

  //3. equals and hashCode implements Out of the Box
  val jim2 = new Person("jim", 34)

  println(jim==jim2)

  //4. Case Classes have handy copy mehtod
  val jim3 = jim.copy()
  val jim4 = jim.copy(age = 54)

  println(jim3)
  println(jim4)

  //5. Case classes have companion objects
  
  val thePerson = Person
  val mary = Person("Mary", 23) //applies authomatically apply method
  
  //6. CCs are serializable - good for Akka - messages are case classes
  
  //7. CCs have extractor patters - can be used in PATTERN MATCHING
  
  //have the same properties as CC but they have no companion objects already being ones
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }
  
}
