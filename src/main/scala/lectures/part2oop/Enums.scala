package lectures.part2oop

//already created a sealed datatype
object Enums {

  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    //add fields/methods

    def openDocuments(): Unit =
      if (this == READ) println("opening document...")
      else println("reading not allowed")
  }

  val somePermissions: Permissions = Permissions.READ

  //constructor arguments

  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4)
    case WRITE extends PermissionsWithBits(2)
    case EXECUTE extends PermissionsWithBits(1)
    case NONE extends PermissionsWithBits(0)
  }

  //companion object for enaum
  //can use as factory method
  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits = PermissionsWithBits.NONE
  }

  //standard API of enum
  //1. Can inspect ORDINAL
  val somePermissionsOrdinal = somePermissions.ordinal
  //2. Can iterate
  val allPermissions = PermissionsWithBits.values //array of all possbiel values of the enum
  val readPermission: Permissions = Permissions.valueOf("READ") //Permissions.READ

  def main(args: Array[String]): Unit = {
    somePermissions.openDocuments()
    println(somePermissionsOrdinal)
  }
}
