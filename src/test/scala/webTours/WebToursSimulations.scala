package webTours

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import scala.concurrent.duration._

object WebToursSimulation {
    
    val httpProtocol: HttpProtocolBuilder = http
        .baseUrl("http://webtours.load-test.ru:1080")
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
  
}

/**
 * Основная симуляция для тестирования WebTours
 * Используется для отладки и базового тестирования
 */
class WebToursSimulation extends Simulation {
  setUp(
    CommonScenarioWebTours()
      .inject(
        // Базовый тест с небольшим количеством пользователей
        rampUsersPerSec(0).to(5).during(30 seconds),
        constantUsersPerSec(5).during(2 minutes)
      )
  ).protocols(WebToursSimulation.httpProtocol)
}