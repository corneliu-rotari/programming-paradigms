import util.Pixel

import scala.annotation.tailrec

// Online viewer: https://0xc0de.fr/webppm/
object Solution {
  type Image = List[List[Pixel]]
  type GrayscaleImage = List[List[Double]]

  // prerequisites
  def fromStringPPM(image: List[Char]): Image = {
    def split(splitBy: Char)(c: Char, acc: List[List[Char]]): List[List[Char]] = acc match {
      case Nil => if (c == splitBy) Nil else List(List(c))
      case x :: xs => if (c == splitBy) Nil :: acc else (c :: x) :: xs
    }

    val split_information: List[List[List[Char]]] = image.foldRight(Nil: List[List[Char]])(split('\n'))
      .map(_.foldRight(Nil: List[List[Char]])(split(' ')))

    val params = split_information.slice(1, 2).map(_.map(_.foldRight("")(_ + _).toInt)).head

    split_information.drop(3).map(_.map(_.foldRight("")(_ + _).toInt))
      .map {
        case List(r, g, b) => Pixel(r, g, b)
        case _ => Pixel(0, 0, 0)
      }.grouped(params.head).toList
  }

  def toStringPPM(image: Image): List[Char] = {
    val intToChar: Int => Char = (nr) => (nr.toChar + '0').toChar
    val header = "P3\n" + intToChar(image.head.size) + ' ' + intToChar(image.size) + "\n255\n"

    def op(delim: Char)(e: List[Char], acc: List[Char]): List[Char] =
      acc match {
        case Nil => e
        case _ => e ++ List(delim) ++ acc
      }

    (header.toList :: image.map(_.map(pixel => List(pixel.red, pixel.green, pixel.blue).map(_.toString.toList))
      .foldRight(Nil: List[Char])((line, acc) => line.foldRight(Nil: List[Char])(op(' ')) ++ List('\n') ++ acc)
    )).flatten
  }

  // ex 1
  def verticalConcat(image1: Image, image2: Image): Image = {
    List(image1, image2).flatten
  }

  // ex 2
  def horizontalConcat(image1: Image, image2: Image): Image = {
    image1.zip(image2).map(pair => List(pair._1, pair._2).flatten)
  }

  // ex 3
  def rotate(image: Image, degrees: Integer): Image = {
    val nr_rotations = (degrees / 90) % 4

    def rotate_aux(image: Image): Image =
      image match {
        case Nil :: _ => Nil
        case _ => image.map(_.head) :: rotate_aux(image.map(_.tail))
      }

    @tailrec
    def loop(im: Image, nr: Int): Image = {
      if (nr == nr_rotations) im
      else loop(rotate_aux(im).reverse, nr + 1)
    }

    loop(image, 0)
  }

  // ex 4
  val gaussianBlurKernel: GrayscaleImage = List[List[Double]](
    List( 1, 4, 7, 4, 1),
    List( 4,16,26,16, 4),
    List( 7,26,41,26, 7),
    List( 4,16,26,16, 4),
    List( 1, 4, 7, 4, 1)
  ).map(_.map(_ / 273))

  val Gx : GrayscaleImage = List(
    List(-1, 0, 1),
    List(-2, 0, 2),
    List(-1, 0, 1)
  )

  val Gy : GrayscaleImage = List(
    List( 1, 2, 1),
    List( 0, 0, 0),
    List(-1,-2,-1)
  )

  def edgeDetection(image: Image, threshold : Double): Image = ???

  def applyConvolution(image: GrayscaleImage, kernel: GrayscaleImage) : GrayscaleImage = ???

  // ex 5
  def moduloPascal(m: Integer, funct: Integer => Pixel, size: Integer): Image = ???
}
