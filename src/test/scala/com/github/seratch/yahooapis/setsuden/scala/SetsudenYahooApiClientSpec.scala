package com.github.seratch.yahooapis.setsuden.scala

import org.specs.Specification
import java.util.Properties
import com.github.seratch.yahooapis.setsuden.fields.Output
import request.{ElectricPowerForecastRequestParameters, RequestParameters}

object SetsudenYahooApiClientSpec extends Specification {

  "SetsudenYahooApiClient" should {
    "be available to use getLatestPowerUsage" in {
      val props = new Properties
      try {
        props.load(SetsudenYahooApiClientSpec.getClass.getClassLoader.getResourceAsStream("yahoo-developer.properties"))
        val applicationId = props.getProperty("applicationId")
        val client = new SetsudenYahooApiClient(applicationId)
        val response = client.getLatestPowerUsage()
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
    "be able to use getLatestPowerUsage with specified parameters" in {
      val props = new Properties
      try {
        props.load(SetsudenYahooApiClientSpec.getClass.getClassLoader.getResourceAsStream("yahoo-developer.properties"))
        val client = new SetsudenYahooApiClient(props.getProperty("applicationId"))
        val response = client.getLatestPowerUsage(RequestParameters(yyyymmddhh = "2011032901", output = Output.json))
        response mustNotBe null
        response.statusCode mustEqual 200
        response.electricPowerUsage mustNotBe null
        println(response.electricPowerUsage)
      }
      catch {
        case e: Exception => {
          e.printStackTrace
        }
      }
    }
    "be able to use getLatestPowerUsage with specified parameters" in {
      val props = new Properties
      try {
        props.load(SetsudenYahooApiClientSpec.getClass.getClassLoader.getResourceAsStream("yahoo-developer.properties"))
        val client = new SetsudenYahooApiClient(props.getProperty("applicationId"))
        val response = client.getLatestPowerUsage(RequestParameters(yyyymmddhh = "2011032901"))
        response mustNotBe null
        response.statusCode mustEqual 200
        response.electricPowerUsage mustNotBe null
        println(response.electricPowerUsage)
      }
      catch {
        case e: Exception => {
          e.printStackTrace
        }
      }
    }
    "be available to use getPowerForecasts" in {
      val props = new Properties
      try {
        props.load(SetsudenYahooApiClientSpec.getClass.getClassLoader.getResourceAsStream("yahoo-developer.properties"))
        val applicationId = props.getProperty("applicationId")
        val client = new SetsudenYahooApiClient(applicationId)
        val response = client.getPowerForecasts()
        println(response.electricPowerForecasts)
        response mustNotBe null
        response.statusCode mustEqual 200
        response.electricPowerForecasts mustNotBe null
      }
      catch {
        case e: Exception => {
          e.printStackTrace
        }
      }
    }
    "be available to use getPowerForecasts with specified params" in {
      val props = new Properties
      try {
        props.load(SetsudenYahooApiClientSpec.getClass.getClassLoader.getResourceAsStream("yahoo-developer.properties"))
        val applicationId = props.getProperty("applicationId")
        val client = new SetsudenYahooApiClient(applicationId)
        val params = new ElectricPowerForecastRequestParameters(
          yyyymmdd = "20110429"
        )
        val response = client.getPowerForecasts(params)
        response.electricPowerForecasts foreach { each =>
          println(each.toString)
        }
        response mustNotBe null
        response.statusCode mustEqual 200
        response.electricPowerForecasts mustNotBe null
      }
      catch {
        case e: Exception => {
          e.printStackTrace
        }
      }
    }
  }

}