package com.sanomush.littlelemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.syprosegwako.littlelemon.utils.PreferencesManager
import dev.syprosegwako.littlelemon.utils.markaziTextFamily
import dev.syprosegwako.littlelemon.utils.paragraphTitleTextStyle

@Composable
fun Profile(navController: NavController){

    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }

    fun logOut() {
        preferencesManager.clearAllData()
        navController.navigate(Onboarding.route)
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(vertical = 40.dp)
    ) {
        //logo
        Logo(modifier = Modifier.align(Alignment.CenterHorizontally))

        //paragraph title
        Text(
            text = "Personal Information",
            textAlign = TextAlign.Start,
            style = paragraphTitleTextStyle,
            color = colorResource(id = R.color.black),
            modifier = Modifier.padding(40.dp)

        )
        //form
        GenericTextField(
            label = "First name",
            value = preferencesManager.getData("FIRST_NAME", ""),
            onValueChange = {},
            readOnly = true
        )
        GenericTextField(
            label = "Last name",
            value = preferencesManager.getData("LAST_NAME", ""),
            onValueChange = {},
            readOnly = true
        )
        GenericTextField(
            label = "Email",
            value = preferencesManager.getData("EMAIL", ""),
            onValueChange = {},
            keyboardType = KeyboardType.Email,
            readOnly = true
        )

        // Spacer to push the button to the bottom
        Spacer(modifier = Modifier.weight(1f))
        //button
        Button(
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.primary2)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth().padding(40.dp),
            onClick = { logOut() }
        )
        {
            Text(
                text = "Log Out",
                color = Color.Black,
                fontFamily = markaziTextFamily
            )
        }
    }

}

@Preview(backgroundColor = 1, showBackground = true)
@Composable
fun ProfilePreview(){
    val navController = rememberNavController()
    Profile(navController)
}