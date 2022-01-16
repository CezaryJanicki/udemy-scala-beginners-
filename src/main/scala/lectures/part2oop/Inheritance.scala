package lectures.part2oop

object Inheritance extends App {


  //Single class inheritance
  // private protected(class and subclasses) public
    sealed class Animal {
    val creatureType = "Dinosaur"
    def eat = println("mniam")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch")
    }
  }

  val cat = new Cat
  cat.eat
  cat.crunch


  //constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)

    // class Adult(name: String, age: Int, idCard: String) extends Person  //not compiling because parent class is does not has such constructor, should use auxiliay
    class Adult(name: String, age: Int, idCard: String) extends Person(name)
  }
    //overriding - field, field with parameters, in parameters
    class Dog(override val creatureType: String) extends Animal {
     // override val creatureType: String = "Canine"
      override def eat = {
        super.eat
        println("dog eats")
      }
    }


    class Doggie(dogType: String) extends Animal {
      override val creatureType = dogType
    }

  val dog = new Dog("KS")
  dog.eat
  println(dog.creatureType)


  //type substitution (broad: polymorfism) - goes to most overriden method
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  //overRiding vs

  // super - access to parent
  
  //preventing overrides
  //final keyword on member
  //final on class member
  //seal the class = extends classes in THIS FILE, prevents extension in ohter files  keaword - SEALED


}

