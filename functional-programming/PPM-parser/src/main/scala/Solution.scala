import util.Pixel
import util.Util.toGrayScale
import util.Util.getNeighbors
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

    val column = split_information.slice(1, 2)
      .map(_.map(_.foldRight("")(_ + _).toInt))
      .head
      .head

    split_information.drop(3)
      .map(_.map(_.foldRight("")(_ + _).toInt))
      .map {
        case List(r, g, b) => Pixel(r, g, b)
        case _ => Pixel(0, 0, 0)
      }
      .grouped(column)
      .toList
  }

  def toStringPPM(image: Image): List[Char] = {
    val header = s"P3\n${image.head.size} ${image.size}\n255\n"

    def combineWith(delim: Char)(e: List[Char], acc: List[Char]): List[Char] =
      acc match {
        case Nil => e
        case _ => e ++ List(delim) ++ acc
      }

    val matrix = image.map(_.map(pixel =>
        List(pixel.red, pixel.green, pixel.blue).map(_.toString.toList))
      .foldRight(Nil: List[Char])((line, acc) =>
        line.foldRight(Nil: List[Char])(combineWith(' ')) ++ List('\n') ++ acc))

    header.toList ++ matrix.flatten
  }

  // ex 1
  def verticalConcat(image1: Image, image2: Image): Image = {
    image1 ++ image2
  }

  // ex 2
  def horizontalConcat(image1: Image, image2: Image): Image = {
    image1.zip(image2)
      .map(pair => pair._1 ++ pair._2)
  }

  // ex 3
  def rotate(image: Image, degrees: Integer): Image = {
    val nr_rotations = (degrees / 90) % 4

    @tailrec
    def loop(im: Image, nr: Int): Image = {
      def rotate_aux(image: Image): Image =
        image match {
          case Nil :: _ => Nil
          case _ => image.map(_.head) :: rotate_aux(image.map(_.tail))
        }

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

  def edgeDetection(image: Image, threshold : Double): Image = {
    val grayScale : GrayscaleImage = image.map(_.map(toGrayScale))
    val blur = applyConvolution(grayScale, gaussianBlurKernel)
    val Mx = applyConvolution(blur, Gx)
    val My = applyConvolution(blur, Gy)

    val combined = My.zip(Mx)
      .map(pair => pair._1.zip(pair._2))
      .map(_.map(pair => math.abs(pair._1) + math.abs(pair._2)))

    combined.map(_.map(pix => if (pix < threshold) Pixel(0,0,0) else Pixel(255,255,255)))
  }

  private def applyConvolution(image: GrayscaleImage, kernel: GrayscaleImage) : GrayscaleImage = {
    getNeighbors(image, kernel.size / 2)
      .map(_.map(_.zip(kernel).map(pair => pair._2.zip(pair._1).map(pair => pair._1 * pair._2).sum).sum))
  }

  // ex 5
  def moduloPascal(m: Integer, funct: Integer => Pixel, size: Integer): Image = {
    def cMod(n: Integer, k: Integer, prev: List[Integer]): Integer = {
      if (k > n) -1
      else if (k == 1 || k == n) 1
      else (prev.drop(prev.size - k).head + prev.drop(prev.size - k + 1).head) % m
    }

    @tailrec
    def loop(n: Integer, acc: List[List[Integer]], k: Integer): List[List[Integer]] = {

      @tailrec
      def loopIntern(n: Integer, acc: List[Integer], k: Integer, prev: List[Integer]): List[Integer] = {
        if (k > size) acc
        else loopIntern(n, cMod(n, k, prev) :: acc, k + 1, prev)
      }

      val prev = acc match {
        case Nil => Nil
        case _ => acc.head
      }

      if (n > size) acc
      else loop(n + 1, loopIntern(n, Nil, k, prev) :: acc, k)
    }

    loop(1, Nil, 1).map(_.map(funct).reverse).reverse
  }
}
