package writer

import util.parser.{ Parser }

class PersonApplication[T](parser: Parser[T]) {
  def write(file: String): Unit = {
    parser.parse(file)
  }
}
