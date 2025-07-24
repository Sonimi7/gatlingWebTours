error id: file://<WORKSPACE>/src/test/scala/webTours/WebToursDebug.scala:
file://<WORKSPACE>/src/test/scala/webTours/WebToursDebug.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 0
uri: file://<WORKSPACE>/src/test/scala/webTours/WebToursDebug.scala
text:
```scala
@@package webTours

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.scenario.Simulation

class WebToursDebug extends Simulation{

    setUp(CommonScenario().inject(atOnceUsers(1)))
        .protocols(WebToursSimulation.httpProtocol)
  
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 