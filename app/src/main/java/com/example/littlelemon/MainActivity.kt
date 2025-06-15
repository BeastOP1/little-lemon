package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.data.PreferenceKeys
import com.example.littlelemon.presentation.Home
import com.example.littlelemon.presentation.OnBoarding
import com.example.littlelemon.presentation.Profile
import com.example.littlelemon.ui.theme.Black
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.Orange

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LittleLemonTheme {
                var startDestination = LittleLemonApp.startDestination.collectAsState()



                val navController = rememberNavController()
                   AppStart(navController, startDestination.value )
            }



        }





    }



}

enum class Routes{
    HOME,
    PROFILE,
    REGISTER
}

 @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
 @Composable
private fun AppStart(
     navController: NavHostController,
     startDescriptor: String,
 ) {

    val interactionSource = remember { MutableInteractionSource() }

    Scaffold{ innerPadding ->
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

                Home(modifier = Modifier, navController)
            }
        }

    }
     }

