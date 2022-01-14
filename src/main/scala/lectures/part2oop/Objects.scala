package lectures.part2oop

object Objects {
  //Scala does not have class-level functionality ("no static")
  //Objects have static functionality
  //Objects do not get
  //Scala Object is SINGLETON
  object Person { // own type and its only instance
    val N_EYES = 2

    def canFly: Boolean = false

    //factory method - in practice they are under apply function
    def from(mother: Person, father: Person): Person = new Person("Booby")

    def apply(mother: Person, father: Person): Person = new Person("Booby")
  }

  class Person(val name: String = "") {
    //seperating instance level functionality from "static"/class level functionality
    //COMPANIONS - class and object have the same scope and same name
  }

  def main(args: Array[String]): Unit = { //or use Extends App which already has main(args: Array[String]) method
    println(Person.N_EYES)
    println(Person.canFly)

    val mary = Person
    val john = Person
    println(mary == john) //equal reference

    val mark = new Person
    val jenny = new Person
    println(mark == jenny) //different instances because also a class

    val bobbie = Person.from(mark, jenny)
    val bobbieBrother = Person(mark, jenny) //factory method used as constructor through apply method in singleton object

    //Scala Applications = Scala object with particular method def main(args: Array[String]): Unit

  }

}