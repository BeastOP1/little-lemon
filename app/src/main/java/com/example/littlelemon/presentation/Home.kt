package com.example.littlelemon.presentation

import android.content.Context.MODE_PRIVATE
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemon.R
import com.example.littlelemon.Routes
import com.example.littlelemon.data.PreferenceKeys
import com.example.littlelemon.ui.theme.Black
import com.example.littlelemon.ui.theme.DarkGrey
import com.example.littlelemon.ui.theme.DarkWhite
import com.example.littlelemon.ui.theme.LightWhite
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.Yellow

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Home(modifier: Modifier = Modifier, navController: NavHostController) {
    val userPreference = LocalContext.current.getSharedPreferences("user",MODE_PRIVATE)
    val name =userPreference.getString(PreferenceKeys.FIRST_NAME.name,"")!!
    val email =userPreference.getString(PreferenceKeys.EMAIL.name,"")!!
    val password = userPreference.getString("Password","NOt Exits")!!


    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Row (
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){

            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Absolute.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(R.drawable.logo),
                    modifier = Modifier
                        .width(160.dp)
                        .height(60.dp),
                    contentScale = ContentScale.Fit,
                    contentDescription = null,
                    alignment = Alignment.Center
                )
            }


            Image(painter = painterResource(R.drawable.profile),
                modifier = Modifier
                    .size(45.dp).border(width = 1.dp, Yellow, CircleShape)
                    .clip(CircleShape)
                    .combinedClickable(enabled = true,
                        indication = null,
                        interactionSource = interactionSource,
                        onClick = {
                            navController.navigate(Routes.PROFILE.name) {
                                launchSingleTop = true
                            }
                        }

                    ),

                contentScale = ContentScale.Fit,
                contentDescription = null)


        }

        Hero()


    }
}

@Composable
fun Hero(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(DarkGrey).padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {


        Text(text = "Little Lemon", style = MaterialTheme.typography.displaySmall.copy(color = Yellow, fontWeight = FontWeight.SemiBold))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                modifier = Modifier.weight(0.5f)
            ){
                Text(text = "Chicago", style = MaterialTheme.typography.titleLarge.copy(color =  if(!isSystemInDarkTheme())LightWhite else Black, fontWeight = FontWeight.Medium))
                Text(
                    maxLines = 5,
                    text = "We are family owned \nMediterranean restaurant,\nfocused on traditional \nrecipes served with a\nmodern twist",
                    style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.background, fontWeight = FontWeight.Normal))
            }

            Image(
                painter = painterResource(R.drawable.hero_image),
                contentDescription = null,
                modifier = Modifier.size(135.dp).clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )
        }


        TextField(value = "", onValueChange = {},
            placeholder = {
                Text(text = "Enter search phrase", color = if(isSystemInDarkTheme()) DarkWhite else DarkGrey)
            },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Yellow,
                focusedLeadingIconColor = Yellow,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.onBackground
            )
            ,
            leadingIcon = {
            Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
        }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(9.dp))



    }
}

@PreviewLightDark
@Composable
fun HomePreview(modifier: Modifier = Modifier) {
    LittleLemonTheme {
        Hero()
    }
}