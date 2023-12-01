import java.util.Calendar
trait Quote extends ILogger {
  abstract override def mixMessage(s: String) = {
    val message = s"\"$s\""
    return super.mixMessage(message)
  }
}

trait TimeStamp extends ILogger {
  abstract override def mixMessage(s: String) = {
    val today = Calendar.getInstance().getTime()
    val message = s"$today $s"
    return super.mixMessage(message)
  }
}

trait Severity extends ILogger {
  abstract override def pprint(s: String): Unit = {
    super.pprint(s)
  }

  abstract override def mixMessage(s: String): String = {
    return super.mixMessage(s)
  }

  def warning(s: String): Unit = {
    pprint(mixMessage(s"WARN $s"))
  }

  def error(s: String): Unit = {
    pprint(mixMessage(s"ERROR $s"))
  }

  def info(s: String): Unit = {
    pprint(mixMessage(s"INFO $s"))
  }

  def debug(s: String): Unit = {
    pprint(mixMessage(s"DEBUG $s"))
  }

}

abstract class ILogger {
  def pprint(s: String): Unit

  def mixMessage(s: String): String
}

class Logger extends ILogger {
  override def pprint(s: String) = {
    print(mixMessage(s))
  }

  def mixMessage(s: String): String = s

}

@main def hello: Unit = {
  val l = new Logger with Quote with TimeStamp with Severity
  l.pprint("Hello world")
  println()

  l.warning("something is wrong")
  println()

  val ql = new Logger with Quote
  ql.pprint("No date, just quote")
  println()

  val tl = new Logger with TimeStamp
  tl.pprint("some time stamped message")
  println()

  val plain = new Logger
  plain.pprint("just the text")
  println()

}
