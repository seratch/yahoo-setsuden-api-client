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

import com.github.seratch.yahooapis.setsuden.fields._

case class RequestParameters(val output: Output = null,
                        val jsonpCallbackUrl: String = null,
                        val area: Area = Area.tokyo,
                        val yyyymmddhh: String = null) {

  override def toString: String = {
    val buf = new StringBuilder
    if (output != null) {
      if (buf.length > 0) buf.append("&")
      buf.append("output=")
      buf.append(output.toString)
    }
    if (jsonpCallbackUrl != null) {
      if (buf.length > 0) buf.append("&")
      buf.append("callback=")
      buf.append(jsonpCallbackUrl)
    }
    if (area != null) {
      if (buf.length > 0) buf.append("&")
      buf.append("area")
      buf.append(area.toString)
    }
    if (yyyymmddhh != null) {
      if (buf.length > 0) buf.append("&")
      buf.append("datetime=")
      buf.append(yyyymmddhh)
    }
    return buf.toString
  }

}