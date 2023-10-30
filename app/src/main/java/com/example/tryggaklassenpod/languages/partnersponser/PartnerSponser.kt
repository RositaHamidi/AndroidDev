package com.example.tryggaklassenpod.languages.partnersponser

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tryggaklassenpod.languages.otherAppsLink.EmailLinkCompose

@Composable
fun PartnerSponser(navController: NavController){
    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Trygga Klassen Offers companies to become a preventive partner - sponsor," +
                " we offer two options.",
            modifier = Modifier.fillMaxWidth(.9f),
            textAlign = TextAlign.Justify,
            style = LocalTextStyle.current.merge(
                TextStyle(
                    lineHeight = 1.5.em,)
            )
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = "Alternative 1 Gold partner",
            modifier = Modifier.fillMaxWidth(.9f),
            textAlign = TextAlign.Justify,
            style = LocalTextStyle.current.merge(
                TextStyle(
                    lineHeight = 1.5.em,
                    fontWeight = FontWeight.Bold,
                    )
            )
        )
        Spacer(modifier = Modifier.padding(3.dp))
        Text(
            text = "The company logo can be seen in our podcast Gold partner. As a gold partner, " +
                    "we offer the company Lotus model's digital preventive training to all employees" +
                    " of the company. The company receives a specific username and password. " +
                    "The training is aimed at managers and supervisors as well as all employees. " +
                    "The aim of the training is to increase knowledge about alcohol and drugs, " +
                    "everything is evaluated with digital tools and that there is the \"red button\"," +
                    " for advice and support. The license right to the training is 12 months. " +
                    "\nPrice: SEK 19,900 excluding VAT\n" +
                    "Order via email to: ",
            modifier = Modifier.fillMaxWidth(.9f),
            textAlign = TextAlign.Justify,
            style = LocalTextStyle.current.merge(
                TextStyle(
                    lineHeight = 1.5.em,
                )
            )
        )
        EmailLinkCompose("kontakt@lotusmodellen.se")

        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = "Alternative 2 Silver partner",
            modifier = Modifier.fillMaxWidth(.9f),
            textAlign = TextAlign.Justify,
            style = LocalTextStyle.current.merge(
                TextStyle(
                    lineHeight = 1.5.em,
                    fontWeight = FontWeight.Bold,
                )
            )
        )
        Spacer(modifier = Modifier.padding(3.dp))
        Text(
            text = "The company logo is visible in our podcast under Silver partner. " +
                    "\nPrice: 3,000 excl. VAT\n" +
                    "Order via email to: ",
            modifier = Modifier.fillMaxWidth(.9f),
            textAlign = TextAlign.Justify,
            style = LocalTextStyle.current.merge(
                TextStyle(
                    lineHeight = 1.5.em,
                )
            )
        )
        EmailLinkCompose("kontakt@lotusmodellen.se")

    }
}

@Preview(showBackground = true)
@Composable
fun PartnerSponserPreview(){
    PartnerSponser(rememberNavController() )
}