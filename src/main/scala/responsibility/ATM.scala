package atm

package money {
  case class Money(amount: Int)
}

package dispenser {
  import atm.money._

  trait Dispenser {
    val amount: Int
    val next: Option[Dispenser]

    def dispense(money: Money): Unit = {
      if (money.amount >= amount) {
        val notes = money.amount / amount
        val left = money.amount % amount
        println(s"Dispensing $notes note/s of $amount.")
        if (left > 0) next.foreach(_.dispense(Money(left)))
      } else {
        next.foreach(_.dispense(money))
      }

    }
  }

  class Dispenser50(val next: Option[Dispenser]) extends Dispenser {
    val amount = 50
  }

  class Dispenser20(val next: Option[Dispenser]) extends Dispenser {
    val amount = 20
  }

  class Dispenser10(val next: Option[Dispenser]) extends Dispenser {
    val amount = 10
  }

  class Dispenser5(val next: Option[Dispenser]) extends Dispenser {
    val amount = 5
  }

  class ATM {
    val dispenser: Dispenser = {
      val d1 = new Dispenser5(None)
      val d2 = new Dispenser10(Some(d1))
      val d3 = new Dispenser20(Some(d2))
      new Dispenser50(Some(d3))
    }

    def requestMoney(money: Money): Unit = {
      if (money.amount % 5 != 0) {
        println("The smallest nominal is 5 and we cannot satisfy your request.")
      } else {
        dispenser.dispense(money)
      }
    }
  }
}
