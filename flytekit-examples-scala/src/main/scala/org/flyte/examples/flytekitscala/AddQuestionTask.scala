/*
 * Copyright 2021 Flyte Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.flyte.examples.flytekitscala

import org.flyte.flytekit.{SdkBindingData, SdkRunnableTask, SdkTransform}
import org.flyte.flytekitscala.SdkScalaType

case class AddQuestionTaskInput(greeting: String)
case class AddQuestionTaskOutput(greeting: String)

/** Example Flyte task that takes a greeting message as input, appends "How are
  * you?", and outputs the result.
  */
class AddQuestionTask
    extends SdkRunnableTask(
      SdkScalaType[AddQuestionTaskInput],
      SdkScalaType[AddQuestionTaskOutput]
    ) {

  /** Defines task behavior. This task takes a greeting message as the input,
    * append it with " How are you?", and outputs a new greeting message.
    *
    * @param input
    *   the greeting message
    * @return
    *   the updated greeting message
    */
  override def run(input: AddQuestionTaskInput): AddQuestionTaskOutput =
    AddQuestionTaskOutput(s"${input.greeting} How are you?")
}

object AddQuestionTask {

  /** Binds input data to this task
    *
    * @param greeting
    *   the input greeting message
    * @return
    *   a transformed instance of this class with input data
    */
  def apply(greeting: SdkBindingData): SdkTransform =
    new AddQuestionTask().withInput("greeting", greeting)
}
