package com.pizza.app.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.pizza.app.ui.theme.Beige
import com.pizza.app.ui.theme.Black
import com.pizza.app.ui.theme.White
import com.pizza.app.ui.theme.mShape
import com.pizza.app.ui.theme.textFieldHeight
import com.pizza.app.ui.theme.textFieldWidth

@Composable
fun EditText(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    password: Boolean = false,
    enabled: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    onClick: () -> Unit = {},
    onChange: (String) -> Unit,

    ) {
    TextField(
        value = text,
        label = { Text(hint, color = White) },
        onValueChange = onChange,
        visualTransformation = if (password) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.run {
            outlinedTextFieldColors(
                backgroundColor = Beige,
                focusedLabelColor = Black,
                unfocusedLabelColor = White,
                disabledTextColor = Black
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = ImeAction.Done),
        enabled = enabled,
        modifier = modifier
            .height(textFieldHeight)
            .width(textFieldWidth)
            .clip(RoundedCornerShape(mShape))
            .clickable { onClick() }
    )
}