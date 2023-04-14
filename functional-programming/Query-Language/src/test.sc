val oop :String =
  """Language,Original purpose,Other paradigms
    |ActionScript,Application; client-side; web,prototype-based
    |Ada,Application; embedded; realtime; system,Concurrent; distributed
    |Aldor,Highly domain-specific; symbolic computing,
    |Ateji PX,Parallel application,pi calculus
    |APL,Application; data processing,Array-oriented; tacit
    |AutoHotkey,GUI automation (macros); highly domain-specific,
    |Ballerina,Integration; agile; server-side; general,Concurrent; transactional; statically and strongly typed programming; diagrammatic / visual programming
    |BeanShell,Application; scripting,
    |BlitzMax,Application; game,
    |Boo,Application; game scripting,
    |C++,Application; system,
    |C#,Application; RAD; business; client-side; general; server-side; web; game programming,Structured; concurrent
    |Clarion,General; business; web,
    |CLU,General,
    |COBOL,Application; business,
    |Cobra,Application; business; general; web,
    |ColdFusion (CFML),Web,
    |Common Lisp,General,Extensible syntax; Array-oriented; syntactic macros; multiple dispatch; concurrent
    |Crystal,General purpose,Alpha stage
    |Cython,Application; general; numerical computing,Aspect-oriented
    |D,Application; system,Generative; concurrent
    |Dart,Application; web; server-side; mobile; IoT,Structured
    |Dylan,Application,
    |Eiffel,General; application; business; client-side; server-side; web (EWF),Distributed SCOOP; Void-safe
    |F#,Application,
    |Fortran,Application; numerical computing,Array-based; vectorized; concurrent; native distributed/shared-memory parallelism
    |FreeBASIC,Application; numerical computing,
    |Gambas,Application,
    |Game Maker Language,Application; game programming,
    |GLBasic,Application; games,Simple object-oriented
    |Gosu,Application; general; scripting; web,
    |GraphTalk,Application,Logic
    |Groovy,Application; general; scripting; web,Meta-programming
    |Harbour,Application; business; data processing; general; web,Declarative
    |Haxe,Application; general; web,
    |HyperTalk,Application; RAD; general,Weakly typed
    |Io,Application; host-driven scripting,
    |ISLISP,General,
    |J,Application; data processing,Array-oriented; function-level; tacit; concurrent
    |JADE,Application; distributed,
    |Java,Application; business; client-side; general; mobile development; server-side; web,Concurrent
    |JavaScript,Client-side; server-side; web,prototype-based
    |Julia,General; technical computing,"Multiple dispatch; meta; scalar and array-oriented; parallel; concurrent; distributed (""cloud"")"
    |Kotlin,Application; mobile development; server-side; client-side; web,
    |Ksh,Shell; scripting,Several variants; custom programmable; dynamic loadable modules
    |LabVIEW (G),Application; industrial instrumentation-automation,Dataflow; visual
    |LiveCode,Application; RAD; general,Weakly typed
    |Logtalk,Artificial intelligence; application,Logic
    |Lua,Application; embedded scripting,Aspect-oriented; prototype-based
    |Maple,Symbolic computation; numerical computing,Distributed
    |Mathematica,Symbolic language,Logic; distributed
    |MATLAB,Highly domain-specific; numerical computing,
    |Modula-3,Application,
    |Nim,Application; general; web; scripting; system,Multiple dispatch; Concurrent; meta
    |Oberon,Application; system,
    |Object Pascal,Application; general; mobile app; web,Structured
    |Objective-C,Application; general,Concurrent
    |OCaml,Application; general,
    |OpenLisp,General; Embedded Lisp Engine,
    |Oxygene,Application,
    |Oz-Mozart,Application; distribution; education,Concurrent; logic
    |Perl,Application; scripting; text processing; Web,
    |PHP,Server-side; web application; web,
    |PL/I,Application,
    |PowerShell,Administration; application; general; scripting,Pipeline
    |Python,Application; general; web; scripting; artificial intelligence; scientific computing,Aspect-oriented
    |R,Application; statistics,
    |Racket,Education; general; scripting,Modular; logic; meta
    |Raku,Scripting; text processing; glue,Aspect-oriented; array; lazy evaluation; multiple dispatch; metaprogramming
    |Rebol,Distributed,Dialected
    |REXX,Scripting,
    |Ring,Application,metaprogramming; declarative; natural-language
    |Ruby,Application; scripting; web,Aspect-oriented
    |Rust,Application; server-side; system; web,Concurrent
    |S,Application; statistics,
    |Scala,Application; distributed; web,
    |Seed7,Application; general; scripting; web,Multi-paradigm; extensible; structured
    |Simula,Education; general,discrete event simulation; multi-threaded (quasi-parallel) program execution
    |Smalltalk,Application; general; business; artificial intelligence; education; web,Concurrent; declarative
    |Swift,Application; general,Concurrent; declarative; protocol-oriented
    |Tcl,Application; scripting; web,
    |Visual Basic,Application; RAD; education; business; general; (Includes VBA); office automation,Component-oriented
    |Visual Basic .NET,Application; RAD; education; web; business; general,Structured; concurrent
    |Visual FoxPro,Application,Data-centric; logic
    |Visual Prolog,Application,Declarative; logic
    |Wolfram Language,Symbolic language,Logic; distributed
    |Xojo,Application; RAD; general; web,""".stripMargin

