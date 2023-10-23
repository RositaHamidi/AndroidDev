package com.example.tryggaklassenpod.languages.contactus

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tryggaklassenpod.R
import com.example.tryggaklassenpod.languages.abouts.AppBarWithMenuAndOptions


@Composable
fun ContactFr(navController: NavController){
    Column (
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(),
    ){
        ContactsAppBar("French", navController)
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .background(Color(0xFF006971))
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        )  {
            Column(
                modifier = Modifier
                    .background(Color(0xFF004F55))
                    .padding(16.dp)
            ) {
                // Image
                Image(
                    painter = painterResource(id = R.drawable.po),
                    contentDescription = null, // Provide a suitable description
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Text content
                Text(
                    text = "Êtes-vous intéressé par l'utilisation de Tryggra Klassen à l'école ? Envoyez-nous un message, un e-mail ou appelez-nous et nous pouvons vous en dire plus sur nous, le concept et les outils numériques que nous utilisons.",
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Contact information
                Text(
                    text = "Contact:",
                    color = Color(0xFF4DD9E6)
                )
                Text(
                    text = "E-mail: p-o@lotusmodellen.se",
                    color = Color.White,
                    modifier = Modifier.clickable {
                        // Handle the email click action here
                    }
                )
                Text(
                    text = "Téléphone: +46706255750",
                    color = Color.White,
                    modifier = Modifier.clickable {
                        // Handle the phone click action here
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ContactFrPreview(){
    ContactFr(rememberNavController())
}