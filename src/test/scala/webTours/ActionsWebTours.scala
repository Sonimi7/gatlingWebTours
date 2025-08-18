package webTours

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.structure.ChainBuilder

object ActionsWebTours {

// переход на главную страницу
  val getMainPage: ChainBuilder = exec(
    http("getMainPage")
      .get("/webtours/")
  )

// получение userSession
val getUserSession: ChainBuilder = exec(
  http("getUserSession")
    .get("/cgi-bin/nav.pl")
    .queryParam("in", "home")
    .check(
      regex("""<input type="hidden" name="userSession" value="([^"]+)"""").find.saveAs("userSessionVar")
    )
)
.exec { session =>
  val userSession = session("userSessionVar").asOption[String]
  println(s"Полученный userSession: $userSession")
  session
}

// логин
  val login: ChainBuilder = exec(
    http("login")
      .post("/cgi-bin/login.pl")
      .formParam("username", "${username}")
      .formParam("userSession", "${userSessionVar}")
      .formParam("login.x", "65")
      .formParam("login.y", "6")
      .formParam("JSFormSubmit", "off")
  )

// переход на страницу с рейсами
  val getPageFlights: ChainBuilder = exec(
    http("getPageFlights")
      .get("/cgi-bin/nav.pl")
      .queryParam("page", "menu")
      .queryParam("in", "flights")
      .queryParam("userSession", "${userSessionVar}")
  )

// переход на страницу с формой выбора рейсов
  val getFlightForm: ChainBuilder = exec(
  http("getFlightForm")
    .get("/cgi-bin/reservations.pl")
    .queryParam("page", "welcome")
    .queryParam("userSession", "${userSessionVar}")
    .check(
      regex("""<option.*?value="([^"]+)".*?>""").findAll.saveAs("cities")
    )
)

// выбор городов отправления и прибытия
val chooseCities: ChainBuilder = exec { session =>
  val cities = session("cities").as[Seq[String]]
  val rnd = new scala.util.Random
  val depart = cities(rnd.nextInt(cities.length))
  var arrive = depart
  // Если только один город, используем его для отправления и прибытия
  if (cities.length > 1) {
    while (arrive == depart) {
      arrive = cities(rnd.nextInt(cities.length))
    }
  }
  println(s"Выбранный город отправления: $depart")
  println(s"Выбранный город прибытия: $arrive")
  session
    .set("departCity", depart)
    .set("arriveCity", arrive)
}

//получение рейсов по выбранным городам
val findFlights: ChainBuilder = exec(
  http("findFlights")
    .post("/cgi-bin/reservations.pl")
    .formParam("userSession", "${userSessionVar}")
    .formParam("depart", "${departCity}")
    .formParam("arrive", "${arriveCity}")
    .formParam("advanceDiscount", "0")
    .formParam("departDate", "07/25/2025")
    .formParam("returnDate", "07/27/2025")
    .formParam("numPassengers", "1")
    .formParam("seatPref", "None")
    .formParam("seatType", "Coach")
    .formParam("findFlights.x", "59")
    .formParam("findFlights.y", "10")
    .formParam(".cgifields", "roundtrip")
    .formParam(".cgifields", "seatType")
    .formParam(".cgifields", "seatPref")
    .check(
      regex("""<input[^>]*?name="outboundFlight"[^>]*?value="([^"]+)"[^>]*?>""").findAll.saveAs("outboundFlights")
    )
)
.exec { session =>
  val flights = session("outboundFlights").asOption[Seq[String]].getOrElse(Seq.empty)
  println(s"Все найденные рейсы: ${flights.mkString(", ")}")
  val rnd = new scala.util.Random
  val chosenFlight = if (flights.nonEmpty) flights(rnd.nextInt(flights.length)) else ""
  println(s"Выбранный рейс: $chosenFlight")
  session.set("chosenFlight", chosenFlight)
}

// заполнение формы с деталями рейса
val flightsDetails: ChainBuilder = exec(
  http("flightsDetails")
    .post("/cgi-bin/reservations.pl")
    .formParam("userSession", "${userSessionVar}")
    .formParam("outboundFlight", "${chosenFlight}")
    .formParam("numPassengers", "1")
    .formParam("advanceDiscount", "0")
    .formParam("seatType", "Coach")
    .formParam("seatPref", "None")
    .formParam("reserveFlights.x", "59")
    .formParam("reserveFlights.y", "10")
)

// детали оплаты
val paymentDetails: ChainBuilder = exec(
  http("paymentDetails")
    .post("/cgi-bin/reservations.pl")
    .formParam("userSession", "${userSessionVar}")
    .formParam("firstName", "${firstName}")
    .formParam("lastName", "${lastName}")
    .formParam("address1", "${address1}")
    .formParam("address2", "${address2}")
    .formParam("pass1", "${pass1}")
    .formParam("creditCard", "${creditCard}")
    .formParam("expDate", "${expDate}")
    .formParam("saveCC", "on")
    .formParam("oldCCOption", "on")
    .formParam("numPassengers", "1")
    .formParam("seatType", "Coach")
    .formParam("seatPref", "None")
    .formParam("outboundFlight", "${chosenFlight}")
    .formParam("advanceDiscount", "0")
    .formParam("returnFlight", "")
    .formParam("JSFormSubmit", "off")
    .formParam("buyFlights.x", "61")
    .formParam("buyFlights.y", "12")
    .formParam(".cgifields", "saveCC")
    .check(status.is(200))
)

}
