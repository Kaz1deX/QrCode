package com.example.qrcode.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.qrcode.R

val montserratFontFamily = FontFamily(
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_semi_bold, FontWeight.SemiBold),
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_extra_bold, FontWeight.ExtraBold)
)

val TitleTextStyle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 28.sp,
            fontFamily = montserratFontFamily,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }

val MainTextStyle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 24.sp,
            fontFamily = montserratFontFamily,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }

val SmallTextStyle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 16.sp,
            fontFamily = montserratFontFamily,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.primary
        )
    }