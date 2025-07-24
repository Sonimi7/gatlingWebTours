package webTours

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.structure.ChainBuilder

object ActionsWebTours {

  val getMainPage: ChainBuilder = exec(
    http("getMainPage")
      .get("/webtours/")
  )

  val login: ChainBuilder = exec(
    http("login")
      .post("")
      .formParam("username", "sofi")
      .formParam("password", "otus")
  )

}
