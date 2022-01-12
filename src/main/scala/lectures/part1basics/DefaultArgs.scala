package lectures.part1basics

object DefaultArgs extends App {

    def trFactorial(n: Int, acc: Int = 1): Int = {
      if (n<1) acc
      else trFactorial(n-1, n * acc)
    }
    
  // naming parameters allows to use lead parameter/default and also add parameters in any order
}