val functional: String =
  """Language,Original purpose,Other paradigms
    |1C:Enterprise programming language,Application; RAD; business; general; web; mobile,Object-based; Prototype-based programming
    |ActionScript,Application; client-side; web,prototype-based
    |Aldor,Highly domain-specific; symbolic computing,
    |ALGOL 68,Application,Concurrent
    |APL,Application; data processing,Array-oriented; tacit
    |Ballerina,Integration; agile; server-side; general,Concurrent; transactional; statically and strongly typed programming; diagrammatic / visual programming
    |BeanShell,Application; scripting,
    |C++,Application; system,
    |C#,Application; RAD; business; client-side; general; server-side; web; game programming,Structured; concurrent
    |Clarion,General; business; web,
    |Clean,General,
    |Clojure,General,Concurrent
    |Cobra,Application; business; general; web,
    |Common Lisp,General,Extensible syntax; Array-oriented; syntactic macros; multiple dispatch; concurrent
    |Crystal,General purpose,Alpha stage
    |Curry,Application,lazy evaluation; non-determinism
    |Cython,Application; general; numerical computing,Aspect-oriented
    |D,Application; system,Generative; concurrent
    |Dart,Application; web; server-side; mobile; IoT,Structured
    |Dylan,Application,
    |Eiffel,General; application; business; client-side; server-side; web (EWF),Distributed SCOOP; Void-safe
    |Elixir,Application; distributed,Concurrent; distributed
    |Erlang,Application; distributed,Concurrent; distributed
    |Factor,General,Stack-oriented
    |FP,,
    |F#,Application,
    |Forth,General,Stack-oriented
    |Fortran,Application; numerical computing,Array-based; vectorized; concurrent; native distributed/shared-memory parallelism
    |Groovy,Application; general; scripting; web,Meta-programming
    |Harbour,Application; business; data processing; general; web,Declarative
    |Haskell,Application,Lazy evaluation
    |Haxe,Application; general; web,
    |IPL,General,
    |ISLISP,General,
    |J,Application; data processing,Array-oriented; function-level; tacit; concurrent
    |Java,Application; business; client-side; general; mobile development; server-side; web,Concurrent
    |JavaScript,Client-side; server-side; web,prototype-based
    |Joy,Research,Stack-oriented
    |jq,\"\"\"awk for JSON\"\"\",Tacit; Backtracking; Streaming; PEG
    |Julia,General; technical computing,"Multiple dispatch; meta; scalar and array-oriented; parallel; concurrent; distributed (""cloud"")"
    |Kotlin,Application; mobile development; server-side; client-side; web,
    |LabVIEW (G),Application; industrial instrumentation-automation,Dataflow; visual
    |Lisp,General,
    |Lua,Application; embedded scripting,Aspect-oriented; prototype-based
    |Maple,Symbolic computation; numerical computing,Distributed
    |Mathematica,Symbolic language,Logic; distributed
    |Nim,Application; general; web; scripting; system,Multiple dispatch; Concurrent; meta
    |OCaml,Application; general,
    |Opa,Web applications,Distributed
    |OpenLisp,General; Embedded Lisp Engine,
    |Oz-Mozart,Application; distribution; education,Concurrent; logic
    |Perl,Application; scripting; text processing; Web,
    |PHP,Server-side; web application; web,
    |PowerShell,Administration; application; general; scripting,Pipeline
    |Prolog,Application; artificial intelligence,Logic; declarative
    |Python,Application; general; web; scripting; artificial intelligence; scientific computing,Aspect-oriented
    |R,Application; statistics,
    |Racket,Education; general; scripting,Modular; logic; meta
    |Raku,Scripting; text processing; glue,Aspect-oriented; array; lazy evaluation; multiple dispatch; metaprogramming
    |Rebol,Distributed,Dialected
    |Ring,Application,metaprogramming; declarative; natural-language
    |Ruby,Application; scripting; web,Aspect-oriented
    |Rust,Application; server-side; system; web,Concurrent
    |S,Application; statistics,
    |Scala,Application; distributed; web,
    |Scheme,Education; general,meta; extensible-syntax
    |Smalltalk,Application; general; business; artificial intelligence; education; web,Concurrent; declarative
    |Standard ML,Application,
    |Swift,Application; general,Concurrent; declarative; protocol-oriented
    |Tcl,Application; scripting; web,
    |Visual Basic .NET,Application; RAD; education; web; business; general,Structured; concurrent
    |Visual Prolog,Application,Declarative; logic
    |Wolfram Language,Symbolic language,Logic; distributed
    |XPath/XQuery,Databases; data processing; scripting,Tree-oriented
    |Zig,Application; general; system,Concurrent""".stripMargin

