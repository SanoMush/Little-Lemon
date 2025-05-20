package com.sanomush.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import dev.syprosegwako.littlelemon.utils.displayText
import dev.syprosegwako.littlelemon.utils.inputFieldTextStyle
import dev.syprosegwako.littlelemon.utils.markaziTextFamily
import dev.syprosegwako.littlelemon.utils.normalTextStyle
import dev.syprosegwako.littlelemon.utils.paragraphTitleTextStyle
import java.util.Locale


@Composable
fun Home(
    navController: NavHostController,
    items: List<MenuItemRoom>
) {
    var menuItems = items.sortedBy { it.title }

    var searchPhrase by rememberSaveable { mutableStateOf("") }
    var filterCategory by rememberSaveable { mutableStateOf("") }

    if (searchPhrase.isNotEmpty()) {
        filterCategory = ""
        menuItems = menuItems.filter {
            it.title.contains(searchPhrase, ignoreCase = true)
        }
    }

    if (filterCategory.isNotEmpty()) {
        menuItems = menuItems.filter {
            it.category == filterCategory.lowercase(Locale.ROOT)         }
    }

    fun goToProfile() {
        navController.navigate(Profile.route)
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(vertical = 40.dp)

    ) {
        //logo header
        Row (modifier = Modifier
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Spacer(modifier = Modifier.weight(1f))
            Logo(
                modifier = Modifier
                    .weight(2f)
                    .padding(top = 16.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .weight(1f)
                    .size(50.dp)
                    .clickable { goToProfile() }
            )

        }

        HeroBanner(
            searchPhrase = searchPhrase,
            onValueChange = { newPhrase -> searchPhrase = newPhrase }
        )
        MenuCategories(onFilter = {category -> filterCategory = category})

        HorizontalDivider(
            modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
            thickness = 1.dp,
            color = Color.Gray
        )
        MenuItemsList(menuItems)
    }

}

@Composable
fun HeroBanner(
    searchPhrase: String,
    onValueChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.primary1))
            .padding(16.dp)
    ) {
        Text(
            text = "Little Lemon",
            textAlign = TextAlign.Start,
            style = displayText,
            fontSize = 48.sp,
            color = colorResource(id = R.color.primary2),
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(2f)
            ) {
                Text(
                    text = "Chicago",
                    textAlign = TextAlign.Start,
                    style = displayText,
                    color = colorResource(id = R.color.white)
                )
                Text(
                    text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                    textAlign = TextAlign.Start,
                    style = normalTextStyle,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.hero),
                contentDescription = "Hero image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(2f)
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }
        //search bar
        OutlinedTextField(
            value = searchPhrase,
            onValueChange = onValueChange,
            textStyle = inputFieldTextStyle(),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(id = R.color.highlight1),
                focusedContainerColor = colorResource(id = R.color.highlight1),
                focusedBorderColor = colorResource(id = R.color.primary1),
                cursorColor = colorResource(id = R.color.primary1)
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = colorResource(id = R.color.primary1)
                )
            },
            placeholder = {
                Text(
                    text = "Enter search phrase",
                    color = Color.Gray )},
            maxLines = 1,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
    }

}

@Composable
private fun MenuCategories(
    onFilter: (String) -> Unit
) {
   val categories = listOf("Starters", "Desserts", "Mains", "Drinks")

    Column( modifier = Modifier.padding(16.dp)) {
        Text(
            text = "ORDER FOR DELIVERY!",
            style = paragraphTitleTextStyle,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp),
        )
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(
                items =  categories,
                itemContent = { category ->
                    Button(
                        modifier = Modifier.padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(Color.LightGray),
                        shape = RoundedCornerShape(20.dp),
                        onClick = { onFilter(category) }) {
                                Text(
                                    text = category,
                                    style = paragraphTitleTextStyle
                                )
                        }
                }
            )
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MenuItemsList(items: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(modifier = Modifier.weight(3f)) {
                        Text(
                            text = menuItem.title,
                            style = paragraphTitleTextStyle
                        )
                        Text(
                            text = menuItem.description,
                            style = normalTextStyle,
                            color = colorResource(id = R.color.highlight2),
                        )
                        Text(
                            modifier = Modifier.padding(5.dp),
                            style = paragraphTitleTextStyle,
                            textAlign = TextAlign.Start,
                            color = colorResource(id = R.color.primary1),
                            text = "$%.2f".format(menuItem.price)
                        )
                    }
                    GlideImage(
                        model = menuItem.image,
                        contentDescription = menuItem.title,
                        contentScale = ContentScale.Crop,
                        loading = placeholder(R.drawable.greek_salad),
                        modifier = Modifier
                            .weight(1f)
                            .size(80.dp)
                            .padding(8.dp)

                    )
                }
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 0.2.dp,
                    color = Color.Gray
                )
            }
        )
    }
}

@Preview(backgroundColor = 1, showBackground = true)
@Composable
fun HomePreview() {
    val navController = rememberNavController()
    val menuItems = listOf(
        MenuItemRoom(
            id = 1,
            "Greek Salad",
            "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
            10.0,
            "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
            "starters"
        ),
        MenuItemRoom(
            2,
            "Lemon Desert",
            "Traditional homemade Italian Lemon Ricotta Cake.",
            10.0,
            "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/lemonDessert%202.jpg?raw=true",
            "desserts"
        )
    )
    Home(navController, menuItems)
}