package player

trait State[T] {
  def press(context: T)
}

class Playing extends State[MediaPlayer] {
  def press(context: MediaPlayer): Unit = {
    println("Pressing pause.")
    context.setState(new Paused)
  }
}

class Paused extends State[MediaPlayer] {
  def press(context: MediaPlayer): Unit = {
    println("Pressing play.")
    context.setState(new Playing)
  }
}

case class MediaPlayer() {
  private var state: State[MediaPlayer] = new Paused

  def pressPlayOrPauseButton(): Unit = {
    state.press(this)
  }

  def setState(state: State[MediaPlayer]): Unit = {
    this.state = state
  }
}
