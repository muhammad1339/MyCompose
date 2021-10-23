package com.example.mycompose.ui.main.view

import android.app.Application
import android.util.Log
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.mycompose.R
import com.example.mycompose.ui.base.RouteNames
import com.example.mycompose.ui.main.viewmodel.FormValidationVM
import com.example.mycompose.ui.widgets.*

@Composable
fun LoginView(navController: NavController) {
    val tag = "LoginView"
    lateinit var emailInput: String
    lateinit var passwordInput: String
    val formVM by lazy {
        ViewModelProvider.AndroidViewModelFactory(Application())
            .create(FormValidationVM::class.java)
    }
    val isFormEmailValid by formVM.isFormEmailValid.observeAsState()
    val isFormPasswordValid by formVM.isFormPasswordValid.observeAsState()

    val loginLogo = painterResource(id = R.drawable.login_image)
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

            Image(painter = loginLogo, contentDescription = "")
            ComposableInput(hint = R.string.email, drawableStart = R.drawable.ic_mail) {
                emailInput = it
                formVM.email.postValue(emailInput)
            }
            Spacer(modifier = Modifier.size(4.dp))
            ComposablePasswordInput(hint = R.string.password, drawableStart = R.drawable.ic_lock) {
                passwordInput = it
                formVM.password.postValue(passwordInput)
            }
            Spacer(modifier = Modifier.size(12.dp))
            ComposableButton(
                R.string.login,
                isEnabled = (isFormEmailValid ?: false && isFormPasswordValid ?: false)
            ) {
                navController.navigate(RouteNames.REGISTER_ROUTE) {
                    // popUpTo(RouteNames.LOGIN_ROUTE)
                    anim {
                        enter = R.anim.anim_fade_in
                        exit = R.anim.anim_fade_out
                    }
                }
            }
            Spacer(modifier = Modifier.size(4.dp))
            Row {
                ComposableText(text = R.string.do_not_have_account)
                Spacer(modifier = Modifier.width(8.dp))
                ComposableText(text = R.string.register, isUnderLined = true)
            }
        }
    }
}