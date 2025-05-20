package dev.syprosegwako.littlelemon.utils

import androidx.annotation.DimenRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.sanomush.littlelemon.R
import com.sanomush.littlelemon.ui.theme.Black


val markaziTextFamily = FontFamily(
    Font(R.font.markazi_text_regular, FontWeight.Normal),
    Font(R.font.markazi_text_medium, FontWeight.Medium),
    Font(R.font.markazi_text_semi_bold, FontWeight.SemiBold),
    Font(R.font.markazi_text_bold, FontWeight.Bold)
)

val karlaFamily = FontFamily(
    Font(R.font.karla_regular, FontWeight.Normal),
    Font(R.font.karla_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.karla_medium, FontWeight.Medium),
    Font(R.font.karla_bold, FontWeight.Bold)
)

val displayText = TextStyle(
    fontFamily = markaziTextFamily,
    fontSize = 32.sp,
    fontWeight = FontWeight.Medium
)

val subtitleTextStyle = TextStyle(
    fontFamily = karlaFamily,
    fontSize = 32.sp,
    fontWeight = FontWeight.Medium
)

val paragraphTitleTextStyle = TextStyle(
    color = Black,
    fontFamily = markaziTextFamily,
    fontSize = 20.sp,
    fontWeight = FontWeight.SemiBold
)
val normalTextStyle = TextStyle(
    color = Black,
    fontFamily = markaziTextFamily,
    fontSize = 20.sp,
    fontWeight = FontWeight.Normal
)

@Composable
fun inputFieldTextStyle(): TextStyle {
    return TextStyle(
        color = Black,
        fontFamily = markaziTextFamily,
        fontWeight = FontWeight.Normal,
        fontSize = spResource(R.dimen.text_16),
        textAlign = TextAlign.Left,
        fontStyle = FontStyle.Normal
    )
}

@Composable
@ReadOnlyComposable
fun spResource(@DimenRes id: Int): TextUnit {
    return with(LocalContext.current.resources) {
        (getDimension(id) / displayMetrics.scaledDensity).sp
    }
}