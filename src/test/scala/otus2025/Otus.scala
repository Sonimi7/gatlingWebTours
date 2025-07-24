package otus2025

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

object Otus {

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("https://vc.ru/")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")

}
