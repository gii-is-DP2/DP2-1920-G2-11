package dp2

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU8 extends Simulation {

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
		.pause(7)
	
	}	

	object Login {
		val login = exec(http("Login")
			.get("/login")
			.check(css("input[name=_csrf]", "value").saveAs("stoken")))
		.pause(10)
		.exec(http("Logged")
			.post("/login")
			.headers(headers_3)
			.formParam("username", "owner1")
			.formParam("password", "0wn3r")
			.formParam("_csrf", "${stoken}"))
		.pause(6)
	}
	object Clinics{
		val clinics = exec(http("Clinics")
			.get("/clinics")
			.headers(headers_0))
		.pause(16)
	}
	object ShowClinic{	
		val showClinic = exec(http("ShowClinic")
			.get("/clinics/2"))
		.pause(13)

	}	

	val scnHU8 = scenario("HU8").exec(Home.home,
									  Login.login,
									  Clinics.clinics,
									  ShowClinic.showClinic
									  )

	setUp(scnHU8.inject(rampUsers(7000) during (100 seconds)))
    .protocols(httpProtocol)
    .assertions(
        global.responseTime.max.lt(5000),
        global.responseTime.mean.lt(1000),
        global.successfulRequests.percent.gt(95)
    )
}