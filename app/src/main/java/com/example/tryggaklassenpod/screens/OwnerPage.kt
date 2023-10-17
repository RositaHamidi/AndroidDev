package com.example.tryggaklassenpod.screens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tryggaklassenpod.R
import com.example.tryggaklassenpod.dataClasses.AdminDataClass
import com.example.tryggaklassenpod.helperFunctions.ValidatePassword
import com.example.tryggaklassenpod.sealed.InsertAdminDataState
import com.example.tryggaklassenpod.sealed.FetchingAdminDataState
import com.example.tryggaklassenpod.viewModels.OwnerPageViewModel


@Composable
fun OwnerPageContent(modifier: Modifier = Modifier){
    val viewModel: OwnerPageViewModel = viewModel()
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ){
        TabbedPage(viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabbedPage(viewModel: OwnerPageViewModel) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    //val snackState = remember { SnackbarHostState() }
    //val scope = rememberCoroutineScope()

    // Labels for the tabs
    val tabLabels = listOf("Owner", "Admin")
    Scaffold(
        //snackbarHost = { SnackbarHost(snackState) },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
                    .padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
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
                    0 -> TabContent1(viewModel)
                    1 -> TabContent2()
                }
                /*Button(
                    onClick = {
                        scope.launch {
                            snackState.showSnackbar("hello")
                        }
                    }
                ){
                    Text( "click")
                }*/
            }
        }
    )
}


@Composable
fun TabContent1(viewModel: OwnerPageViewModel) {
    // test empty list
    // var admins by remember { mutableStateOf(mutableListOf<Map<String, String>>()) }
    val isDarkTheme = isSystemInDarkTheme()

    val backgroundColor = if (isDarkTheme) {
        Color(0xFF4DD8E5) // Dark theme background color
    } else {
        Color(0xFF006971) // Light theme background color
    }

    Column(){
        Text(
            text = "Current admins",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(
                    start = dimensionResource(R.dimen.padding_small),
                    top = dimensionResource(R.dimen.padding_small)
                )
        )
        Box(
            modifier = Modifier
                .height(400.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(backgroundColor, shape = RoundedCornerShape(15.dp))
                .border(
                    BorderStroke(2.dp, SolidColor(backgroundColor)),
                    shape = RoundedCornerShape(15.dp)
                )
        ){
            when (val result = viewModel.response.value) {
                is FetchingAdminDataState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is FetchingAdminDataState.Success -> {
                    ShowLazyList(result.data)
                }
                is FetchingAdminDataState.Failure -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = result.message,
                            fontSize = 16.sp,
                        )
                    }
                }
                else -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Error Fetching data",
                            fontSize = 16.sp,
                        )
                    }
                }
            }
        }
        Text(
            text = "Add a new admin",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(
                    start = dimensionResource(R.dimen.padding_small),
                    top = dimensionResource(R.dimen.padding_small)
                )
        )
        Card(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(
                    BorderStroke(2.dp, SolidColor(Color(0xFF006971))),
                    shape = RoundedCornerShape(15.dp)
                )
        ){
            AddAnAdminSection(viewModel)

        }
    }


}

@Composable
fun ShowLazyList(admins: MutableList<AdminDataClass>) {
    LazyColumn {
        items(admins) { admin ->
            AdminItem(admin,
                modifier = Modifier
                .padding(
                    top = dimensionResource(R.dimen.padding_small),
                    bottom = dimensionResource(R.dimen.padding_small),
                    start = dimensionResource(R.dimen.padding_medium),
                    end = dimensionResource(R.dimen.padding_medium)
                )
            )
        }
    }
}

@Composable
fun AdminItem(
    admin: AdminDataClass,
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
                //Log.i(TAG, "Hi " + admin.username)
                AdminInformation(admin.username, admin.school)
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
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
            )
        }
        if (adminSchool != null) {
            Text(
                text = adminSchool,
                style = MaterialTheme.typography.titleSmall,
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
                Text("Edit Admin info")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAnAdminSection(viewModel: OwnerPageViewModel) {
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var school by remember { mutableStateOf("") }
    var podcastPoster by remember { mutableStateOf(true) }
    var podcastEditor by remember { mutableStateOf(true) }
    var commentReviewer by remember { mutableStateOf(true) }
    var permissions by remember { mutableStateOf(mapOf("podcastPoster" to false, "podcastEditor" to false, "commentReviewer" to false) )}

    var passValid = ValidatePassword()
    var showBadPass by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = school,
            onValueChange = { school = it },
            label = { Text("School") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        podcastPoster = SentenceSwitch("Can post podcasts")
        permissions = permissions + mapOf("podcastPoster" to podcastPoster)
        //Log.i(TAG, "Hi " + podcastPoster)

        podcastEditor = SentenceSwitch("Can edit podcasts")
        permissions = permissions + mapOf("podcastEditor" to podcastEditor)
        //Log.i(TAG, "Hi " + podcastEditor)

        commentReviewer = SentenceSwitch("Can review comments")
        permissions = permissions + mapOf("commentReviewer" to commentReviewer)
        //Log.i(TAG, "Hi " + permissions)



        Button(
            onClick = {
                val passStatus = passValid.execute(password)
                if (passStatus){
                    viewModel.addNewAdmin(name, school, password, permissions)
                    showBadPass = false
                    name = ""
                    password = ""
                    school = ""
                }
                else{
                    showBadPass = true
                }


            }
        ) {
            Text("Add Admin")
        }

        Spacer(modifier = Modifier.height(8.dp))
        if(showBadPass){
            Text(
                text = "Please make sure your password is:\n- At least 8 characters long\n- Has at least 1 capital letter\n- Has at least one number",
                color = Color.Red)
        }

        // Display the message under the button
        val message = viewModel.message.value
        if (message is InsertAdminDataState.Success) {
            Text(
                text = (message).message,
                color = Color(0xFF46B44A) // Color for success message
            )
        } else if (message is InsertAdminDataState.Failure) {
            Text(
                text = (message).error,
                color = Color.Red // Color for error message
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SentenceSwitch(sentence:String, modifier: Modifier = Modifier):Boolean {
    var checked by remember { mutableStateOf(true) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(R.dimen.padding_large),
                end = 60.dp
            ),
        )
    {
        Text(text = sentence,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = dimensionResource(R.dimen.padding_small)),
            )
        Switch(
            checked = checked,
            onCheckedChange = {
                checked = it
            },
            thumbContent = if (checked) {
                {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                    )
                }
            } else {
                null
            }
        )
    }
    return checked
}
fun checkPass(pass:String){
    //Check password
}


@Composable
fun TabContent2() {
    Text(text = "Tab Content 2")
}