package dp2

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HU14ScenariosDiagnosis extends Simulation {

	val httpProtocol = http
		.baseUrl("http://www.dp2.com")
		.inferHtmlResources(BlackList(""".*.png""", """.*.css""", """.*.js""", """.*.ico"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3")
		//.upgradeInsecureRequestsHeader("1")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:76.0) Gecko/20100101 Firefox/76.0")

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
		// Home
	}
	object Login {
		val login =exec(http("Login")
			.get("/login")
			.check(css("input[name=_csrf]", "value").saveAs("stoken")))
		.pause(12)
		.exec(http("Logged")
			.post("/login")
			.headers(headers_3)
			.formParam("username", "admin1")
			.formParam("password", "4dm1n")
			.formParam("_csrf", "${stoken}"))
		.pause(20)
	}
		
		object Clinics{
		val clinics = exec(http("Clinics")
			.get("/clinics")
			.headers(headers_0))
		.pause(11)
		}
		
		object ClinicCreate{
		val clinicCreate = exec(http("ClinicCreate")
			.get("/clinics/new")
			.check(css("input[name=_csrf]", "value").saveAs("stoken")))
		.pause(40)
		 .exec(http("ClinicCreated")
			.post("/clinics/save")
			.headers(headers_3)
			.formParam("id", "")
			.formParam("name", "okkkkkkkkkokokok")
			.formParam("city", "oooookokokoo")
			.formParam("email", "okkok@oko.com")
			.formParam("address", "okokokoko")
			.formParam("telephone", "88896")
			.formParam("_csrf", "${stoken}"))
		.pause(29)
		}
		
		

		val scnHU14 = scenario("HU14ScenariosDiagnosis").exec(Home.home,
									  Login.login,
									 // Clinics.clinics,
									  ClinicCreate.clinicCreate
									  )


	setUp(scnHU14.inject(rampUsers(5000) during (100 seconds)))
    .protocols(httpProtocol)
    .assertions(
        global.responseTime.max.lt(5000),
        global.responseTime.mean.lt(1000),
        global.successfulRequests.percent.gt(95)
    )
}