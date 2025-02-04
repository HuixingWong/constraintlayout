/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.constraintlayout.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp


@Composable
fun ConstraintlayoutSample() {

    ConstraintLayout(
        constraintSet = MyConstraintSets()
    ) {
        Box(modifier = Modifier.size(200.dp).background(Color.Red).layoutId(Ref.A))
        Box(modifier = Modifier.size(200.dp).background(Color.Red).layoutId(Ref.B))
        Box(modifier = Modifier.size(200.dp).background(Color.Red).layoutId(Ref.C))
        Box(modifier = Modifier.size(200.dp).background(Color.Red).layoutId(Ref.D))
    }

}

@Composable
fun MyConstraintSets() = ConstraintSet {
    val A = createRefFor(Ref.A)
    val B = createRefFor(Ref.B)
    val C = createRefFor(Ref.C)
    val D = createRefFor(Ref.D)
    constrain(A) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
    }
    constrain(B) {
        top.linkTo(A.bottom)
        start.linkTo(A.end)
    }
    constrain(C) {
        top.linkTo(B.bottom)
        start.linkTo(B.end)
    }
    constrain(D) {
        top.linkTo(C.bottom)
        start.linkTo(C.end)
    }
}

object Ref {
    const val A = "A"
    const val B = "B"
    const val C = "C"
    const val D = "D"
}