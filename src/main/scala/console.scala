package pro.savant.circumflex
package scalaconsole

import ru.circumflex._, core._
import java.io.{PrintWriter, Writer, File, StringWriter}
import tools.nsc.Settings
import tools.nsc.interpreter.{Results, IMain}

class ScalaConsole(val bucketFunc: () => StringWriter) {

  val settings = new Settings()

  settings.classpath.value +=
      File.pathSeparator + System.getProperty("java.class.path") +
          File.pathSeparator + cx.getString("application.classpath").getOrElse("")
  settings.Yreplsync.value = true

  val writer = new Writer {
    def write(cbuf: Array[Char], off: Int, len: Int) {
      bucketFunc().write(cbuf, off, len)
    }
    def flush() {}
    def close() {}
  }

  var imain = new IMain(settings, new PrintWriter(writer))

  // Default imports

  imain.interpret("import ru.circumflex._, core._, web._, orm._")
  imain.interpret("import java.util.Date, java.io._")

  def execute(cmd: String): Results.Result = imain.interpret(cmd)

  def reset() {
    imain.reset()
    imain.close()
    imain = new IMain(settings, new PrintWriter(writer))
  }

}