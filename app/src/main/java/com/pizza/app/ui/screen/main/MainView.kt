package com.pizza.app.ui.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pizza.app.R
import com.pizza.app.ui.elements.DefButton
import com.pizza.app.ui.elements.Header
import com.pizza.app.ui.navigation.Screen
import com.pizza.app.ui.theme.Beige
import com.pizza.app.ui.theme.Black
import com.pizza.app.ui.theme.DarkBlue
import com.pizza.app.ui.theme.Red
import com.pizza.app.ui.theme.White
import com.pizza.app.ui.theme.borderSize
import com.pizza.app.ui.theme.lPadding
import com.pizza.app.ui.theme.lText
import com.pizza.app.ui.theme.mPadding
import com.pizza.app.ui.theme.mShape
import com.pizza.app.ui.theme.nonScaledSp
import com.pizza.app.ui.theme.sPadding
import com.pizza.app.utils.BackPressHandler
import com.pizza.app.utils.Const
import java.util.UUID

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MainView(
    mainViewModel: MainViewModel,
    navController: NavController = NavController(LocalContext.current)
) {
    val category = remember {
        mutableStateOf("")
    }
    UI(
        payClick = {
            navController.navigate(Screen.Basket.name)
            mainViewModel.setPizzaParam(it.copy(category = category.value))
        },
        logoutClick = {
            navController.navigate(Screen.Login.name)
        },
        historyClick = {
            navController.navigate(Screen.History.name)
        },
        onCategoryClick = { it ->
            category.value = it

        },
        stateCategory = category.value
    )
    BackPressHandler {}
}

@Composable
fun CustomCategoryItem(
    title: String,
    isSelected: Boolean,
    onClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(
                if (isSelected) Beige
                else Color.LightGray
            )
            .padding(horizontal = 2.dp)
            .clickable {
                onClick.invoke(title)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            modifier = Modifier
                .padding(2.dp)
        )
    }
}


@Composable
private fun UI(
    payClick: (PizzaParam) -> Unit,
    stateCategory: String,
    onCategoryClick: (String) -> Unit,
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
        HorizontalScrollBlock(
            onCategoryClick = onCategoryClick,
            stateCategory = stateCategory
        )

        Spacer(modifier = Modifier.padding(8.dp))

        ScrollBlock(
            payClick = payClick
        )
    }
}

@Immutable
data class PizzaParam(
    val name: String = "",
    val price: Int = 0,
    val weight: Int = 0,
    val category: String = "",
    val imageId: Int = R.drawable.pizza1,
    val id: String = UUID.randomUUID().toString()
)

@Composable
private fun HorizontalScrollBlock(
    onCategoryClick: (String) -> Unit,
    stateCategory: String
) {
    val categories = listOf("чесночный соус", "соевый соус", "кетчуп", "сырный соус")

    LazyRow(modifier = Modifier.padding(end = 10.dp, top = 10.dp)) {
        items(categories) { title ->
            CustomCategoryItem(
                title = title,
                isSelected = title == stateCategory,
                onClick = {
                    if (stateCategory == it) {
                        onCategoryClick("")
                    } else {
                        onCategoryClick(it)
                    }
                }
            )
            Spacer(modifier = Modifier.padding(5.dp))
        }
    }
}

@Composable
private fun ScrollBlock(
    payClick: (PizzaParam) -> Unit
) {
    val pizzaList = listOf(
        PizzaParam("Пицца Пепперони", 399, 850, "", R.drawable.pizza1),
        PizzaParam("Пицца Барбекю", 589, 520, "", R.drawable.pizza2),
        PizzaParam("Пицца 4 сыра", 565, 470, "", R.drawable.pizza3),
        PizzaParam("Пицца 4 вкуса", 559, 510, "", R.drawable.pizza4),
        PizzaParam("Пицца Чизбургер", 589, 540, "", R.drawable.pizza5),
        PizzaParam("Пицца Фермерская", 365, 460, "", R.drawable.pizza6)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(lPadding),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = pizzaList,
            key = {
                it.id
            }
        ) {
            PizzaElement(
                it,
                payClick = payClick
            )
        }
    }
}

