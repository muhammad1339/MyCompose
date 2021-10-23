package com.example.mycompose.ui.main.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycompose.ui.base.RouteNames
import com.example.mycompose.ui.theme.MyComposeTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AppRouter()
                }
            }
        }

    }
}

@Composable
fun AppRouter() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = RouteNames.LOGIN_ROUTE) {
        composable(
            route = RouteNames.LOGIN_ROUTE,
            ) {
            LoginView(navController = navController)
        }
        composable(route = RouteNames.REGISTER_ROUTE) {

            Crossfade(targetState = RouteNames.REGISTER_ROUTE,animationSpec = tween(3000)) { screen ->
                when (screen) {
                    RouteNames.REGISTER_ROUTE -> RegisterView(navController = navController)
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    MyComposeTheme {
//        MainContainer()
//    }
//}


