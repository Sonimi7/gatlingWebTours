package webTours

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

object CommonScenarioWebTours{

  def apply(): ScenarioBuilder = scenario("Common scenario")
    .exec(ActionsWebTours.getMainPage)
    .exec(ActionsWebTours.login)
}

