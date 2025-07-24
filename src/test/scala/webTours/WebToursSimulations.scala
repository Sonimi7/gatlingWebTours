package webTours

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

object WebToursSimulation {
    
    val httpProtocol: HttpProtocolBuilder = http
        .baseUrl("http://webtours.load-test.ru:1080")
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
  
}
