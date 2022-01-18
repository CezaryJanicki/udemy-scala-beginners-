package lectures.part3fp

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None
  println(myFirstOption)
  println(noOption)

  // WORKING with unsafe APIs
  def unsafeMethod(): String = null

  // val result = Some(unsafeMethod()) wrong
  val result = Option(unsafeMethod()) //no nullcheck because either Option or Method
  println(result)

  //chained methods
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  //DESIGN unsafe APIs - make functions with Option
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()


  //functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) //unsafe
  println(myFirstOption.getOrElse(Option))

  //map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  //for-comprehensions

  val config: Map[String, String] = Map(
    //fetched from elswhere
    "host" -> "153.234.141.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected"
  }
  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  //establishing a connection
  val host = config.get("host")
  val port = config.get("port")
  // if h !null if p !null return Connection.apply(h,p) return null - imperative coe
  val connection = host.flatMap( h => port.flatMap(p => Connection.apply(h, p)))
  //if c !null return c.connect otherwise null
  val connectionStatus = connection.map(c => c.connect)
  //if connectionStatus == null printl(Mone) else connectionStatus.get
  println(connectionStatus)
  connectionStatus.foreach(println)

  //chaind calls - shorhand - but with new random operation
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
        .map(connection => connection.connect))
      .foreach(println)

  //for-comprehensions
  val forFonnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect

  forFonnectionStatus.foreach(println)

}
