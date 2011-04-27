/*
 * Copyright 2011 Kazuhiro Sera
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.github.seratch.yahooapis.setsuden.scala.response

import com.github.seratch.yahooapis.setsuden.exception.ClientException
import net.sf.json.JSONObject
import org.xml.sax.SAXException
import java.io.{ByteArrayInputStream, IOException}
import javax.xml.parsers.{SAXParserFactory, ParserConfigurationException}
import com.github.seratch.yahooapis.setsuden.fields.{Area, Output}
import com.github.seratch.yahooapis.setsuden.response.ElectricPowerForecastSAXHandler

import collection.JavaConversions._
import org.joda.time.DateTime

case class ElectricPowerForecastResponse(val statusCode: Int,
                                         val headers: Map[String, List[String]],
                                         val rawContent: String,
                                         val requestedOutput: Output = Output.xml) {

  def currentTime: DateTime = {
    requestedOutput match {
      case Output.xml => {
        val errorMessage = "Cannot parse xml data";
        try {
          val handler: ElectricPowerForecastSAXHandler = new ElectricPowerForecastSAXHandler
          val saxp = SAXParserFactory.newInstance.newSAXParser
          saxp.parse(new ByteArrayInputStream(rawContent.getBytes("UTF-8")), handler)
          return handler.getCurrentTime
        } catch {
          case e: ParserConfigurationException => throw new ClientException(errorMessage, e)
          case e: SAXException => throw new ClientException(errorMessage, e)
          case e: IOException => throw new ClientException(errorMessage, e)
        }
      }
      case Output.json => {
        val dateTimeString = JSONObject.fromObject(rawContent).getJSONObject("ElectricPowerForecasts").getString("CurrentTime")
        return com.github.seratch.yahooapis.setsuden.response.ElectricPowerForecast.getDateFormat.parseDateTime(dateTimeString)
      }
      case Output.php =>
        throw new UnsupportedOperationException("Currently unsupported")
      case _ =>
        throw new ClientException("Unexpected value:" + requestedOutput, new IllegalArgumentException)
    }
  }

  def updateTime: DateTime = {
    requestedOutput match {
      case Output.xml => {
        val errorMessage = "Cannot parse xml data";
        try {
          val handler: ElectricPowerForecastSAXHandler = new ElectricPowerForecastSAXHandler
          val saxp = SAXParserFactory.newInstance.newSAXParser
          saxp.parse(new ByteArrayInputStream(rawContent.getBytes("UTF-8")), handler)
          return handler.getUpdateTime
        } catch {
          case e: ParserConfigurationException => throw new ClientException(errorMessage, e)
          case e: SAXException => throw new ClientException(errorMessage, e)
          case e: IOException => throw new ClientException(errorMessage, e)
        }
      }
      case Output.json => {
        val dateTimeString = JSONObject.fromObject(rawContent).getJSONObject("ElectricPowerForecasts").getString("UpdateTime")
        return com.github.seratch.yahooapis.setsuden.response.ElectricPowerForecast.getDateFormat.parseDateTime(dateTimeString)
      }
      case Output.php =>
        throw new UnsupportedOperationException("Currently unsupported")
      case _ =>
        throw new ClientException("Unexpected value:" + requestedOutput, new IllegalArgumentException)
    }
  }

  def electricPowerForecasts: List[ElectricPowerForecast] = {
    requestedOutput match {
      case Output.xml => {
        val errorMessage = "Cannot parse xml data";
        try {
          val handler: ElectricPowerForecastSAXHandler = new ElectricPowerForecastSAXHandler
          val saxp = SAXParserFactory.newInstance.newSAXParser
          saxp.parse(new ByteArrayInputStream(rawContent.getBytes("UTF-8")), handler)
          for (
            javaObject <- handler.getElectricPowerForecasts.toList
          ) yield ElectricPowerForecast(
            area = javaObject.getArea,
            usageKilowattPerHour = javaObject.getUsageKilowattPerHour.longValue,
            capacityKilowattPerHour = javaObject.getCapacityKilowattPerHour.longValue,
            date = javaObject.getDate,
            hour = javaObject.getHour.intValue
          )
        } catch {
          case e: ParserConfigurationException => throw new ClientException(errorMessage, e)
          case e: SAXException => throw new ClientException(errorMessage, e)
          case e: IOException => throw new ClientException(errorMessage, e)
        }
      }
      case Output.json => {
        val jsonArray = JSONObject.fromObject(rawContent).getJSONObject("ElectricPowerForecasts").getJSONArray("Forecast")
        val seq = for (
          i <- 0 to (jsonArray.length - 1);
          json: JSONObject = jsonArray.getJSONObject(i)
        ) yield ElectricPowerForecast(
          area = Area.valueOf(json.getString("Area")),
          usageKilowattPerHour = json.getJSONObject("Usage").getLong("$").longValue,
          capacityKilowattPerHour = json.getJSONObject("Capacity").getLong("$").longValue,
          date = com.github.seratch.yahooapis.setsuden.response.ElectricPowerForecast.getDateFormat.parseDateTime(json.getString("Date")).toLocalDate,
          hour = json.getInt("Hour").intValue
        )
        seq.toList
      }
      case Output.php =>
        throw new UnsupportedOperationException("Currently unsupported")
      case _ =>
        throw new ClientException("Unexpected value:" + requestedOutput, new IllegalArgumentException)
    }
  }

  override def toString: String = {
    val buf = new StringBuilder
    buf.append("ElectricPowerForecastResponse [statusCode:")
    buf.append(statusCode)
    buf.append(",headers:")
    buf.append(headers.toString)
    buf.append(",rawContent:")
    buf.append(rawContent)
    buf.append("]")
    return buf.toString
  }

}
