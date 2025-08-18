package webTours

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

object CommonScenarioWebTours{
  val userFeeder = csv("users.csv").circular

  def apply(): ScenarioBuilder = scenario("WebTours Flight Booking")
    .feed(userFeeder)
    .exec(ActionsWebTours.getMainPage) // Открытие главной страницы
    .exec(ActionsWebTours.getUserSession)
    .exec(ActionsWebTours.login)
    .exec(ActionsWebTours.getPageFlights)
    .exec(ActionsWebTours.getFlightForm)
    .exec(ActionsWebTours.chooseCities)
    .exec(ActionsWebTours.findFlights)
    .exec(ActionsWebTours.flightsDetails)
    .exec(ActionsWebTours.paymentDetails)
    .exec(ActionsWebTours.getMainPage) // Переход на корневую страницу (требование задания)
}

