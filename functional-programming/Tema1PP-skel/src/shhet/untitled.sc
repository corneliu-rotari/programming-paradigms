import scala.annotation.tailrec
case class Pixel(red: Integer, green: Integer, blue: Integer) {
}

type Image = List[List[Pixel]]
//type Image = List[List[Int]]
type GrayscaleImage = List[List[Double]]


def moduloPascal(m: Integer, funct: Int => Pixel, size: Int) = {
  def cmod(n : Int, k : Int, prev: List[Int]) : Int = {
    if (k > n) -1
    else if (k == 1 || k == n) 1
    else (prev.drop(prev.size - k ).head  + prev.drop(prev.size - k + 1).head) % m
//    else (cmod(n - 1 , k - 1) % m + cmod(n-1, k) % m ) %m
//    else cmod(n - 1 , k - 1) + cmod(n-1, k)
  }


  @tailrec
  def loopin(n : Int, acc: List[Int], k: Int, prev: List[Int]) : List[Int] = {
    if (k > size) acc
    else loopin(n, cmod(n,k, prev) :: acc, k + 1, prev)
  }

  @tailrec
  def loop(n: Int, acc: List[List[Int]], k : Int): List[List[Int]] = {
    val prev = acc match {
      case Nil => Nil
      case _ => acc.head
    }
    if (n > size) acc
    else loop(n + 1, loopin(n, Nil, k, prev) :: acc, k)
  }
  loop(1, Nil, 1).map(_.reverse).reverse.foreach(println)
}

def pickColor(i: Int) : Pixel = {
  if (i == 0) Pixel(255, 0, 0)
  else if (i == 1) Pixel(0, 0, 255)
  else if (i == 2) Pixel(0, 255, 0)
  else if (i == 3) Pixel(255, 255, 255)
  else Pixel(0, 0, 0)
}
moduloPascal(3, pickColor, 81)