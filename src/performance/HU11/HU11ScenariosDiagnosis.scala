package dp2

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU11ScenariosDiagnosis extends Simulation {

	val httpProtocol = http
		.baseUrl("http://www.dp2.com")
		.inferHtmlResources(BlackList(""".*.ico""", """.*.css""", """.*.js""", """.*.png"""), WhiteList())
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
		.pause(5)
	}

		object Login{
		val login = exec(http("Login")
			.get("/login")
			.check(css("input[name=_csrf]", "value").saveAs("stoken")))
		.pause(17)
		
		.exec(http("Logged")
			.post("/login")
			.headers(headers_3)
			.formParam("username", "vet1")
			.formParam("password", "v3t")
			.formParam("_csrf", "${stoken}"))
		.pause(88)
		}
		
		object ShowVets{
		val showVets = exec(http("ShowVets")
			.get("/vets"))
		.pause(31)
		}

		object NewVaccine{
		val newVaccine = exec(http("NewVaccine")
			.get("/vets/newVaccine")
			.check(css("input[name=_csrf]", "value").saveAs("stoken")))
		.pause(22)
		.exec(http("VaccineAdded")
			.post("/vets/newVaccine")
			.headers(headers_2)
			.formParam("name", "ikkkkkkkiii")
			.formParam("components", "ikkkkkkkkkiii")
			.formParam("months", "5")
			.formParam("sickness", "1")
			.formParam("_csrf", "${stoken}"))
		.pause(16)
		}
		

	val scnHU11 = scenario("HU11").exec(Home.home,
									  Login.login,
									 ShowVets.showVets,
									 NewVaccine.newVaccine)

	setUp(scnHU11.inject(rampUsers(4000) during (100 seconds)))
    .protocols(httpProtocol)
    .assertions(
        global.responseTime.max.lt(5000),
        global.responseTime.mean.lt(1000),
        global.successfulRequests.percent.gt(95)
    )
}