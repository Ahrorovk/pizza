package com.pizza.app.ui.screen.basket

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pizza.app.R
import com.pizza.app.ui.elements.EditText
import com.pizza.app.ui.elements.Header
import com.pizza.app.ui.navigation.Screen
import com.pizza.app.ui.screen.main.MainViewModel
import com.pizza.app.ui.theme.Beige
import com.pizza.app.ui.theme.Black
import com.pizza.app.ui.theme.DarkBlue
import com.pizza.app.ui.elements.DefButton
import com.pizza.app.ui.navigation.EnterAnimation
import com.pizza.app.ui.screen.main.HistoryItem
import com.pizza.app.ui.screen.registration.RegView
import com.pizza.app.ui.theme.Red
import com.pizza.app.ui.theme.White
import com.pizza.app.ui.theme.borderSize
import com.pizza.app.ui.theme.lPadding
import com.pizza.app.ui.theme.lText
import com.pizza.app.ui.theme.mPadding
import com.pizza.app.ui.theme.mShape
import com.pizza.app.ui.theme.nonScaledSp
import com.pizza.app.ui.theme.xlText
import com.pizza.app.utils.BackPressHandler
import com.pizza.app.utils.Const
import com.pizza.app.utils.ToastHelper
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun BasketView(
    mainViewModel: MainViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    AnimatedVisibility(visible = true) {
        UI(mainViewModel,
            payClick = {
                ToastHelper().show(context, context.getString(R.string.toastSuccess))
                navController.popBackStack()
            },
            logoutClick = {
                navController.popBackStack()
            },
            historyClick = {
                navController.navigate(Screen.History.name)
            }
        )
    }
    BackPressHandler {
        navController.popBackStack()
    }
}

@Composable
private fun UI(
    mainViewModel: MainViewModel,
    payClick: () -> Unit,
    logoutClick: () -> Unit,
    historyClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(DarkBlue)
            .fillMaxSize()
    ) {
        Header(
            logoutClick = logoutClick,
            historyClick = historyClick
        )
        Body(
            mainViewModel,
            payClick = payClick
        )
    }
}

@Composable
private fun Body(
    mainViewModel: MainViewModel,
    payClick: () -> Unit,
) {
    val context = LocalContext.current
    var address by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    val pizzaParam = mainViewModel.selectedPizzaParam.collectAsState().value
    var payType by remember { mutableIntStateOf(0) }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Text(
                text = stringResource(id = R.string.payed),
                fontSize = xlText.nonScaledSp,
                color = White,
                modifier = Modifier.padding(top = mPadding)
            )

            Column(
                modifier = Modifier
                    .padding(lPadding)
                    .background(Beige, RoundedCornerShape(mShape))
                    .fillMaxWidth()
                    .padding(lPadding)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painterResource(id = pizzaParam.imageId),
                        contentDescription = Const.IMAGE_DESCRIPTION,
                        modifier = Modifier
                            .clip(RoundedCornerShape(100.dp))
                            .width(150.dp)
                    )
                    Column {
                        Text(
                            text = pizzaParam.name,
                            fontSize = lText.nonScaledSp,
                            color = Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = mPadding)
                                .fillMaxWidth()
                        )
                        Text(
                            text = pizzaParam.category,
                            fontSize = lText.nonScaledSp,
                            color = Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = mPadding)
                                .fillMaxWidth()
                        )
                        Text(
                            text = "${pizzaParam.weight}г",
                            fontSize = lText.nonScaledSp,
                            color = Black,
                            modifier = Modifier
                                .padding(top = mPadding)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "${pizzaParam.price}р",
                            fontSize = lText.nonScaledSp,
                            color = Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = mPadding)
                                .fillMaxWidth()
                        )
                    }
                }
            }

            EditText(
                text = address,
                hint = stringResource(id = R.string.enterAddress),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = lPadding, end = lPadding)
            ) {
                address = it
            }
            EditText(
                text = phone,
                hint = stringResource(id = R.string.contactPhone),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = lPadding, top = lPadding, end = lPadding),
                keyboardType = KeyboardType.Number
            ) {
                phone = it
            }

            Text(
                text = stringResource(id = R.string.choicePayType),
                fontSize = lText.nonScaledSp,
                color = White,
                modifier = Modifier.padding(top = mPadding)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = lPadding, top = lPadding, end = lPadding)
            ) {
                DefButton(
                    text = stringResource(id = R.string.payTypeCash),
                    modifier = Modifier
                        .border(borderSize, White, RoundedCornerShape(mShape))
                        .height(35.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent,
                        contentColor = if (payType == 0) Red else White,
                    ),
                    onClick = {
                        payType = 0
                    },
                )
                DefButton(
                    text = stringResource(id = R.string.payTypeCard),
                    modifier = Modifier
                        .border(borderSize, White, RoundedCornerShape(mShape))
                        .height(35.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent,
                        contentColor = if (payType == 1) Red else White,
                    ),
                    onClick = {
                        payType = 1
                    },
                )
            }

            DefButton(
                text = stringResource(id = R.string.order),
                modifier = Modifier
                    .padding(start = lPadding, top = lPadding, end = lPadding)
                    .fillMaxWidth()
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Beige,
                    contentColor = Black,
                ),
                onClick = {
                    if (address.isNotEmpty() && phone.isNotEmpty()) {
                        payClick()

                        val sdf = SimpleDateFormat("dd.MM.yyyy hh:mm:ss")
                        val currentDate = sdf.format(Date())

                        Log.e("LOGEGE", "LOG${pizzaParam}")
                        mainViewModel.historyList.value.add(
                            HistoryItem(
                                pizzaParam.price,
                                address,
                                phone,
                                currentDate,
                                pizzaParam.imageId,
                                pizzaParam.category
                            )
                        )
                    } else {
                        if (address.isEmpty() && phone.isEmpty()) {
                            Toast.makeText(
                                context,
                                "Добавьте адрес и номер телефона",
                                Toast.LENGTH_LONG
                            ).show()
                        } else if (address.isEmpty()) {
                            Toast.makeText(context, "Добавьте адрес", Toast.LENGTH_LONG).show()
                        } else if (phone.isEmpty()) {
                            Toast.makeText(context, "Добавьте номер телефона", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                },
            )
        }
    }
}

@Preview
@Composable
fun BasketPreview() {
    val mainViewModel = hiltViewModel<MainViewModel>()
    val navController = rememberNavController()
    BasketView(mainViewModel, navController)
}