package com.example.tryggaklassenpod.screens


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.tryggaklassenpod.R


@Composable
fun AboutScreen(navController: NavController){

    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Image(
            painter = painterResource(id = R.drawable.lotus_stiliserad_liten),
            contentDescription = "logo",
            modifier = Modifier.fillMaxWidth(.6f),
        )

        Spacer(modifier = Modifier.padding(10.dp))
        LinkToLotusModelen()
    }
}

@Composable
fun LinkToLotusModelen() {
    val context = LocalContext.current
    val annotatedString = buildAnnotatedString {
        append("Visit our website: ")
        pushStringAnnotation(
            tag = "LINK",
            annotation = "https://lotusmodellen.se/wp/"
        )
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        ) {
            append("Lotus Modelen")
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(
                tag = "LINK",
                start = offset,
                end = offset
            ).firstOrNull()?.let { annotation ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                if (intent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(intent)
                }
            }
        }
    )
}
