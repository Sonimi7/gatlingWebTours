error id: file://<WORKSPACE>/src/test/scala/webTours/CommonScenarioWebTours.scala:webTours/ActionsWebTours.
file://<WORKSPACE>/src/test/scala/webTours/CommonScenarioWebTours.scala
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol webTours/ActionsWebTours.
empty definition using fallback
non-local guesses:

offset: 274
uri: file://<WORKSPACE>/src/test/scala/webTours/CommonScenarioWebTours.scala
text:
```scala
package webTours

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

object CommonScenarioWebTours{

  def apply(): ScenarioBuilder = scenario("Common scenario")
    .exec(ActionsWebTours.getMainPage)
    .exec(@@ActionsWebTours.login)
    .exec(ActionsWebTours.getPageFlights)
    .exec(ActionsWebTours.getFlightForm)
    .exec(ActionsWebTours.chooseCities)
    .exec(ActionsWebTours.findFlights)
    .exec(ActionsWebTours.flightsDetails)
    .exec(ActionsWebTours.paymentDetails)
    .exec()
}


```


#### Short summary: 

empty definition using pc, found symbol in pc: 