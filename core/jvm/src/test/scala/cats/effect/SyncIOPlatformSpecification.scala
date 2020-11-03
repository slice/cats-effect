/*
 * Copyright 2020 Typelevel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cats.effect

import org.specs2.mutable.Specification

abstract class SyncIOPlatformSpecification extends Specification with Runners {
  def platformSpecs = {
    "platform" should {
      "now should return an Instant constructed from realTime" in {
        // Unfortunately since SyncIO doesn't use on a controllable
        // clock source, so a diff best we can do
        val op = for {
          realTime <- SyncIO.realTime
          now <- SyncIO.now
        } yield (now.toEpochMilli - realTime.toMillis) <= 30

        op must completeAsSync(true)
      }
    }
  }

}
