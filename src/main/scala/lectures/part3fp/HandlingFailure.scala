package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  //create success and failure
  val aSuccss = Success(3)
  val aFailure = Failure(new RuntimeException("Failure"))

  println(aSuccss)
  println(aFailure)

  def unsafeMehtods(): String = throw new RuntimeException("no String")

  //Try objects via the apply method
  val potentialFailure = Try(unsafeMehtods())
  println(potentialFailure)

  //syntax sugar
  val anotherPotentialFailure = Try {
    throw new RuntimeException("potential failure")
  }

  //utility
  println(potentialFailure.isSuccess)
  println(anotherPotentialFailure.isSuccess)

  //orElse
  def backupMethod(): String = "A valid result"
  val fallbackTry = Try(unsafeMehtods()).orElse(Try(backupMethod()))
  println(fallbackTry)

  //If you design the API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBuckupMethod(): Try[String] = Success("A valid result")
  val betterFallback = betterUnsafeMethod() orElse betterBuckupMethod()
  println(betterFallback)

  //map, Flatmap, filter
  println(aSuccss.map(_ * 2))
  println(aSuccss.flatMap(x => Success(x * 10)))
  println(aSuccss.filter(_ > 10))

  //for-comprehensions

  val host = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
    val random = new Random(System.nanoTime())
    if (random.nextBoolean()) "<html> ...</html>"
    else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if(random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  val possibleConnection = HttpService.getSafeConnection(host, port)
  val posssibleHtml = possibleConnection.flatMap(connection => connection.getSafe("/home"))
  posssibleHtml.foreach(renderHTML)

  //shorhand version
  HttpService.getSafeConnection(host, port)
    .flatMap(connection => connection.getSafe("/home"))
    .foreach(renderHTML)

  //for-comprehensions
  for {
    connection <- HttpService.getSafeConnection(host, port)
    html <- connection.getSafe("/home")
  } renderHTML(html)

  //in imperative language
//  try {
//    connection = HttpService.getConnection(host, port)
//      try {
//        page = connection.get("/home")
//        renderHTML(page+
//      } catch (some other excpetion) {
//      }
//    } catch (exception) {
//
//  }

}
