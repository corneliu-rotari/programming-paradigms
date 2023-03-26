import scala.annotation.tailrec
case class Pixel(red: Integer, green: Integer, blue: Integer) {
}

//type Image = List[List[Pixel]]
type Image = List[List[Int]]
type GrayscaleImage = List[List[Double]]


val im1 = List(List(1,2,3),List(4,5,6),List(10,11,12),List(16,17,18))

val im2 = List(List(1,2,3),List(7,8,9),List(13,14,15))

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
im1.foreach(println)

rotate(im1, 90).foreach(println)