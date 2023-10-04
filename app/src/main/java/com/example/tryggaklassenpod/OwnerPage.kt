package com.example.tryggaklassenpod

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*


@Composable
fun OwnerPageContent(modifier: Modifier = Modifier){
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ){
        TabbedPage()
    }
}

@Composable
fun TabbedPage() {
    var selectedTabIndex by remember { mutableStateOf(0) }

    // Labels for the tabs
    val tabLabels = listOf("Owner", "Admin")

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Create the TabRow with tabs
        TabRow(
            selectedTabIndex = selectedTabIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
                )
            }
        ) {
            // Create tabs
            tabLabels.forEachIndexed { index, label ->
                Tab(
                    text = { Text(text = label) },
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }
                )
            }
        }

        // Display the content based on the selected tab
        when (selectedTabIndex) {
            0 -> TabContent1()
            1 -> TabContent2()
        }
    }
}

@Composable
fun TabContent1() {
    Text(text = "Tab Content 1")
}

@Composable
fun TabContent2() {
    Text(text = "Tab Content 2")
}

