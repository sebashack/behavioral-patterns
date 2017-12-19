package document

abstract class Element(val text: String) {
  def accept(visitor: Visitor)
}

class Title(text: String) extends Element(text) {
  def accept(visitor: Visitor): Unit = {
    visitor.visit(this)
  }
}

class Text(text: String) extends Element(text) {
  def accept(visitor: Visitor): Unit = {
    visitor.visit(this)
  }
}

class Hyperlink(text: String, val url: String) extends Element(text) {
  def accept(visitor: Visitor): Unit = {
    visitor.visit(this)
  }
}

class Document(parts: List[Element]) {
  def accept(visitor: Visitor): Unit = {
    parts.foreach(p => p.accept(visitor))
  }
}

trait Visitor {
  def visit(title: Title)
  def visit(title: Text)
  def visit(title: Hyperlink)
}


package html {
  class HtmlExporterVisitor extends Visitor {
    val line = System.getProperty("line.separator")
    val builder = new StringBuilder

    def getHtml(): String = builder.toString

    def visit(title: Title): Unit = {
      builder.append(s"<h1>${title.text}</h1>").append(line)
    }

    def visit(text: Text): Unit = {
      builder.append(s"<p>${text.text}</p>").append(line)
    }

    def visit(hyperlink: Hyperlink): Unit = {
      builder.append(s"""<a href=\"${hyperlink.url}\">${hyperlink.text}</a>""")
             .append(line)
    }
  }
}

package plain {
  class PlainTextExporterVisitor extends Visitor {
    val line = System.getProperty("line.separator")
    val builder = new StringBuilder
    def getText(): String = builder.toString

    def visit(title: Title): Unit = {
      builder.append(title.text).append(line)
    }

    def visit(text: Text): Unit = {
      builder.append(text.text).append(line)
    }

    def visit(hyperlink: Hyperlink): Unit = {
      builder.append(s"${hyperlink.text} (${hyperlink.url})").append(line)
    }
  }
}
