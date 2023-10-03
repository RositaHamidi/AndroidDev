package com.example.tryggaklassenpod.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tryggaklassenpod.R
import com.example.tryggaklassenpod.ui.theme.md_theme_dark_primaryContainer

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    Column (
        modifier = Modifier.background(Color.Transparent)
    ){
        TopAppBar(

            title = { Text(
                text = "Trygga Klassen",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )},
            navigationIcon = {
                IconButton(
                    onClick = { /* Handle menu icon click */ },
                    ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                        )
                }
            },

            )

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(5.dp)
                ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id = R.drawable.imageplaceholder),
                    contentDescription = "sample",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(40.dp)

                    )
                Spacer(modifier = Modifier.padding(5.dp))
                Column (verticalArrangement = Arrangement.Center){
                    Text(text = "Podcast 1",
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxSize())
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(text = "Details / description of each podcast",
                        color = Color.White,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxSize())
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id = R.drawable.imageplaceholder),
                    contentDescription = "sample",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Column (verticalArrangement = Arrangement.Center){
                    Text(text = "Podcast 2",
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxSize())
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(text = "Details / description of each podcast",
                        color = Color.White,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxSize())
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id = R.drawable.imageplaceholder),
                    contentDescription = "sample",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Column (verticalArrangement = Arrangement.Center){
                    Text(text = "Podcast 3",
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxSize())
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(text = "Details / description of each podcast",
                        color = Color.White,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxSize())
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id = R.drawable.imageplaceholder),
                    contentDescription = "sample",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Column (verticalArrangement = Arrangement.Center){
                    Text(text = "Podcast 4",
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxSize())
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(text = "Details / description of each podcast",
                        color = Color.White,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxSize())
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id = R.drawable.imageplaceholder),
                    contentDescription = "sample",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Column (verticalArrangement = Arrangement.Center){
                    Text(text = "Podcast 5",
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxSize())
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(text = "Details / description of each podcast",
                        color = Color.White,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxSize())
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id = R.drawable.imageplaceholder),
                    contentDescription = "sample",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Column (verticalArrangement = Arrangement.Center){
                    Text(text = "Podcast 6",
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxSize())
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(text = "Details / description of each podcast",
                        color = Color.White,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxSize())
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id = R.drawable.imageplaceholder),
                    contentDescription = "sample",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Column (verticalArrangement = Arrangement.Center){
                    Text(text = "Podcast 7",
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxSize())
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(text = "Details / description of each podcast",
                        color = Color.White,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxSize())
                }
            }

        }

        BottomAppBar (
        ){
            /* for later */
        }

    }

}

@Preview(showBackground = true)
@Composable
fun homeScreenPreview(){
    HomeScreen()
}