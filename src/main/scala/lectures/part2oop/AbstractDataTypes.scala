package lectures.part2oop

object AbstractDataTypes extends App {

  //abstract methods and clasess

  abstract class Animal {
    val creatureType: String
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "canine"
    def eat: Unit = println("mniam") //no need to put override
  }

  //traits - have abstrat fields, but can be
  //can have multilple traits
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait Coldblooded 
  
  class Crocodile extends Animal with Carnivore with Coldblooded {
    override val creatureType: String = "crock"
    def eat: Unit = println("mniam")
    def eat(animal: Animal):Unit = println(s"I am a croc and I'm eathing ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile

  croc.eat(dog)
  
  //traits vs abstract classes
  //both can have abstract and non abstract members
  //1. traits do not have constructor parameters (no params in traits)
  //2. can only extend 1 class but can have multiple traits 
  //3. traits are behaviour related (abstract class is a type of "thing")
}
