package com.example.tryggaklassenpod.screens

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.tryggaklassenpod.R
import com.example.tryggaklassenpod.dataClasses.AdminDataClass
import com.example.tryggaklassenpod.helperFunctions.PasswordHash
import com.example.tryggaklassenpod.helperFunctions.ValidatePassword
import com.example.tryggaklassenpod.sealed.InsertAdminDataState
import com.example.tryggaklassenpod.sealed.FetchingAdminDataState
import com.example.tryggaklassenpod.sealed.FetchingAdminIDsState
import com.example.tryggaklassenpod.viewModels.OwnerPageViewModel
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import com.example.tryggaklassenpod.sealed.DeleteAdminState
import com.example.tryggaklassenpod.sealed.UpdateAdminState


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OwnerPageContent(modifier: Modifier = Modifier, navController: NavHostController){
    val viewModel: OwnerPageViewModel = viewModel()
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ){
        TabbedPage(viewModel, navController)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabbedPage(viewModel: OwnerPageViewModel, navController: NavHostController) {
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
                    1 -> TabContent2(navController)
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


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TabContent1(viewModel: OwnerPageViewModel) {
    // test empty list
    // var admins by remember { mutableStateOf(mutableListOf<Map<String, String>>()) }
    var adminIds by remember { mutableStateOf(mutableListOf<String>()) }
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
                //.background(backgroundColor, shape = RoundedCornerShape(15.dp))
                .border(
                    BorderStroke(2.dp, SolidColor(backgroundColor)),
                    shape = RoundedCornerShape(15.dp)
                )
        ){
            when (val result2 = viewModel.fetchIDresponse.value) {
                is FetchingAdminIDsState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is FetchingAdminIDsState.Success -> {
                    adminIds = result2.data
                }
                is FetchingAdminIDsState.Failure -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = result2.message,
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
            when (val result = viewModel.fetchAdminresponse.value) {
                is FetchingAdminDataState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is FetchingAdminDataState.Success -> {
                    ShowLazyList(viewModel, result.data, adminIds)
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ShowLazyList(viewModel: OwnerPageViewModel, admins: MutableList<AdminDataClass>, adminIds: MutableList<String>) {
    LazyColumn {
        itemsIndexed(admins) { index, admin ->
            val adminId = adminIds.getOrNull(index) // Get the corresponding admin ID
            AdminItem(viewModel, admin, adminId, // Pass both admin and adminId to your AdminItem composable
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AdminItem(
    viewModel: OwnerPageViewModel,
    admin: AdminDataClass,
    adminId: String?,
    modifier: Modifier = Modifier
) {
    var keepUpdating by remember { mutableStateOf(false)}

    var expanded by remember { mutableStateOf(false) }
    var editable by remember { mutableStateOf(false)}
    var updatedInfo by remember { mutableStateOf(mapOf<String, Any>()) }
    //var updatedInfo = remember { mutableStateOf(mapOf("name" to "", "password" to "", "school" to ""))}
    var updatedPermissions by remember { mutableStateOf(mapOf("podcastPoster" to false, "podcastEditor" to false, "commentReviewer" to false) )}
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
                val pairReceived = AdminInformation(editable, admin.username!!, admin.school!!,
                    admin.password!!
                )
                updatedInfo = pairReceived.first
                keepUpdating = pairReceived.second
                Spacer(Modifier.weight(1f))
                AdminItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                    editable
                )
            }
            if (expanded) {
                updatedPermissions = ViewAdminPermissions(editable, admin.permissions)
                if (adminId != null) {
                    editable = AdminOptions( keepUpdating, adminId, updatedPermissions, updatedInfo, viewModel,  modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        bottom = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium)
                    ))
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminInformation(
    editable:Boolean,
    adminName: String,
    adminSchool: String,
    adminPass: Map<String, String>,
    modifier: Modifier = Modifier
): Pair<Map<String, Any>, Boolean> {
    var name by remember { mutableStateOf(adminName) }
    var school by remember { mutableStateOf(adminSchool) }

    var newName by remember { mutableStateOf("") }
    var newPass by remember { mutableStateOf("") }
    var newSchool by remember { mutableStateOf("")}

    var showBadPass by remember { mutableStateOf(false) }

    var newInfo by remember { mutableStateOf<Map<String, Any>>((
            mapOf(
                "name" to adminName,
                "password" to adminPass,
                "school" to adminSchool
            ))) }

    Column(modifier = modifier) {
        if(!editable){
            if (adminName != null) {
                Row(verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "Name: " + name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
                    )
                }

            }
            if (adminSchool != null) {
                Text(
                    text = "School: "+ school,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
                )
            }
        } else{
            TextField(
                value = name,
                onValueChange = {
                    newName = it
                    name = it
                    newInfo = if(name != null){
                        newInfo + mapOf("name" to newName)
                    } else {
                        newInfo + mapOf("name" to adminName)
                    }

                },
                label = { Text("Name") }
            )


            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = school,
                onValueChange = {
                    newSchool = it
                    school = it
                    newInfo =
                        if(school != null){
                            newInfo + mapOf("school" to newSchool)
                        } else {
                            newInfo + mapOf("school" to adminSchool)
                        }
                                },
                label = { Text("School") }
            )

            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = newPass,
                onValueChange = {
                    newPass = it
                    val passValid = ValidatePassword()
                    val passStatus = passValid.execute(newPass)
                    if(newPass.isNullOrEmpty()){
                        newInfo = newInfo + mapOf("password" to adminPass)
                        showBadPass = false
                    } else if(newPass != null && passStatus){
                        val hashedPass = PasswordHash.hashPassword(newPass)
                        newInfo = newInfo + mapOf("password" to mapOf("salt" to hashedPass.second, "hashedPass" to hashedPass.first))
                        showBadPass = false
                    } else if(newPass != null && !passStatus){
                        showBadPass = true
                    }
                },
                label = { Text("New Password") }
            )
        }
        if(showBadPass){
            Text(
                text = "Please make sure your password is:\n" +
                        "    - At least 8 characters long\n" +
                        "    - Has at least one capital letter\n" +
                        "    - Has at least one number",
                color = Color.Red,
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium), top = dimensionResource(R.dimen.padding_medium)))
        }

    }

    return Pair(newInfo, showBadPass)
}

@Composable
private fun AdminItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    editable: Boolean,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick =
        if (editable) {
        /* Do nothing when editable is true */ {}
        } else {
            onClick
        },
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
fun ViewAdminPermissions(editable:Boolean, adminPermissions:Map<String, Boolean>?): Map<String, Boolean> {


    val podcastPoster by remember { mutableStateOf(adminPermissions?.get("podcastPoster")) }
    val podcastEditor by remember { mutableStateOf(adminPermissions?.get("podcastEditor")) }
    val commentReviewer by remember { mutableStateOf(adminPermissions?.get("commentReviewer"))}

    var newPodcastPosterState by remember { mutableStateOf(true) }
    var newPodcastEditorState by remember { mutableStateOf(true) }
    var newCommentReviewerState by remember { mutableStateOf(true)}

    var permissions by remember { mutableStateOf(mapOf("podcastPoster" to false, "podcastEditor" to false, "commentReviewer" to false) )}

    newPodcastPosterState = podcastPoster?.let { SentenceSwitch(it, editable, "Can post podcasts") }!!
    permissions = permissions + mapOf("podcastPoster" to newPodcastPosterState)

    newPodcastEditorState = podcastEditor?.let { SentenceSwitch(it, editable,  "Can edit podcasts") }!!
    permissions = permissions + mapOf("podcastEditor" to newPodcastEditorState)

    newCommentReviewerState = commentReviewer?.let { SentenceSwitch(it, editable,  "Can review comments") }!!
    permissions = permissions + mapOf("commentReviewer" to newCommentReviewerState)
    Spacer(modifier = Modifier.height(8.dp))
    return permissions
}
@Composable
fun AdminOptions(
    keepUpdating: Boolean,
    adminId:String,
    adminPermissions:Map<String, Boolean>?,
    adminInfo:Map<String, Any>?,
    viewModel: OwnerPageViewModel,
    modifier: Modifier = Modifier
):Boolean {
    var editable by remember { mutableStateOf(false)}
    var deleteStatusMessage by remember { mutableStateOf(false) }
    var updateStatusMessage by remember { mutableStateOf(false) }

    var buttonText by remember { mutableStateOf("Enable editing info") }
    Log.i(TAG, "Hi " + adminPermissions)
    Log.i(TAG, "Hi " + adminInfo)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        if (!keepUpdating) {
                            if (editable) {
                                buttonText = "Edit info"
                                editable = false
                                if (adminPermissions != null) {
                                    viewModel.editAdminInfo(
                                        adminId,
                                        adminInfo?.get("name")!! as String,
                                        adminInfo["password"]!! as Map<String, String>,
                                        adminInfo["school"]!! as String,
                                        adminPermissions
                                    )
                                }
                                updateStatusMessage = true
                                deleteStatusMessage = false // Reset delete message flag
                            } else {
                                editable = true
                                buttonText = "Submit changes"
                                updateStatusMessage = false
                                deleteStatusMessage = false // Reset delete message flag
                            }
                        }
                    }
                ) {
                    Text(buttonText)
                }
                Button(
                    onClick = {
                        viewModel.deleteAdminById(adminId)
                        deleteStatusMessage = true
                        updateStatusMessage = false
                    }
                ) {
                    Text("Delete Admin")
                }
            }
            //val updateMessage = viewModel.updateMessage.value
            /*if(updateStatusMessage) {
                if (updateMessage is UpdateAdminState.Success){
                    val toast = (updateMessage).message?.let { CallToast(sentence = it) }
                    toast?.show()
                } else if (updateMessage is UpdateAdminState.Failure){
                    val toast = (updateMessage).error?.let { CallToast(sentence = it) }
                    toast?.show()
                }
            }*/

            // Display update or delete message


            //val deleteMessage = viewModel.deleteMessage.value
            /*if(deleteStatusMessage) {
                if (deleteMessage is DeleteAdminState.Success) {
                    val toast = CallToast(sentence = (deleteMessage).message)
                    toast.show()
                } else if (deleteMessage is DeleteAdminState.Failure) {
                    val toast = CallToast(sentence = (deleteMessage).error)
                    toast.show()
                }
            }*/

        }
        if (updateStatusMessage) {
            val updateMessage = viewModel.updateMessage.value
            if (updateMessage is UpdateAdminState.Success) {
                Text(
                    text = updateMessage.message ?: "",
                    color = Color(0xFF46B44A) // Color for success message
                )
            } else if (updateMessage is UpdateAdminState.Failure) {
                Text(
                    text = updateMessage.error ?: "",
                    color = Color.Red // Color for error message
                )
            }
        }

        if (deleteStatusMessage) {
            val deleteMessage = viewModel.deleteMessage.value // Use a separate LiveData for delete messages
            if (deleteMessage is DeleteAdminState.Success) {
                Text(
                    text = deleteMessage.message,
                    color = Color(0xFF46B44A) // Color for success message
                )
            } else if (deleteMessage is DeleteAdminState.Failure) {
                Text(
                    text = deleteMessage.error,
                    color = Color.Red // Color for error message
                )
            }
        }
    }
    return editable
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAnAdminSection(viewModel: OwnerPageViewModel) {
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var school by remember { mutableStateOf("") }
    val podcastPoster by remember { mutableStateOf(true) }
    val podcastEditor by remember { mutableStateOf(true) }
    val commentReviewer by remember { mutableStateOf(true) }
    var newPodcastPosterState by remember { mutableStateOf(true) }
    var newPodcastEditorState by remember { mutableStateOf(true) }
    var newCommentReviewerState by remember { mutableStateOf(true)}
    var permissions by remember { mutableStateOf(mapOf("podcastPoster" to false, "podcastEditor" to false, "commentReviewer" to false) )}

    val passValid = ValidatePassword()
    var showBadPass by remember { mutableStateOf(false) }
    var InsertionStatusMessage by remember { mutableStateOf(false) } // Track if the message is shown

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
        Spacer(modifier = Modifier.height(20.dp))

        newPodcastPosterState = podcastPoster.let { SentenceSwitch(it, true,"Can post podcasts") }!!
        permissions = permissions + mapOf("podcastPoster" to newPodcastPosterState)

        newPodcastEditorState = podcastEditor.let { SentenceSwitch(it, true,"Can edit podcasts") }!!
        permissions = permissions + mapOf("podcastEditor" to newPodcastEditorState)

        newCommentReviewerState = commentReviewer.let { SentenceSwitch(it, true, "Can review comments") }!!
        permissions = permissions + mapOf("commentReviewer" to newCommentReviewerState)

        /*podcastPoster = SentenceSwitch("Can post podcasts")
        permissions = permissions + mapOf("podcastPoster" to podcastPoster)
        //Log.i(TAG, "Hi " + podcastPoster)

        podcastEditor = SentenceSwitch("Can edit podcasts")
        permissions = permissions + mapOf("podcastEditor" to podcastEditor)
        //Log.i(TAG, "Hi " + podcastEditor)

        commentReviewer = SentenceSwitch("Can review comments")
        permissions = permissions + mapOf("commentReviewer" to commentReviewer)
        //Log.i(TAG, "Hi " + permissions)*/



        Button(
            onClick = {
                val passStatus = passValid.execute(password)
                if (passStatus){
                    val hashedPass = PasswordHash.hashPassword(password)
                    Log.i(TAG, "Hi " + hashedPass.first)
                    viewModel.addNewAdmin(name, school, mapOf("salt" to hashedPass.second, "hashedPass" to hashedPass.first), permissions)
                    showBadPass = false
                    name = ""
                    password = ""
                    school = ""
                    InsertionStatusMessage = true
                }
                else{
                    showBadPass = true
                    InsertionStatusMessage = false
                }
            }
        ) {
            Text("Add Admin")
        }

        Spacer(modifier = Modifier.height(8.dp))
        if(showBadPass){
            Text(
                text = "Please make sure your password is:\n" +
                        "- At least 8 characters long\n" +
                        "- Has at least one capital letter\n" +
                        "- Has at least one number",
                color = Color.Red)
        }
        if(InsertionStatusMessage) {
            // Display the toast with a message
            val message = viewModel.message.value
            if (message is InsertAdminDataState.Success) {
                val toast = CallToast(sentence = (message).message)
                toast.show()
            } else if (message is InsertAdminDataState.Failure) {
                val toast = CallToast(sentence = (message).error)
                toast.show()
            }
        }
        /*if(InsertionStatusMessage) {
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
        }*/
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SentenceSwitch(startingState:Boolean, enableSwitcher:Boolean, sentence:String, modifier: Modifier = Modifier):Boolean {
    val isDarkTheme = isSystemInDarkTheme()

    var checked by remember { mutableStateOf(startingState) }
    var switchColor by remember { mutableStateOf(Color(0xFF006971).copy(0.5f)) }

    if(!enableSwitcher && isDarkTheme){
        switchColor = Color(0xFF4DD8E5).copy(0.4f)
    } else if(enableSwitcher && isDarkTheme ){
        switchColor = Color(0xFF4DD8E5)
    } else if(!enableSwitcher && !isDarkTheme ){
        switchColor= Color(0xFF006971).copy(0.5f)
    } else {
        switchColor= Color(0xFF006971)
    }
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
                if (enableSwitcher) {
                    checked = it
                }

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
            },
            colors = SwitchDefaults.colors(
                checkedTrackColor = switchColor,
            )

        )
    }
    return checked
}
@SuppressLint("ShowToast")
@Composable
fun CallToast(sentence:String):Toast{
    val context = LocalContext.current
    val toast = Toast.makeText(
        context, sentence,
        Toast.LENGTH_LONG)
    return toast
}


@Composable
fun TabContent2(controller:NavController) {
    AdminScreen(controller)
}