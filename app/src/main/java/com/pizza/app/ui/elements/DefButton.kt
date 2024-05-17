package com.pizza.app.ui.elements

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pizza.app.ui.theme.Peru
import com.pizza.app.ui.theme.White
import com.pizza.app.ui.theme.buttonHeight
import com.pizza.app.ui.theme.smallButtonWidth

@Composable
fun DefButton(
    text: String,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = White,
        contentColor = Peru,
    ),
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = colors,
        modifier = modifier
            .width(smallButtonWidth)
            .height(buttonHeight)
    ) {
        Text(text)
    }
}