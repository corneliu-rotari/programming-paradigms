type Line = List[String]
type Row = Map[String, String]

trait FilterCond {
  def eval(r: Row): Option[Boolean]
}

case class Field(colName: String, predicate: String => Boolean) extends FilterCond {
  // 2.2.
  override def eval(r: Row): Option[Boolean] = (r.getOrElse(colName, "")) match {
    case "" => None
    case t => Some(predicate(t))
  }
}

class Table (columnNames: Line, tabular: List[Line]) {
  def getColumnNames: Line = columnNames

  def getTabular: List[List[String]] = tabular

  // 1.1
  override def toString: String= {
    def addDelim(c: String)(elem: String, acc: String): String =
      acc match {
        case "" => elem
        case _ => elem + c + acc
      }

    (columnNames :: tabular).map(_.foldRight("")(addDelim(",")))
      .foldRight("")(addDelim("\n"))
  }

  def select(columns: Line) = {
//    val check = columns.foldRight(true)((name, acc) => columnNames.contains(name) & acc)
//    if (!check) None
//    else {
      val i = columns.map(columnNames.indexOf(_))
      val trans = tabular.transpose
      i.foldRight(Nil:List[Line])((idx, acc) => trans(idx)::acc).transpose
//      tabular.transpose
//      Some(new Table(columns, tabular.map(line => line.filter(str => i.contains(line.indexOf(str))))))
//    }
  }

  def newCol(name: String, defaultVal: String): Table = {
    val default = tabular.transpose.head.foldLeft(Nil: List[String])((acc, name) => defaultVal :: acc)
    new Table(columnNames :+ name, (tabular.transpose :+ default).transpose)
  }

  def filter(cond: FilterCond) ={
    val filtered = tabular.map(row => columnNames.zip(row).foldLeft(Map[String, String]())(_ + _)).filter(p => {
      cond.eval(p) match {
        case None => false
        case Some(x: Boolean) => x
      }
    })

    filtered match {
      case Nil => None
      case _ => Some(new Table(columnNames, filtered.map(_.values.toList)))
    }

  }


}

object Table {
  // 1.2
  def apply(s: String): Table = {
    val res_split = s.split('\n').map(_.split(',').toList).toList
    new Table(res_split.head, res_split.tail)
  }
}


val csvContents: String =
  """Language,Object-Oriented,Functional
    |Haskell,false,true
    |Scala,true,true
    |COOL,true,false
    |Prolog,false,false""".stripMargin

Table(csvContents).newCol("ff", "bullshit")
Table(csvContents).filter(Field("Functional", _ == "true"))
