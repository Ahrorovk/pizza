package com.pizza.app.ui.screen.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.pizza.app.ui.elements.Header
import com.pizza.app.ui.navigation.Screen
import com.pizza.app.ui.screen.main.MainViewModel
import com.pizza.app.ui.theme.Beige
import com.pizza.app.ui.theme.Black
import com.pizza.app.ui.theme.DarkBlue
import com.pizza.app.ui.theme.Peru
import com.pizza.app.ui.theme.White
import com.pizza.app.ui.theme.lPadding
import com.pizza.app.ui.theme.lText
import com.pizza.app.ui.theme.mPadding
import com.pizza.app.ui.theme.mShape
import com.pizza.app.ui.theme.nonScaledSp
import com.pizza.app.ui.theme.text
import com.pizza.app.utils.BackPressHandler

@Composable
fun HistoryView(
    mainViewModel: MainViewModel,
    navController: NavController = NavController(LocalContext.current)
) {
    UI(mainViewModel,
        backClick = {
            navController.navigate(Screen.Main.name)
        }
    )
    BackPressHandler {}
}

@Composable
private fun UI(
    mainViewModel: MainViewModel,
    backClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
    ) {
        val list = mainViewModel.historyList.collectAsState()

        Header(logoutClick = backClick, historyClick = {})

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(lPadding)
        ) {
            items(list.value.size) {
                Column(
                    modifier = Modifier
                        .background(Beige, RoundedCornerShape(mShape))
                        .fillMaxSize()
                        .height(100.dp)
                        .padding(start = lPadding, top = mPadding, end = lPadding)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Column {
                            Image(
                                painter = painterResource(id = list.value[it].imageId),
                                contentDescription = "imageId",
                                modifier = Modifier.size(50.dp).clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.size(5.dp))
                            Text(text = list.value[it].category)
                        }

                        Column {
                            Row(
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = list.value[it].date,
                                    fontSize = text.nonScaledSp,
                                    color = Gray
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Column(horizontalAlignment = Alignment.End) {
                                    Text(
                                        text = "${list.value[it].sum}р",
                                        fontSize = lText.nonScaledSp,
                                        color = Black,
                                        textAlign = TextAlign.End
                                    )
                                    Text(
                                        text = list.value[it].address,
                                        fontSize = lText.nonScaledSp,
                                        color = Black
                                    )
                                    Text(
                                        text = list.value[it].phoneNumber,
                                        fontSize = lText.nonScaledSp,
                                        color = Black
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
