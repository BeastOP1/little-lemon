package com.example.littlelemon.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import com.example.littlelemon.LittleLemonApp
import com.example.littlelemon.R
import com.example.littlelemon.Routes
import com.example.littlelemon.data.remote.Menu
import com.example.littlelemon.ui.theme.Black
import com.example.littlelemon.ui.theme.DarkGrey
import com.example.littlelemon.ui.theme.DarkWhite
import com.example.littlelemon.ui.theme.Grey
import com.example.littlelemon.ui.theme.LightWhite
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.Yellow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalLayoutApi::class
)
@Composable
fun Home(modifier: Modifier = Modifier, navController: NavHostController) {
    var selectedCategory by remember { mutableStateOf("") }
    val listChips = listOf<String>("Starters", "Mains", "Desserts", "Drinks")

    var menus = listOf<Menu>(
        Menu(
            id = 1,
            title = "Greek Salad",
            description = "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
            price = "10",
            image = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
            category = "starters"
        ),
        Menu(
            id = 2,
            title = "Lemon Desert",
            description = "Traditional homemade Italian Lemon Ricotta Cake.",
            price = "10",
            image = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
            category = "desserts"
        ),
        Menu(
            id = 3,
            title = "Grilled Fish",
            description = "Our Bruschetta is made from grilled bread that has been smeared with garlic and seasoned with salt and olive oil.",
            price =  "10",
            image = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
            category = "mains"
        ),
        Menu(
            id = 4,
            title = "Pasta",
            description = "Penne with fried aubergines, cherry tomatoes, tomato sauce, fresh chili, garlic, basil & salted ricotta cheese.",
            price =  "10",
            image = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
            category = "mains"
        ),Menu(
            id = 5,
            title = "Bruschetta",
            description = "Oven-baked bruschetta stuffed with tomatoes and herbs.",
            price =  "10",
            image = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
            category = "starters"
        ),
    )

    val scrollState = rememberScrollState()
    var searchPhrase by remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

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
                    .size(45.dp)
                    .border(width = 1.dp, Yellow, CircleShape)
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

        Hero(searchPhrase = searchPhrase,
            onSearchChange = {
                searchPhrase = it })
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ORDER FOR DELIVERY!",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )

                IconButton(
                    onClick = {
                        selectedCategory = ""
                    }, colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Yellow
                    )
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Refresh,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )

                }

            }
            Categories(
                items = listChips,
                selectedCategory = selectedCategory
            ) {
                selectedCategory = it
            }
            HorizontalDivider(modifier, thickness = 2.dp)
        }
        if(selectedCategory.isNotEmpty()){
            menus = menus.filter { it.category == selectedCategory.toLowerCase(Locale.current) }
        }
        else if(searchPhrase.isNotEmpty()){
            menus = menus.filter { it.title == searchPhrase.capitalize(Locale.current.platformLocale) }
        }
        Menus(menus)

    }
}

@Composable
fun Hero(searchPhrase: String, onSearchChange:(String)-> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(DarkGrey)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {


        Text(
            text = "Little Lemon",
            style = MaterialTheme.typography.displaySmall.copy(
                color = Yellow,
                fontWeight = FontWeight.SemiBold
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                modifier = Modifier.weight(0.5f)
            ) {
                Text(
                    text = "Chicago",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = if (!isSystemInDarkTheme()) LightWhite else Black,
                        fontWeight = FontWeight.Medium
                    )
                )
                Text(
                    maxLines = 5,
                    text = "We are family owned \nMediterranean restaurant,\nfocused on traditional \nrecipes served with a\nmodern twist",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.background,
                        fontWeight = FontWeight.Normal
                    )
                )
            }

            Image(
                painter = painterResource(R.drawable.hero_image),
                contentDescription = null,
                modifier = Modifier
                    .size(135.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )
        }


        TextField(value = searchPhrase, onValueChange = onSearchChange,
            placeholder = {
                Text(
                    text = "Enter search phrase",
                    color = if (isSystemInDarkTheme()) DarkWhite else DarkGrey
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Yellow,
                focusedLeadingIconColor = Yellow,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.onBackground
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
            }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(9.dp)
        )


    }
}


@Composable
fun MenuItem(
    dishName: String,
    description: String,
    price: String,
     imgUrl: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            Column(
                modifier = Modifier.weight(0.7f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = dishName,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground,
                        lineBreak = LineBreak.Heading
                    )
                )
                Text(
                    modifier = Modifier,
                    minLines = 2,
                    maxLines = 2,
                    text = description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Normal,
                        lineBreak = LineBreak.Paragraph.copy(strategy = LineBreak.Strategy.Balanced),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
                Text(
                    text = "$${price}",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = if (isSystemInDarkTheme()) Grey else DarkGrey
                    )
                )
            }
            AsyncImage(

                model = imgUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(5.dp)),
            )

        }

        HorizontalDivider()

    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Menus(
    menus: List<Menu>,
) {
    FlowColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)

        ) {
        repeat(menus.size){
            MenuItem(
                dishName = menus[it].title,
                description = menus[it].description,
                price = menus[it].price,
                imgUrl = menus[it].image
            )
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Categories(
    items: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        repeat(items.size) {
            CategoryItem(items[it], selectedCategory == items[it]) {
                onCategorySelected(items[it])
            }
        }

    }


}

@Composable
fun CategoryItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    FilterChip(
        onClick = onClick,
        modifier = Modifier.height(40.dp),
        shape = RoundedCornerShape(16.dp),
        colors = FilterChipDefaults.filterChipColors(
            containerColor = LightWhite.copy(0.5f),
            selectedContainerColor = Yellow.copy(0.6f),

            labelColor = DarkGrey,
            selectedLabelColor = MaterialTheme.colorScheme.onBackground
        ),
        border = BorderStroke(0.dp, Color.Transparent),
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )
        },
        selected = selected,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@PreviewLightDark
@Composable
fun HomePreview(modifier: Modifier = Modifier) {


    LittleLemonTheme {

    }
}