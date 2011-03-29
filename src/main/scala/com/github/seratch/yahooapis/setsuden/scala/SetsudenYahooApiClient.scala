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
package com.github.seratch.yahooapis.setsuden.scala

import com.github.seratch.yahooapis.setsuden.scala.request.RequestParameters
import com.github.seratch.yahooapis.setsuden.scala.response.ElectricPowerUsageResponse
import com.github.seratch.yahooapis.setsuden.request.Urls
import java.net.{URL, HttpURLConnection}
import java.io.{InputStreamReader, BufferedReader, InputStream}

class SetsudenYahooApiClient(val applicationId: String) {

  val USER_AGENT = "SetsudenYahooApiClient HTTP Fetcher (+https://github.com/seratch/yahoo-setsuden-api-client)"

  def getLatestPowerUsage(params: RequestParameters): ElectricPowerUsageResponse = {

    val url = new StringBuilder
    url.append(Urls.LATEST_POWER_USAGE)
    url.append("?appid=")
    url.append(applicationId)
    url.append("&")
    url.append(params.toString)

    val conn = new URL(url.toString).openConnection.asInstanceOf[HttpURLConnection]
    conn.setConnectTimeout(3000)
    conn.setReadTimeout(10000)
    conn.setRequestProperty("User-Agent", USER_AGENT)
    conn.setRequestMethod("GET")

    conn.connect()

    import scala.collection.JavaConverters._

    val headersInJava = conn.getHeaderFields
    val headerMapBuffer = new collection.mutable.HashMap[String, List[String]]
    headersInJava.keySet.asScala foreach {
      case key =>
        headerMapBuffer.update(
          key,
          headersInJava.get(key).asScala.toList)
    }

    return ElectricPowerUsageResponse(
      statusCode = conn.getResponseCode.intValue,
      headers = headerMapBuffer.toMap,
      rawContent = getResponseCotent(conn, "UTF-8"),
      requestedOutput = params.output
    )

  }

  private[setsuden]
  def getResponseCotent(conn: HttpURLConnection, charset: String): String = {
    var is: InputStream = null
    var br: BufferedReader = null
    try {
      is = conn.getInputStream
      val isr = charset match {
        case null => new InputStreamReader(is)
        case _ => new InputStreamReader(is, charset)
      }
      br = new BufferedReader(isr)
      val buf = new StringBuilder
      var line: String = null
      while ( {
        line = br.readLine;
        line
      } != null) {
        buf.append(line)
        buf.append("\n")
      }
      return buf.toString
    }
    finally {
      is match {
        case null =>
        case _ => try is.close catch {
          case _ =>
        }
      }
      br match {
        case null =>
        case _ => try br.close catch {
          case _ =>
        }
      }
    }
  }

}