@Composable
private fun PizzaElement(
    pizzaParam: PizzaParam,
    payClick: (PizzaParam) -> Unit
) {

    var doughState by remember { mutableIntStateOf(1) }
    var doughSize by remember { mutableIntStateOf(0) }

    var weight: Int = when (doughSize) {
        1 -> (pizzaParam.weight * 1.4f).toInt()
        2 -> (pizzaParam.weight * 1.6f).toInt()
        else -> pizzaParam.weight
    }
    var price: Int = when (doughSize) {
        1 -> (pizzaParam.price * 1.2f).toInt()
        2 -> (pizzaParam.price * 1.5f).toInt()
        else -> pizzaParam.price
    }

    if (doughState == 0) {
        weight = (weight / 1.2f).toInt()
    }
    if (doughState == 0) {
        price = (price * 1.2f).toInt()
    }

    Column(
        modifier = Modifier
            .background(Beige, RoundedCornerShape(mShape))
            .fillMaxWidth()
            .padding(start = mPadding, top = lPadding, end = mPadding, bottom = lPadding)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
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
                    modifier = Modifier.padding()
                )
                Text(
                    text = "${stringResource(id = R.string.weight)} ${weight}г",
                    fontSize = lText.nonScaledSp,
                    color = Black,
                    modifier = Modifier.padding(top = mPadding)
                )
                Text(
                    text = "${stringResource(id = R.string.price)} ${price}р",
                    fontSize = lText.nonScaledSp,
                    color = Black,
                    modifier = Modifier.padding(top = mPadding)
                )
            }
        }
        Text(
            text = stringResource(id = R.string.dough),
            fontSize = lText.nonScaledSp,
            textAlign = TextAlign.Center,
            color = Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = sPadding)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = mPadding)
        ) {
            DefButton(
                text = stringResource(id = R.string.doughThin),
                modifier = Modifier
                    .border(borderSize, White, RoundedCornerShape(mShape))
                    .height(35.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = if (doughState == 0) Red else Black,
                ),
                onClick = {
                    doughState = 0
                },
            )
            DefButton(
                text = stringResource(id = R.string.doughFat),
                modifier = Modifier
                    .border(borderSize, White, RoundedCornerShape(mShape))
                    .height(35.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = if (doughState == 1) Red else Black,
                ),
                onClick = {
                    doughState = 1
                },
            )
        }
        Text(
            text = stringResource(id = R.string.size),
            fontSize = lText.nonScaledSp,
            textAlign = TextAlign.Center,
            color = Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = sPadding)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = mPadding)
        ) {
            DefButton(
                text = stringResource(id = R.string.mSize),
                modifier = Modifier
                    .border(borderSize, White, RoundedCornerShape(mShape))
                    .height(35.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = if (doughSize == 0) Red else Black,
                ),
                onClick = {
                    doughSize = 0
                },
            )
            DefButton(
                text = stringResource(id = R.string.lSize),
                modifier = Modifier
                    .border(borderSize, White, RoundedCornerShape(mShape))
                    .height(35.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = if (doughSize == 1) Red else Black,
                ),
                onClick = {
                    doughSize = 1
                },
            )
            DefButton(
                text = stringResource(id = R.string.xlSize),
                modifier = Modifier
                    .border(borderSize, White, RoundedCornerShape(mShape))
                    .height(35.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = if (doughSize == 2) Red else Black,
                ),
                onClick = {
                    doughSize = 2
                },
            )
        }
        DefButton(
            text = stringResource(id = R.string.pay),
            modifier = Modifier
                .padding(top = mPadding)
                .border(borderSize, Red, RoundedCornerShape(mShape))
                .fillMaxWidth()
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = Red,
            ),
            onClick = {
                payClick(
                    PizzaParam(
                        pizzaParam.name,
                        weight,
                        price,
                        pizzaParam.category,
                        pizzaParam.imageId
                    )
                )
            },
        )
    }
}
