package com.pizza.app.ui.screen.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.pizza.app.R
import com.pizza.app.ui.navigation.Screen
import com.pizza.app.ui.elements.DefButton
import com.pizza.app.ui.elements.EditText
import com.pizza.app.ui.screen.login.LoginViewModel
import com.pizza.app.ui.theme.DarkBlue
import com.pizza.app.ui.theme.lPadding
import com.pizza.app.ui.theme.xlPadding
import com.pizza.app.utils.Const

@Composable
fun RegView(
    navController: NavController = NavController(LocalContext.current),
    loginViewModel: LoginViewModel = viewModel()
) {
    val context = LocalContext.current

    UI(onClickLogin = { mail, pass, repPass ->
        if (loginViewModel.registration(context, mail, pass, repPass)) {
            navController.navigate(Screen.Main.name)
        }
    })
}

@Composable
fun UI(
    onClickLogin: (String, String, String) -> Unit
) {

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
            .padding(xlPadding)
    ){
        Logo()
        Auth(onClickLogin)
    }
}

@Composable
fun Logo() {
    Column {
        Image(
            painter = painterResource(id = R.drawable.full_logo),
            contentDescription = Const.IMAGE_DESCRIPTION,
            modifier = Modifier.size(300.dp)
        )
    }
}

@Composable
fun Auth(
    onClick: (String, String, String) -> Unit
) {
    var mail by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var repPass by remember { mutableStateOf("") }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        EditText(mail, stringResource(id = R.string.mail)) { mail = it }

        EditText(
            pass,
            stringResource(id = R.string.pass),
            Modifier.padding(top = lPadding),
            true,
        ) { pass = it }

        EditText(
            repPass,
            stringResource(id = R.string.repPass),
            Modifier.padding(top = lPadding),
            true,
        ) { repPass = it }


        DefButton(
            text = stringResource(id = R.string.registration),
            onClick = { onClick(mail, pass, repPass) },
            modifier = Modifier
                .padding(top = lPadding)
                .width(200.dp)
        )
    }
}

@Preview
@Composable
fun RegPreview(){
    RegView()
}