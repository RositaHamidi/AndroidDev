package com.example.tryggaklassenpod

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*


@Composable
fun OwnerPageContent(modifier: Modifier = Modifier){
    Text(
        text = "Hello!",
        modifier = modifier
    )
}

@Composable
fun TabbedPage() {
    var selectedTabIndex by remember { mutableStateOf(0) }

    // labels for the tabs
    val tabLabels = listOf("Tab 1", "Tab 2")

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

    }
}
