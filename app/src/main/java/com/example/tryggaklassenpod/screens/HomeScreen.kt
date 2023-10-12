package com.example.tryggaklassenpod.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tryggaklassenpod.dataClasses.Episode
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.tryggaklassenpod.veiwModel.GeneralViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, homeViewModel: GeneralViewModel) {

    val episodes by homeViewModel.episodeLiveData.observeAsState(emptyList())
    val isRefreshing by homeViewModel.isRefreshing.observeAsState(false)

    val onRefresh: () -> Unit = {
        homeViewModel.refreshing()
    }

    var menuExpanded by remember { mutableStateOf(false) }
    val myContext = LocalContext.current

    if (!episodes.isEmpty()) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { onRefresh() }
        ) {
            MaterialTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Trygga Klassen",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth(),
                                    style = TextStyle(
                                        color = Color.Blue,
                                        fontFamily = FontFamily.Monospace,
                                        fontSize = 30.sp,
                                    )
                                )
                            },
                            actions = {
                                IconButton(onClick = { menuExpanded = !menuExpanded }) {
                                    Icon(Icons.Filled.MoreVert, contentDescription = "")
                                }
                                DropdownMenu(
                                    expanded = menuExpanded,
                                    onDismissRequest = { menuExpanded = false }) {

                                    DropdownMenuItem(
                                        text = { Text(text = "Login") },
                                        onClick = { /*TODO*/ }  //login page will be added
                                    )
                                    DropdownMenuItem(
                                        text = { Text(text = "About us") },
                                        onClick = { navController.navigate(Screen.AboutScreen.route) }
                                    )

                                }
                            }

                        )
                    },
                    content = {
                        Box (
                            modifier = Modifier.padding(it)
                        ){
                            showEpisodesList(episodes)
                        }

                    }
                )
            }

        }
    } else {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Loading...",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                )
        }

    }
}



@Composable
fun showEpisodesList(episodes: List<Episode>){
    LazyColumn (modifier = Modifier.padding(10.dp)){
        items(episodes){episode ->
        EpisodeListItem(episode)
        }
    }
}


@Composable
fun EpisodeListItem(episode: Episode){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        AsyncImage(
            model = episode.imageUrl,
            contentDescription = "thumbnail")
        Text(text = episode.title,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            )
        Text(text = episode.description)

    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(rememberNavController(), GeneralViewModel() )
}



