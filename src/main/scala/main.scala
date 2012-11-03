package pro.savant.circumflex

import ru.circumflex._, core._, web._, freemarker._
import java.util.Date

class Main extends Router {
  val log = new Logger("pro.savant.circumflex")

  'currentDate := new Date

  get("/test") = "I'm fine, thanks!"
  get("/") = ftl("index.ftl")

}