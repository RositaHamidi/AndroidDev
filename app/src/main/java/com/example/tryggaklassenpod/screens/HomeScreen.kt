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
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tryggaklassenpod.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Column {
        TopAppBar(
            modifier = Modifier.background(Color.Transparent),
            title = { Text(
                text = "Trygga Klassen",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )},
            navigationIcon = {
                // Place your navigation icon (menu icon) here
                IconButton(onClick = { /* Handle menu icon click */ }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                }
            },
        )

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.imageplaceholder),
                contentDescription = "sample",
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "Podcast 1")
            Spacer(modifier = Modifier.padding(15.dp))

            Image(
                painter = painterResource(id = R.drawable.imageplaceholder),
                contentDescription = "sample"
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "Podcast 2")
            Spacer(modifier = Modifier.padding(15.dp))

            Image(
                painter = painterResource(id = R.drawable.imageplaceholder),
                contentDescription = "sample"
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "Podcast 3")
            Spacer(modifier = Modifier.padding(15.dp))

            Image(
                painter = painterResource(id = R.drawable.imageplaceholder),
                contentDescription = "sample"
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "Podcast 4")
            Spacer(modifier = Modifier.padding(15.dp))

            Image(
                painter = painterResource(id = R.drawable.imageplaceholder),
                contentDescription = "sample"
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "Podcast 5")
            Spacer(modifier = Modifier.padding(15.dp))
        }

        BottomAppBar {
            /* for later */
        }

    }

}
