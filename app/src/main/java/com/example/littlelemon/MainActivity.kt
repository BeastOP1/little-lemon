package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.presentation.Home
import com.example.littlelemon.presentation.OnBoarding
import com.example.littlelemon.presentation.Profile
import com.example.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LittleLemonTheme {
                val navController = rememberNavController()
                val startDestination = "register"
                   AppStart(navController, startDestination )
            }
        }
    }
}

enum class Routes{
    HOME,
    PROFILE,
    REGISTER
}

 @Composable
private fun AppStart(navController: NavHostController, startDescriptor: String) {

    Scaffold { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController, startDestination = startDescriptor ,

            ){

            composable(route = Routes.REGISTER.name){
                OnBoarding(modifier = Modifier, navController)
            }

            composable(route = Routes.PROFILE.name){

                Profile(modifier = Modifier, navController)
            }

            composable(route = Routes.HOME.name,){

                Home()
            }
        }

    }
     }

