package com.example.littlelemon.presentation

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import com.example.littlelemon.LittleLemonApp
import com.example.littlelemon.MainActivity
import com.example.littlelemon.R
import com.example.littlelemon.Routes
import com.example.littlelemon.data.PreferenceKeys
import com.example.littlelemon.ui.theme.Black
import com.example.littlelemon.ui.theme.DarkGrey
import com.example.littlelemon.ui.theme.Grey
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.Orange
import com.example.littlelemon.ui.theme.White
import com.example.littlelemon.ui.theme.Yellow

@Composable
fun OnBoarding(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {

    val interactionSource = remember { MutableInteractionSource() }

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val userPreference = LittleLemonApp.userPrefer


    fun writeUser(firstName: String, lastName: String, email: String){
        userPreference.edit{
            putString(PreferenceKeys.FIRST_NAME.name, firstName)
            putString(PreferenceKeys.LAST_NAME.name, lastName)
            putString(PreferenceKeys.EMAIL.name, email)
            apply()
        }
    }
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.height(90.dp).width(180.dp),
            contentScale = ContentScale.Fit
        )

        Row(
            modifier.fillMaxWidth().height(150.dp).background(DarkGrey),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Let's get to know you ",
                fontSize = 34.sp,

                color = MaterialTheme.colorScheme.onBackground)
        }
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(vertical = 32.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween

        ) {

            Text(text = "Personal information", style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight.SemiBold),modifier = Modifier.padding())
            Column(
                modifier = Modifier.wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {

                Column {

                    Text(text = "First Name", style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onBackground.copy(0.8f)),modifier = Modifier.padding(bottom = 5.dp))
                    TextField(value = firstName, onValueChange = {
                        firstName = it
                    },
                        keyboardActions = KeyboardActions(
                            onNext = {
                            },
                        ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        placeholder = {
                        Text(text = "Enter First name")
                    },modifier = Modifier.fillMaxWidth()
                        .border(width = 1.dp, color = Grey, shape = RoundedCornerShape(8.dp)), colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        focusedTextColor = MaterialTheme.colorScheme.onBackground,
                        focusedIndicatorColor = Yellow

                    ) , )
                }

                Column {

                    Text(text = "Last Name", style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onBackground.copy(0.8f)),modifier = Modifier.padding(bottom = 5.dp))
                    TextField(value = lastName, onValueChange = {
                        lastName = it
                    }, placeholder = {
                        Text(text = "Enter Last name")
                    },modifier = Modifier.fillMaxWidth().border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp)), colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        focusedTextColor = MaterialTheme.colorScheme.onBackground,
                        focusedIndicatorColor = Yellow
                    ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                            },
                        ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    )
                }

                Column {

                    Text(text = "Email", style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onBackground.copy(0.8f)),modifier = Modifier.padding(bottom = 5.dp))
                    TextField(value = email, onValueChange = {
                        email = it
                    }, placeholder = {
                        Text(text = "Enter Email")
                    },modifier = Modifier.fillMaxWidth().border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp)), colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        focusedTextColor = MaterialTheme.colorScheme.onBackground,
                        focusedIndicatorColor = Yellow

                    ) ,
                        keyboardActions = KeyboardActions(
                            onNext = {
                            },
                        ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    )
                }
            }


            Button(
                onClick = {
                    writeUser(firstName,lastName,email)
                    LittleLemonApp.startDestination.value = Routes.HOME.name
                 },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = Yellow,
                    contentColor = if(isSystemInDarkTheme()) White else Black
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(width = 1.dp, color = Orange)
            ) {
                Text(text = "Register", style = MaterialTheme.typography.titleSmall)
            }

        }


    }

}


@PreviewLightDark
@Composable
fun OnBoardingPreview(modifier: Modifier = Modifier) {

    LittleLemonTheme { 
//        OnBoarding(navController = navController)
    }
}