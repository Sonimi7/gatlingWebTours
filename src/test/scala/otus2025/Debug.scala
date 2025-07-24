package otus2025

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.scenario.Simulation

class Debug extends Simulation{

  setUp(CommonScenario().inject(atOnceUsers(1)))
    .protocols(Otus.httpProtocol)

}
