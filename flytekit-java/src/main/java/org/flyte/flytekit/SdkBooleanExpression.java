/*
 * Copyright 2021 Flyte Authors
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
package org.flyte.flytekit;

import com.google.auto.value.AutoOneOf;
import org.flyte.api.v1.ConjunctionExpression;

@AutoOneOf(SdkBooleanExpression.Kind.class)
public abstract class SdkBooleanExpression {
  enum Kind {
    CONJUNCTION,
    COMPARISON
  }

  abstract Kind kind();

  abstract SdkComparisonExpression comparison();

  abstract SdkConjunctionExpression conjunction();

  public SdkBooleanExpression and(SdkBooleanExpression other) {
    return ofConjunction(
        SdkConjunctionExpression.create(ConjunctionExpression.LogicalOperator.AND, this, other));
  }

  public SdkBooleanExpression or(SdkBooleanExpression other) {
    return ofConjunction(
        SdkConjunctionExpression.create(ConjunctionExpression.LogicalOperator.OR, this, other));
  }

  static SdkBooleanExpression ofComparison(SdkComparisonExpression comparison) {
    return AutoOneOf_SdkBooleanExpression.comparison(comparison);
  }

  static SdkBooleanExpression ofConjunction(SdkConjunctionExpression conjunction) {
    return AutoOneOf_SdkBooleanExpression.conjunction(conjunction);
  }
}
