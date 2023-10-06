package com.example.tryggaklassenpod.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.example.tryggaklassenpod.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontName = GoogleFont("Open Sans")
val fontName2 = GoogleFont("Segoe UI")

val fontFamilyH = FontFamily(
    Font(
        googleFont = fontName,
        fontProvider = provider
    )
)

val fontFamilyB = FontFamily(
    Font(
        googleFont = fontName2,
        fontProvider = provider
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = fontFamilyH,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = fontFamilyH,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = fontFamilyH,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = fontFamilyH,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = fontFamilyH,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = fontFamilyH,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = fontFamilyB,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = fontFamilyB,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = fontFamilyB,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
    /*val h1 = TextStyle(
        fontFamily = fontFamilyH,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
    ),
    val h2 = TextStyle(
        fontFamily = fontFamilyH,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    val h3 = TextStyle(
        fontFamily = fontFamilyH,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    val h4 = TextStyle(
        fontFamily = fontFamilyH,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    val h5 = TextStyle(
        fontFamily = fontFamilyH,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    val h6 = TextStyle(
        fontFamily = fontFamilyH,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    val body1 = TextStyle(
        fontFamily = fontFamilyB,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    val body2 = TextStyle(
        fontFamily = fontFamilyB,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )*/
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)