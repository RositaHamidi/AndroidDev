package com.example.tryggaklassenpod.languages.contactus

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tryggaklassenpod.R

@Composable
fun ContactAr(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFF006971))
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
                text = "هل لديك اهتمام باستخدام Trygga klassen في المدرسة؟ تواصل معنا عبر البريد الإلكتروني أو اتصل بنا وسنخبرك المزيد عنا وعن المفهوم والأدوات الرقمية التي نستخدمها.",
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Contact information
            Text(
                text = "معلومات التواصل:",
                color = Color(0xFF4DD9E6)
            )
            Text(
                text = "بريد إلكتروني: p-o@lotusmodellen.se",
                color = Color.White,
                modifier = Modifier.clickable {
                    // Handle the email click action here
                }
            )
            Text(
                text = "هاتف: +46706255750",
                color = Color.White,
                modifier = Modifier.clickable {
                    // Handle the phone click action here
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ContactArPreview(){
    ContactAr()
}