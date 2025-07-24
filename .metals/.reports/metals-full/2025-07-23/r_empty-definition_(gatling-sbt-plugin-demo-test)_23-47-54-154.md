error id: file://<WORKSPACE>/src/test/scala/webTours/ActionsWebTours.scala:
file://<WORKSPACE>/src/test/scala/webTours/ActionsWebTours.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 501
uri: file://<WORKSPACE>/src/test/scala/webTours/ActionsWebTours.scala
text:
```scala
package webTours

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.structure.ChainBuilder

object ActionsWebTours {

  val getMainPage: ChainBuilder = exec(
    http("getMainPage")
      .get("/webtours/")
  )

  val login: ChainBuilder = exec(
    http("login")
      .post("")
      .formParam("username", "sofi")
      .formParam("password", "otus")
  )

  val getPageFlights: ChainBuilder = exec(
    http("getPageFlights")
      .get("/cgi-bin/nav.pl?page=me@@nu&in=flights")
      .queryParam("page", "sofi")
      .queryParam("password", "otus")
  )

}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 