val imperative: String =
"""Language,Original purpose,Other paradigms
  |1C:Enterprise programming language,Application; RAD; business; general; web; mobile,Object-based; Prototype-based programming
  |ActionScript,Application; client-side; web,prototype-based
  |Ada,Application; embedded; realtime; system,Concurrent; distributed
  |Aldor,Highly domain-specific; symbolic computing,
  |ALGOL 58,Application,
  |ALGOL 60,Application,
  |ALGOL 68,Application,Concurrent
  |APL,Application; data processing,Array-oriented; tacit
  |Assembly language,General,Any; syntax is usually highly specific; related to the target processor
  |AutoHotkey,GUI automation (macros); highly domain-specific,
  |AutoIt,GUI automation (macros); highly domain-specific,
  |Ballerina,Integration; agile; server-side; general,Concurrent; transactional; statically and strongly typed programming; diagrammatic / visual programming
  |Bash,Shell; scripting,
  |BASIC,Application; education,
  |BeanShell,Application; scripting,
  |BlitzMax,Application; game,
  |C,Application; system; general purpose; low-level operations,
  |C++,Application; system,
  |C#,Application; RAD; business; client-side; general; server-side; web; game programming,Structured; concurrent
  |Clarion,General; business; web,
  |CLU,General,
  |COBOL,Application; business,
  |Cobra,Application; business; general; web,
  |Common Lisp,General,Extensible syntax; Array-oriented; syntactic macros; multiple dispatch; concurrent
  |COMAL 80,Education,
  |Crystal,General purpose,Alpha stage
  |Cython,Application; general; numerical computing,Aspect-oriented
  |D,Application; system,Generative; concurrent
  |Dart,Application; web; server-side; mobile; IoT,Structured
  |Eiffel,General; application; business; client-side; server-side; web (EWF),Distributed SCOOP; Void-safe
  |ELAN,Education,Structured; stepwise refinement
  |Factor,General,Stack-oriented
  |F#,Application,
  |Forth,General,Stack-oriented
  |Fortran,Application; numerical computing,Array-based; vectorized; concurrent; native distributed/shared-memory parallelism
  |FreeBASIC,Application; numerical computing,
  |Gambas,Application,
  |Game Maker Language,Application; game programming,
  |GLBasic,Application; games,Simple object-oriented
  |Go,Application; web; server-side,Concurrent
  |Gosu,Application; general; scripting; web,
  |Groovy,Application; general; scripting; web,Meta-programming
  |Harbour,Application; business; data processing; general; web,Declarative
  |Haxe,Application; general; web,
  |Io,Application; host-driven scripting,
  |ISLISP,General,
  |J,Application; data processing,Array-oriented; function-level; tacit; concurrent
  |JADE,Application; distributed,
  |Java,Application; business; client-side; general; mobile development; server-side; web,Concurrent
  |JavaScript,Client-side; server-side; web,prototype-based
  |Julia,General; technical computing,"Multiple dispatch; meta; scalar and array-oriented; parallel; concurrent; distributed (""cloud"")"
  |Kotlin,Application; mobile development; server-side; client-side; web,
  |Ksh,Shell; scripting,Several variants; custom programmable; dynamic loadable modules
  |LabVIEW (G),Application; industrial instrumentation-automation,Dataflow; visual
  |Linden Scripting Language (LSL),Virtual worlds content scripting and animation,Scripts exist in in-world objects
  |Lua,Application; embedded scripting,Aspect-oriented; prototype-based
  |Maple,Symbolic computation; numerical computing,Distributed
  |Mathematica,Symbolic language,Logic; distributed
  |MATLAB,Highly domain-specific; numerical computing,
  |Modula-2,Application; system,
  |Modula-3,Application,
  |MUMPS (M),General; application; databases,Concurrent; multi-user; NoSQL; transaction processing
  |Nim,Application; general; web; scripting; system,Multiple dispatch; Concurrent; meta
  |Oberon,Application; system,
  |Object Pascal,Application; general; mobile app; web,Structured
  |Objective-C,Application; general,Concurrent
  |OCaml,Application; general,
  |Occam,General,Concurrent; process-oriented
  |Opa,Web applications,Distributed
  |OpenLisp,General; Embedded Lisp Engine,
  |Oxygene,Application,
  |Oz-Mozart,Application; distribution; education,Concurrent; logic
  |Pascal,Application; education,
  |Perl,Application; scripting; text processing; Web,
  |PHP,Server-side; web application; web,
  |PL/I,Application,
  |Plus,Application; system development,
  |PostScript,Graphics; page description,Concatenative; stack-oriented
  |PowerShell,Administration; application; general; scripting,Pipeline
  |Python,Application; general; web; scripting; artificial intelligence; scientific computing,Aspect-oriented
  |R,Application; statistics,
  |Raku,Scripting; text processing; glue,Aspect-oriented; array; lazy evaluation; multiple dispatch; metaprogramming
  |Rebol,Distributed,Dialected
  |REXX,Scripting,
  |RPG,Application; system,
  |Ring,Application,metaprogramming; declarative; natural-language
  |Ruby,Application; scripting; web,Aspect-oriented
  |Rust,Application; server-side; system; web,Concurrent
  |S,Application; statistics,
  |S-Lang,Application; numerical; scripting,
  |Scala,Application; distributed; web,
  |Scheme,Education; general,meta; extensible-syntax
  |Seed7,Application; general; scripting; web,Multi-paradigm; extensible; structured
  |Simula,Education; general,discrete event simulation; multi-threaded (quasi-parallel) program execution
  |Small Basic,Application; education; games,Component-oriented
  |Smalltalk,Application; general; business; artificial intelligence; education; web,Concurrent; declarative
  |Standard ML,Application,
  |Swift,Application; general,Concurrent; declarative; protocol-oriented
  |Tcl,Application; scripting; web,
  |Visual Basic,Application; RAD; education; business; general; (Includes VBA); office automation,Component-oriented
  |Visual Basic .NET,Application; RAD; education; web; business; general,Structured; concurrent
  |Visual Prolog,Application,Declarative; logic
  |Wolfram Language,Symbolic language,Logic; distributed
  |Xojo,Application; RAD; general; web,
  |Zeek,Domain-specific; application,
  |Zig,Application; general; system,Concurrent
  |Zsh,Shell; scripting,Loadable modules""".stripMargin

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
  def getColumnNames : Line = columnNames
  def getTabular : List[List[String]] = tabular

  // 1.1
  override def toString: String = {
    def addDelim(c: String)(elem: String, acc: String): String = {
      (acc match {
        case "" => elem match {
          case "" => " "
          case _ => elem
        }
        case _ => elem match {
          case "" => c + acc
          case _ => elem + c + acc
        }
      } ).stripLineEnd
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
      case _ => Some(new Table(columnNames, filtered.map(_.values.toList)))
    }
  }

  // 2.3.
  def newCol(name: String, defaultVal: String): Table = {
    val default = tabular.transpose.head.foldLeft(Nil: List[String])((acc, name) => defaultVal :: acc)
    new Table(columnNames :+ name, (tabular.transpose :+ default).transpose)
  }

  // 2.4.
  def merge(key: String, other: Table) = {
    if (!columnNames.contains(key) || !other.getColumnNames.contains(key)) None
    else {
      val colAddFirst = other.getColumnNames.filter(!columnNames.contains(_))
      val colAddSecond = columnNames.filter(!other.getColumnNames.contains(_))
      val newColumns = columnNames ++ colAddFirst

      val first_table = this.toMap.map((line:Map[String,String]) => colAddFirst.foldLeft(line)((acc, col) => acc + (col -> "")))
      val second_table = other.toMap.map((line:Map[String,String]) => colAddSecond.foldLeft(line)((acc, col) => acc + (col -> "")))
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
              case (x,y) => if (x != y) (col, x + ';' + y) else (col, y)
            }
            else (col, pair._1.getOrElse(col, col))
          }).foldLeft(Map[String, String]())((acc, elem) => acc + (elem._1 -> elem._2))
        })
      val newInformation = (valuesRemain ++ changedValues ++ valuesToAddFromSecond)
        .map(ht => newColumns.foldRight(Nil:List[String])((elem,acc) => ht.getOrElse(elem,elem) :: acc))

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


Table(oop).newCol("OOP", "Yes").merge("Language",Table(imperative).newCol("Imperative", "Yes"))
