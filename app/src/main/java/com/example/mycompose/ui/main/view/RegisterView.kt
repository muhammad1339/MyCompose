package com.example.mycompose.ui.main.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mycompose.R
import com.example.mycompose.ui.base.RouteNames
import com.example.mycompose.ui.widgets.*

@Composable
fun RegisterView(navController: NavController) {
    Box(
        Modifier
            .padding(12.dp)
            .fillMaxSize()
            .wrapContentSize()
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            ComposableInput(hint = R.string.email, drawableStart = R.drawable.ic_mail) {
            }
            Spacer(modifier = Modifier.size(4.dp))
            ComposablePasswordInput(hint = R.string.password, drawableStart = R.drawable.ic_lock) {
            }
            Spacer(modifier = Modifier.size(4.dp))
            ComposablePasswordInput(
                hint = R.string.re_password,
                drawableStart = R.drawable.ic_lock
            ) {
            }
            Spacer(modifier = Modifier.size(12.dp))
            ComposableButton(R.string.register) {
                navController.navigate(RouteNames.LOGIN_ROUTE) {
                    anim {
                        enter = R.anim.anim_fade_in
                        exit = R.anim.anim_fade_out
                    }
                    //popUpTo(RouteNames.REGISTER_ROUTE)
                }
            }
        }
    }
}