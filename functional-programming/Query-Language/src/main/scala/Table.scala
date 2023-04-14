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
  override def eval: Option[Table] = Some(t)
}
/*
  Selects certain columns from the result of a target query
  Fails with None if some rows are not present in the resulting table
 */
case class Select(columns: Line, target: Query) extends Query {
  override def eval: Option[Table] = target.eval match {
    case Some(t) => t.select(columns)
    case None => None
  }
}

/*
  Filters rows from the result of the target query
  Success depends only on the success of the target
 */
case class Filter(condition: FilterCond, target: Query) extends Query {
  override def eval: Option[Table] = target.eval match {
    case Some(t) => t.filter(condition)
    case None => None
  }
}

/*
  Creates a new column with default values
  Success depends only on the success of the target
 */
case class NewCol(name: String, defaultVal: String, target: Query) extends Query {
  override def eval: Option[Table] = target.eval match {
    case Some(t) => Some(t.newCol(name, defaultVal))
    case None => None
  }
}

/*
  Combines two tables based on a common key
  Success depends on whether the key exists in both tables or not AND on the success of the target
 */
case class Merge(key: String, t1: Query, t2: Query) extends Query {
  override def eval: Option[Table] = (t1.eval, t2.eval) match {
    case (_, None) => None
    case (None, _) => None
    case (Some(tab1), Some(tab2)) => tab1.merge(key, tab2)
  }
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

  def toMap: List[Map[String, String]] =
    tabular.map(row => columnNames.zip(row).foldLeft(Map[String, String]())(_ + _))

  // 2.2
  def filter(cond: FilterCond): Option[Table] = {
      val filtered = this.toMap.filter(p => {
        cond.eval(p) match {
          case None => false
          case Some(x: Boolean) => x
        }
      })

      filtered match {
        case Nil => None
        case _ => Some(new Table(columnNames, filtered.map(ht => columnNames.foldRight(Nil: List[String])((elem, acc) => ht.getOrElse(elem, elem) :: acc))))
      }
    }

  // 2.3.
  def newCol(name: String, defaultVal: String): Table = {
    val default = tabular.transpose.head.foldLeft(Nil: List[String])((acc, name) => defaultVal :: acc)
    new Table(columnNames :+ name, (tabular.transpose :+ default).transpose)
  }

  // 2.4.
  def merge(key: String, other: Table): Option[Table] = {
    if (!columnNames.contains(key) || !other.getColumnNames.contains(key)) None
    else {
      val colAddFirst = other.getColumnNames.filter(!columnNames.contains(_))
      val colAddSecond = columnNames.filter(!other.getColumnNames.contains(_))
      val newColumns = columnNames ++ colAddFirst

      val first_table = this.toMap.map((line: Map[String, String]) => colAddFirst.foldLeft(line)((acc, col) => acc + (col -> "")))
      val second_table = other.toMap.map((line: Map[String, String]) => colAddSecond.foldLeft(line)((acc, col) => acc + (col -> "")))
      val firstIndexList = first_table.map(_.getOrElse(key, ""))
      val secondIndexList = second_table.map(_.getOrElse(key, ""))

      val valuesToAddFromSecond = secondIndexList.filter(!firstIndexList.contains(_))
        .map(value => second_table(secondIndexList.indexOf(value)))
      val valuesRemain = firstIndexList.filter(!secondIndexList.contains(_))
        .map(value => first_table(firstIndexList.indexOf(value)))

      val changedValues = secondIndexList.filter(firstIndexList.contains(_))
        .map(value => (first_table(firstIndexList.indexOf(value)), second_table(secondIndexList.indexOf(value))))
        .map(pair => {
          newColumns.map(col => {
            if (col != key) (pair._1.getOrElse(col, col), pair._2.getOrElse(col, col)) match {
              case ("", u) => (col, u)
              case (s, "") => (col, s)
              case (x, y) => if (x != y) (col, x + ';' + y) else (col, y)
            }
            else (col, pair._1.getOrElse(col, col))
          }).foldLeft(Map[String, String]())((acc, elem) => acc + (elem._1 -> elem._2))
        })
      val newInformation = (valuesRemain ++ changedValues ++ valuesToAddFromSecond)
        .map(ht => newColumns.foldRight(Nil: List[String])((elem, acc) => ht.getOrElse(elem, elem) :: acc))

      Some(new Table(newColumns, newInformation))
    }
  }
}

object Table {
  // 1.2
  def apply(s: String): Table = {
    val res_split = s.split('\n').map(_.split(',').toList).toList
    val ss = res_split.tail.map(line => if (line.size < res_split.head.size) line ++ List.fill(res_split.head.size - line.size)("") else line)
    new Table(res_split.head, ss)
  }
}
