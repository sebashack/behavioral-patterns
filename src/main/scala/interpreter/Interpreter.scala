package interpreter

trait Expression {
  def interpret(): Int
}

object Expression {
  def apply(operator: String, left: => Expression, right: => Expression)
      : Option[Expression] =
    operator match {
      case "+" => Some(new Add(right, left))
      case "-" => Some(new Subtract(right, left))
      case "*" => Some(new Multiply(right, left))
      case i if i.matches("\\d+") => Some(new Number(i.toInt))
      case _ => None
    }
}

class Number(n: Int) extends Expression {
  def interpret(): Int = n
}

class Add(right: Expression, left: Expression) extends Expression {
  def interpret(): Int = left.interpret() + right.interpret()
}

class Subtract(right: Expression, left: Expression) extends Expression {
  def interpret(): Int = left.interpret() - right.interpret()
}

class Multiply(right: Expression, left: Expression) extends Expression {
  def interpret(): Int = left.interpret() * right.interpret()
}

package rpn {
  package interpreter {
    class RPNInterpreter {
      def interpret(expression: Expression): Int =
        expression.interpret()
    }
  }
}
