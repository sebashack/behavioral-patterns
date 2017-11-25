package robot

case class Robot() {
  def cleanUp(): Unit = println("Clearning up")

  def pourJuice(): Unit = println("Pouring juice")

  def makeSandwich(): Unit = println("Making a sandwich")
}


package command {
  trait RobotCommand {
    def execute(): Unit
  }

  case class MakeSandwichCommand(robot: Robot) extends RobotCommand {
    def execute(): Unit = robot.makeSandwich()
  }

  case class PourJuiceCommand(robot: Robot) extends RobotCommand {
    def execute(): Unit = robot.pourJuice()
  }

  case class CleanUpCommand(robot: Robot) extends RobotCommand {
    def execute(): Unit = robot.cleanUp()
  }
}

package controller {
  import scala.collection.mutable._
  import command._

  class RobotController {
    val history = ListBuffer[RobotCommand]()

    def issueCommand(command: RobotCommand): Unit = {
      command +=: history
      command.execute()
    }

    def showHistory(): Unit = {
      history.foreach(println)
    }
  }
}
