package pro.savant.circumflex
package scalaconsole

import ru.circumflex._, core._, web._, freemarker._
import java.io.StringWriter

class ScalaConsoleRouter extends Router {

  get("/?") = ftl("/scalaconsole/index.ftl")

  post("/?").and(request.body.isXHR) = {
    val cmd = param("cmd").trim
    var result = "<div class=\"input\">" + escapeHtml(cmd) + "</div>"
    val console = getConsole
    dropBucket()
    import scala.tools.nsc._
    console.execute(cmd) match {
      case interpreter.Results.Error =>
        result += "<div class=\"error\">ERROR " +
            escapeHtml(getBucket.toString) +
            "</div>"
        dropBucket()
      case interpreter.Results.Success =>
        result += "<div class=\"success\">" +
            escapeHtml(getBucket.toString) +
            "</div>"
        dropBucket()
      case _ =>
    }
    send(result)
  }

  get("/reset") = ftl("/scalaconsole/reset.ftl")

  post("/reset") = {
    getConsole.imain.reset()
    getConsole.imain.close()
    session -= "scala.console"
    sendRedirect(prefix + "/scalaconsole")
  }

  def getConsole: ScalaConsole = session.get("scala.console") match {
    case Some(c: ScalaConsole) => c
    case _ =>
      val c = new ScalaConsole(() => getBucket)
      session += "scala.console" -> c
      c
  }

  def getBucket: StringWriter = session.get("scala.console.bucket") match {
    case Some(w: StringWriter) => w
    case _ =>
      val w = new StringWriter
      session += "scala.console.bucket" -> w
      w
  }

  def dropBucket() {
    session -= "scala.console.bucket"
  }

}