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
import com.github.seratch.yahooapis.setsuden.response.ElectricPowerUsageSAXHandler
import java.io.{ByteArrayInputStream, IOException}
import javax.xml.parsers.{SAXParserFactory, ParserConfigurationException}
import com.github.seratch.yahooapis.setsuden.fields.{Area, Output}

case class ElectricPowerUsageResponse(val statusCode: Int,
                                      val headers: Map[String, List[String]],
                                      val rawContent: String,
                                      val requestedOutput: Output = Output.xml) {

  def electricPowerUsage: ElectricPowerUsage = {
    requestedOutput match {
      case Output.xml => {
        val errorMessage = "Cannot parse xml data";
        try {
          val handler: ElectricPowerUsageSAXHandler = new ElectricPowerUsageSAXHandler
          val saxp = SAXParserFactory.newInstance.newSAXParser
          saxp.parse(new ByteArrayInputStream(rawContent.getBytes("UTF-8")), handler)
          return new ElectricPowerUsage(handler.getElectricPowerUsage)
        } catch {
          case e: ParserConfigurationException => throw new ClientException(errorMessage, e)
          case e: SAXException => throw new ClientException(errorMessage, e)
          case e: IOException => throw new ClientException(errorMessage, e)
        }
      }
      case Output.json => {
        val json: JSONObject = JSONObject.fromObject(rawContent).getJSONObject("ElectricPowerUsage")
        val javaObject = new com.github.seratch.yahooapis.setsuden.response.ElectricPowerUsage
        return ElectricPowerUsage(
          area = Area.valueOf(json.getString("Area")),
          usageKilowattPerHour = json.getJSONObject("Usage").getLong("$").longValue,
          capacityKilowattPerHour = json.getJSONObject("Capacity").getLong("$").longValue,
          date = javaObject.getDateFormat.parseDateTime(json.getString("Date")).toLocalDate,
          hour = json.getInt("Hour").intValue
        )
      }
      case Output.php =>
        throw new UnsupportedOperationException("Currently unsupported")
      case _ =>
        throw new ClientException("Unexpected value:" + requestedOutput, new IllegalArgumentException)
    }
  }

  override def toString: String = {
    val buf = new StringBuilder
    buf.append("ElectricPowerUsageResponse [statusCode:")
    buf.append(statusCode)
    buf.append(",headers:")
    buf.append(headers.toString)
    buf.append(",rawContent:")
    buf.append(rawContent)
    buf.append("]")
    return buf.toString
  }

}
