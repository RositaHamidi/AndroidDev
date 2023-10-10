package com.example.tryggaklassenpod.screens

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.res.stringResource


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
    var selectedTabIndex by remember { mutableIntStateOf(0) }

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

//Future with database list
/*
@Composable
fun TabContent1() {
    Column(){
        LazyColumn() {
            items(admins) { admin ->
                AdminItem(
                    admin = admin,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}
*/
@Composable
fun TabContent1() {
    var admins by remember { mutableStateOf(mutableListOf<Map<String, String>>()) }
    Column(){
        if (admins.isEmpty()) {
            Text("No admins yet", modifier = Modifier.padding(16.dp))
        } else {
            LazyColumn() {
                items(admins) { admin ->
                    AdminItem(
                        admin = admin,
                        modifier = Modifier.padding()
                    )
                }
            }
        }
    }
}

@Composable
fun AdminItem(
    admin: Map<String, String>,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AdminInformation(admin["name"], admin["school"])
                Spacer(Modifier.weight(1f))
                AdminItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                )
            }
            if (expanded) {
                AdminOptions()
            }
        }
    }
}

@Composable
fun AdminInformation(
    adminName: String?,
    adminSchool: String?,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        if (adminName != null) {
            Text(
                text = adminName,
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding()
            )
        }
        if (adminSchool != null) {
            Text(
                text = adminSchool,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
@Composable
private fun AdminItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = "See more or less options for an Admin",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun AdminOptions(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Options",
            style = MaterialTheme.typography.labelSmall
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    // Remove the admin from database
                }
            ) {
                Text("Delete Admin")
            }
            Button(
                onClick = {
                    // Edit the admin in database
                }
            ) {
                Text("Edit Admin")
            }
        }
    }
}

@Composable
fun TabContent2() {
    Text(text = "Tab Content 2")
}




