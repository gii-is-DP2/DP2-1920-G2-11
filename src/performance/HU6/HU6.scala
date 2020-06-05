package dp2

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU6 extends Simulation {

	val httpProtocol = http
		.baseUrl("http://www.dp2.com")
		.inferHtmlResources(BlackList(""".*.css""", """.*.ico""", """.*.png""", """.*.js"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.9,en;q=0.8")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36")

	val headers_0 = Map(
		"Proxy-Connection" -> "keep-alive",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_2 = Map(
		"Accept" -> "image/webp,image/apng,image/*,*/*;q=0.8",
		"Proxy-Connection" -> "keep-alive")

	val headers_3 = Map(
		"Origin" -> "http://www.dp2.com",
		"Proxy-Connection" -> "keep-alive",
		"Upgrade-Insecure-Requests" -> "1")


object Home {
	val home = exec(http("Home")
			.get("/")
			.headers(headers_0))
		.pause(7)
	}

object Login {
	val login = exec(http("Login")
			.get("/login")
			.check(css("input[name=_csrf]", "value").saveAs("stoken")))
		.pause(9)
		.exec(http("Logged")
			.post("/login")
			.headers(headers_3)
			.formParam("username", "vet1")
			.formParam("password", "v3t")
			.formParam("_csrf", "${stoken}"))
		.pause(8)
	}

object FindOwnersForm {
	val findOwnersForm = exec(http("FindOwnersForm")
			.get("/owners/find")
			.headers(headers_0))
		.pause(19)
	}

object ShowOwners {
	val showOwners = exec(http("ShowOwners")
			.get("/owners?lastName=")
			.headers(headers_0))
		.pause(10)
	}

object ShowOwner {
	val showOwner = exec(http("ShowOwner")
			.get("/owners/1")
			.headers(headers_0))
		.pause(9)
	}

object SicknessesPetType {
	val sicknessesPetType = exec(http("SicknessesPetType")
			.get("/owners/*/pets/1/sicknesses?ownerId=1")
			.headers(headers_0))
		.pause(17)
	}
	
object SicknessDeleted {
	val sicknessDeleted = exec(http("SicknessDeleted")
			.get("/owners/*/pets/*/sicknesses/delete/8")
			.headers(headers_0))
		.pause(9)
	}

val scnHU6 = scenario("HU6").exec(Home.home,
								  Login.login,
								  SicknessDeleted.sicknessDeleted)


setUp(
		scnHU6.inject(rampUsers(7500) during (100 seconds))
		).protocols(httpProtocol)
		.assertions(
        global.responseTime.max.lt(5000),
        global.responseTime.mean.lt(1000),
        global.successfulRequests.percent.gt(95)
    )

}