import util.Util.Row
import util.Util.Line
import TestTables.tableImperative
import TestTables.tableFunctional
import TestTables.tableObjectOriented

trait FilterCond {
  def &&(other: FilterCond): FilterCond = And(this, other)
  def ||(other: FilterCond): FilterCond = Or(this, other)
  // fails if the column name is not present in the row
  def eval(r: Row): Option[Boolean]
}
case class Field(colName: String, predicate: String => Boolean) extends FilterCond {
  // 2.2.
  override def eval(r: Row): Option[Boolean] = (r.getOrElse(colName, "")) match {
    case "" => None
    case t => Some(predicate(t))
  }
}

case class And(f1: FilterCond, f2: FilterCond) extends FilterCond {
  // 2.2.
  override def eval(r: Row): Option[Boolean] =
      (f1.eval(r), f2.eval(r)) match {
      case (_, None) => None
      case (None, _) => None
      case (Some(x), Some(y)) => Some(x && y)
    }
}

case class Or(f1: FilterCond, f2: FilterCond) extends FilterCond {
  // 2.2.
  override def eval(r: Row): Option[Boolean] =
      (f1.eval(r), f2.eval(r)) match {
      case (_, None) => None
      case (None, _) => None
      case (Some(x), Some(y)) => Some(x || y)
    }
}

trait Query {
  def eval: Option[Table]
}

/*
  Atom query which evaluates to the input table
  Always succeeds
 */
case class Value(t: Table) extends Query {
  override def eval: Option[Table] = ???
}
/*
  Selects certain columns from the result of a target query
  Fails with None if some rows are not present in the resulting table
 */
case class Select(columns: Line, target: Query) extends Query {
  override def eval: Option[Table] = ???
}

/*
  Filters rows from the result of the target query
  Success depends only on the success of the target
 */
case class Filter(condition: FilterCond, target: Query) extends Query {
  override def eval: Option[Table] = ???
}

/*
  Creates a new column with default values
  Success depends only on the success of the target
 */
case class NewCol(name: String, defaultVal: String, target: Query) extends Query {
  override def eval: Option[Table] = ???
}

/*
  Combines two tables based on a common key
  Success depends on whether the key exists in both tables or not AND on the success of the target
 */
case class Merge(key: String, t1: Query, t2: Query) extends Query {
  override def eval: Option[Table] = ???
}


class Table (columnNames: Line, tabular: List[List[String]]) {
  def getColumnNames : Line = columnNames
  def getTabular : List[List[String]] = tabular

  // 1.1
  override def toString: String = {
    def addDelim(c: String)(elem: String, acc: String): String =
      acc match {
        case "" => elem
        case _ => elem + c + acc
      }

    (columnNames :: tabular).map(_.foldRight("")(addDelim(",")))
      .foldRight("")(addDelim("\n"))
  }

  // 2.1
  def select(columns: Line): Option[Table] = {
    val check = columns.foldRight(true)((name, acc) => columnNames.contains(name) & acc)
    if (!check) None
    else {
      val columnsToPick = columns.map(columnNames.indexOf(_))
      val trans = tabular.transpose
      val newTabular = columnsToPick.foldRight(Nil: List[Line])((idx, acc) => trans(idx) :: acc).transpose
      Some(new Table(columns, newTabular))
    }
  }

  // 2.2
  def filter(cond: FilterCond): Option[Table] =
    {
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

  // 2.3.
  def newCol(name: String, defaultVal: String): Table = {
    val default = tabular.transpose.head.foldLeft(Nil: List[String])((acc, name) => defaultVal :: acc)
    new Table(columnNames :+ name, (tabular.transpose :+ default).transpose)
  }

  // 2.4.
  def merge(key: String, other: Table): Option[Table] = ???
}

object Table {
  // 1.2
  def apply(s: String): Table = {
    val res_split = s.split('\n').map(_.split(',').toList).toList
    new Table(res_split.head, res_split.tail)
  }
}
