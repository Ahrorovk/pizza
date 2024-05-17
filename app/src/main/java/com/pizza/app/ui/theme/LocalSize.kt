package com.pizza.app.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val text: Int  = 14
val lText: Int = 16
val xlText: Int = 24
val headerText: Int  = 90

val sPadding = 5.dp
val mPadding = 10.dp
val lPadding = 20.dp
val xlPadding = 30.dp

val mShape = 10.dp
val lShape = 20.dp

val mIconSize = 20.dp
val iconSize = 30.dp

//TextField
val textFieldHeight = 60.dp
val textFieldWidth = 280.dp

//Button
val buttonSize = 60.dp
val smallButtonWidth = 150.dp
val buttonHeight = 40.dp

//EditText
val borderSize = 2.dp

//Header
val headerHeight = 60.dp

val Int.nonScaledSp
    @Composable
    get() = (this / LocalDensity.current.fontScale).sp


