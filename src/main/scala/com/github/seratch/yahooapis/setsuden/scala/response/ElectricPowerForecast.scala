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

import com.github.seratch.yahooapis.setsuden.fields.Area
import org.joda.time.LocalDate

case class ElectricPowerForecast(val area: Area = Area.tokyo,
                              val usageKilowattPerHour: Long,
                              val capacityKilowattPerHour: Long,
                              val date: LocalDate,
                              val hour: Int) {

  def this(javaObject: com.github.seratch.yahooapis.setsuden.response.ElectricPowerUsage) = {
    this (javaObject.getArea,
      javaObject.getUsageKilowattPerHour.longValue,
      javaObject.getCapacityKilowattPerHour.longValue,
      javaObject.getDate,
      javaObject.getHour.intValue)
  }

  override def toString: String = {
    val buf = new StringBuilder
    buf.append("ElectricPowerForecast [area:")
    buf.append(area.toString)
    buf.append(",usageKilowattPerHour:")
    buf.append(usageKilowattPerHour)
    buf.append(",capacityKilowattPerHour:")
    buf.append(capacityKilowattPerHour)
    buf.append(",date:")
    buf.append(date)
    buf.append(",hour:")
    buf.append(hour)
    buf.append("]")
    return buf.toString
  }

}