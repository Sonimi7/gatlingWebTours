package webTours

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class WebToursDebug extends Simulation {

    setUp(CommonScenarioWebTours().inject(atOnceUsers(1)))
        .protocols(WebToursSimulation.httpProtocol)

}
