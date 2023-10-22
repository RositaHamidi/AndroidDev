package com.example.tryggaklassenpod.languages.contactus

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tryggaklassenpod.R

@Composable
fun ContactAr(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 0.dp)
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center),
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFF006971))
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End,
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
                text = "\u202Eهل لديك اهتمام باستخدام Trygga klassen في المدرسة؟ تواصل معنا عبر البريد الإلكتروني أو اتصل بنا وسنخبرك المزيد عنا وعن المفهوم والأدوات الرقمية التي نستخدمها.\u202C",
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End

            )

            Spacer(modifier = Modifier.height(16.dp))

            // Contact information
            Text(
                text = "\u202Eمعلومات التواصل:\u202C",
                color = Color(0xFF4DD9E6),
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.End
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 0.dp),
                horizontalArrangement = Arrangement.End,
            ) {
                Text(" po@lotusmodellen.se ", color = Color.White,)
                Text("\u202Eبريد إلكتروني: \u202C", color = Color.White,)
            }


            Text(
                text = "\u202Eهاتف: \u202C0706255750 ",
                color = Color.White,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
                    .fillMaxWidth()
                    .clickable {
                    // Handle the phone click action here
                },
                textAlign = TextAlign.End

            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ContactArPreview(){
    ContactAr()
}