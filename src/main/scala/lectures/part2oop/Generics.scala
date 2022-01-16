package lectures.part2oop

object Generics extends App {

  //classes and traits can be generic but objects not
  class MyList[+A] {
    //use the covariant of type A
    def add[B >: A] (element: B):MyList[B] = ???
    //if to a list of A (Cats) I add a B which is a supertype of A , then the list will turn to list B(Supertype)
    // so A=Cat; adding B=Dog which is in essence B=Animal(well they used to parameters and inferr), so I have a list of Animals now
  }

  class MyMap[Key, Value]
  trait MyTraints[A]

  val listofIntegers = new MyList[Int]
  val listofStrings = new MyList[String]

  //generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val empytListoFIntegers = MyList.empty[Int]


  //variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  //1. yes List[Cat] extends list[Animal] COVARIANCE [+A]
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? polluting animals = QUESTION --> return a list of animals [Animals >: Cats)(element: Dog/Animal): MyList[Animal0}
  //If I add a Dog then I have a list of Animals (turning list of Cats into list of animals - into generic )

  //2. no list of cats and list of animals are 2 seperate things INVARIANCE [A]
  class InvariantList[A]
  //cannot substitute on for another
  // will not compile ->  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat]

  //3. COUNTERINTUITIVE - CONTRAVARIANCE
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]
  //the relationships is counterintuitive for lists but check trainer

  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  //bounded types - Upper Bounded Types
  class Cage[A  <: Animal](animal: A) //subtypes of animal
  val cage = new Cage(new Dog) //expression evaluates to animal

  class Car
  // val newCage = new Cage(new Car) - will not compile
  
  //bounded tpes = Lower Bounded Types
  // (A >: Cat)
  
  
}
