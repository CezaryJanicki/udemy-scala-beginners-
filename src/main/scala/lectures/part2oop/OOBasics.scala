package lectures.part2oop

import org.w3c.dom.css.Counter

object OOBasics extends App {

  val person = new Person("John", 26)
  println(person)
  person.greet("Daniel")
  person.greet()

  val writer = new Writer("mark", "twim", 1812)
  val impostor = new Writer("mark", "twim", 1812)
  val novel = new Novel("Tadpole", 1840, writer
  )
  println(novel.authorAge)
  println(novel.isWrittenBy(writer))
  println(novel.isWrittenBy(impostor))

  val counter = new Counter
  counter.inc.print
  counter.inc.inc.inc.print
  counter.inc(10).print
}

//constructor
class Person(name: String, val age: Int = 18) {
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")
  //overloading
  def greet(): Unit = println(s"Hi, I am $name")
  //overloading constructors
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
}
//class parameters are NOT FIELDS - should use VAL, VAR
//


class Writer(firstName: String, surname: String, val year: Int) {
  def fullName: String = firstName + " " + surname
}

class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def authorAge = yearOfRelease - author.year
  def isWrittenBy(author: Writer) = author == this.author
  def copy(newYearOfRelease: Int): Novel = new Novel(name, newYearOfRelease, author)
}

class CounterField(n: Int) {
  def count = n
}

class Counter(val count: Int = 0) {
  def inc = {
    println("incrementing")
    new Counter(count + 1)
  } //immutibility = vals for primitive types
  def dec = new Counter(count - 1)

  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n-1)
  }

  def dec(n: Int) = new Counter(count - n)

  def print = println(count)
}