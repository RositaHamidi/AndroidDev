package com.example.tryggaklassenpod.screens

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.times
import com.example.tryggaklassenpod.R


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
    // test empty list
    // var admins by remember { mutableStateOf(mutableListOf<Map<String, String>>()) }
    var admins by remember {
        mutableStateOf(
            mutableListOf(
                mapOf("name" to "John", "password" to "password123", "school" to "School A"),
                mapOf("name" to "Alice", "password" to "alicepass", "school" to "School B"),
                mapOf("name" to "Bob", "password" to "bobpassword", "school" to "School C"),
                mapOf("name" to "Sarah", "password" to "sarahpassword", "school" to "School A"),
                mapOf("name" to "Jane", "password" to "janepassword", "school" to "School D"),
                mapOf("name" to "Sami", "password" to "samipassword", "school" to "School C")
            )
        )
    }
    Column(){
        Text(
            text = "Current admins",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small), top = dimensionResource(R.dimen.padding_small))
        )
        Box(
            modifier = Modifier
                .height(400.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF006971), shape = RoundedCornerShape(15.dp))
                .border(BorderStroke(2.dp, SolidColor(Color(0xFF006971))), shape = RoundedCornerShape(15.dp))
        ){
            if (admins.isEmpty()) {
                Text("No admins yet", modifier = Modifier.padding(16.dp))
            } else {
                LazyColumn() {
                    items(admins) { admin ->
                        AdminItem(
                            admin = admin,
                            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small), bottom = dimensionResource(R.dimen.padding_small), start = dimensionResource(R.dimen.padding_medium), end = dimensionResource(R.dimen.padding_medium))
                        )
                    }
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
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                AdminInformation(admin["name"], admin["school"])
                Spacer(Modifier.weight(1f))
                AdminItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                )
            }
            if (expanded) {
                AdminOptions(modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.padding_medium),
                    top = dimensionResource(R.dimen.padding_small),
                    bottom = dimensionResource(R.dimen.padding_medium),
                    end = dimensionResource(R.dimen.padding_medium)
                ))
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
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
            )
        }
        if (adminSchool != null) {
            Text(
                text = adminSchool,
                style = MaterialTheme.typography.titleMedium,
                //
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
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
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_small))
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




