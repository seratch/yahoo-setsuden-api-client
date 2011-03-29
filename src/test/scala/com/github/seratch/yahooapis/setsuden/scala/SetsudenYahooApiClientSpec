package com.github.seratch.yahooapis.setsuden.scala

import request.RequestParameters
import org.specs.Specification
import java.util.Properties
import com.github.seratch.yahooapis.setsuden.fields.Output

object SetsudenYahooApiClientSpec extends Specification {

  "SetsudenYahooApiClient" should {
    "be available" in {
      val props = new Properties
      try {
        props.load(SetsudenYahooApiClientSpec.getClass.getClassLoader.getResourceAsStream("yahoo-developer.properties"))
        val applicationId = props.getProperty("applicationId")
        val client = new SetsudenYahooApiClient(applicationId)
        val response = client.getLatestPowerUsage(RequestParameters(yyyymmddhh = "2011032901"))
        println(response.electricPowerUsage)
        response mustNotBe null
        response.statusCode mustEqual 200
        response.electricPowerUsage mustNotBe null
      }
      catch {
        case e: Exception => {
          e.printStackTrace
        }
      }
    }
    "be able to use specified parameters" in {
      val props = new Properties
      try {
        props.load(SetsudenYahooApiClientSpec.getClass.getClassLoader.getResourceAsStream("yahoo-developer.properties"))
        val client = new SetsudenYahooApiClient(props.getProperty("applicationId"))
        val params = new RequestParameters(yyyymmddhh = "2011032901", output = Output.json)
        val response = client.getLatestPowerUsage(params)
        response mustNotBe null
        response.statusCode mustEqual 200
        response.electricPowerUsage mustNotBe null
      }
      catch {
        case e: Exception => {
          e.printStackTrace
        }
      }
    }
  }

}