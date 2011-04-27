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
package com.github.seratch.yahooapis.setsuden.scala.request

import com.github.seratch.yahooapis.setsuden.fields.{Output, Area}

case class ElectricPowerUsageRequestParameters(val output: Output = Output.xml,
                                             val jsonpCallbackUrl: String = null,
                                             val area: Area = Area.tokyo,
                                             val yyyymmddhh: String = null) {

  override def toString: String = {
    val buf = new StringBuilder
    output match {
      case null =>
      case _ => {
        if (buf.length > 0) buf.append("&")
        buf.append("output=")
        buf.append(output.toString)
      }
    }
    jsonpCallbackUrl match {
      case null =>
      case _ => {
        if (buf.length > 0) buf.append("&")
        buf.append("callback=")
        buf.append(jsonpCallbackUrl)
      }
    }
    area match {
      case null =>
      case _ => {
        if (buf.length > 0) buf.append("&")
        buf.append("area=")
        buf.append(area.toString)
      }
    }
    yyyymmddhh match {
      case null =>
      case _ => {
        if (buf.length > 0) buf.append("&")
        buf.append("datetime=")
        buf.append(yyyymmddhh)
      }
    }
    return buf.toString
  }
}