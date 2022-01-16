package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  //anonymous class - on the spot implementation
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("instantiate a real class rather than abstract class")
  }

  //equivalient with class AnonymousClasses$$anon$1 extends Animal {implementation)
  // then with val funnyAnimal: Animal = new AnonymousClasses$$anon$1

  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"hi, my name is $name, how can I help")
  }

  //anonymous class implementation, but still pass the arguments
  //also provide all the implementatin, otherwise anonymous cannot be created
  //anonymous class work for abstract and normal classes and traits
  val jim = new Person("jim") {
    override def sayHi: Unit = println(s"Hi, my name is Jim"
  }
}
