package util

package person {
  case class Person(name: String, age: Int, address: String)
}


package parser {
  import util.person._

  trait Parser[T] {
    def parse(file: String): List[T]
  }

  package csv {
    import com.github.tototoshi.csv._
    import java.io.{ InputStreamReader }

    class CSVParser extends Parser[Person] {
      def parse(file: String): List[Person] = {
        val reader =
          new InputStreamReader(this.getClass.getResourceAsStream(file))
        CSVReader.open(reader).all().map {
          case List(name, age, address) => Person(name, age.toInt, address)
        }
      }

    }
  }

  package json {
    import org.json4s._
    import org.json4s.jackson._

    class JsonParser extends Parser[Person] {
      implicit val formats = DefaultFormats

      def parse(file: String): List[Person] = {
        val reader =
          new StreamInput(this.getClass.getResourceAsStream(file))
        JsonMethods.parse(reader).extract[List[Person]]
      }
    }

  }
}
