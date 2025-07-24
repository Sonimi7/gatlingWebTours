error id: file://<WORKSPACE>/src/test/scala/webTours/WebToursSimulations.scala:
file://<WORKSPACE>/src/test/scala/webTours/WebToursSimulations.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -io/gatling/core/Predef.minutes.
	 -io/gatling/core/Predef.minutes#
	 -io/gatling/core/Predef.minutes().
	 -io/gatling/http/Predef.minutes.
	 -io/gatling/http/Predef.minutes#
	 -io/gatling/http/Predef.minutes().
	 -minutes.
	 -minutes#
	 -minutes().
	 -scala/Predef.minutes.
	 -scala/Predef.minutes#
	 -scala/Predef.minutes().
offset: 552
uri: file://<WORKSPACE>/src/test/scala/webTours/WebToursSimulations.scala
text:
```scala
package webTours

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

object WebToursSimulation {
    
    val httpProtocol: HttpProtocolBuilder = http
        .baseUrl("http://webtours.load-test.ru:1080")
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
  
}

class WebToursSimulation extends Simulation {
  setUp(
    CommonScenarioWebTours()
      .inject(
        incrementUsersPerSec(10)
          .times(10)
          .eachLevelLasting(2 min@@utes)
          .startingFrom(0)
      )
  ).protocols(WebToursSimulation.httpProtocol)
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: 