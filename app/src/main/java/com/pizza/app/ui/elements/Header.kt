package com.pizza.app.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.pizza.app.R
import com.pizza.app.ui.theme.Beige
import com.pizza.app.ui.theme.Black
import com.pizza.app.ui.theme.headerHeight
import com.pizza.app.ui.theme.iconSize
import com.pizza.app.ui.theme.lText
import com.pizza.app.ui.theme.mIconSize
import com.pizza.app.ui.theme.mPadding
import com.pizza.app.ui.theme.nonScaledSp
import com.pizza.app.ui.theme.sPadding
import com.pizza.app.utils.Const

@Composable
fun Header(
    logoutClick: () -> Unit,
    historyClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Beige)
            .height(headerHeight)
            .fillMaxWidth()
            .padding(mPadding)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    logoutClick()
                }
        ){
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = Const.IMAGE_DESCRIPTION,
                    tint = Black,
                    modifier = Modifier.size(mIconSize)
                )
                Text(
                    text = stringResource(id = R.string.logout),
                    fontSize = lText.nonScaledSp,
                    color = Black,
                    modifier = Modifier.padding(start = sPadding)
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.history),
                contentDescription = Const.IMAGE_DESCRIPTION,
                tint = Black,
                modifier = Modifier
                    .size(iconSize)
                    .clickable {
                        historyClick()
                    }
            )
        }
    }
}