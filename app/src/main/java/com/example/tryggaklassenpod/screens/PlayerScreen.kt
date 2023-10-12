package com.example.tryggaklassenpod.screens

import androidx.compose.foundation.clickable
import coil.compose.AsyncImage
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tryggaklassenpod.R
import com.example.tryggaklassenpod.dataClasses.Episode
import com.example.tryggaklassenpod.helperFunctions.toHoursMinuteSeconds
import com.example.tryggaklassenpod.ui.components.ErrorScreen

@Composable
fun PlayerScreen(
    episodeId: Int?,
    viewModel: PodcastViewModel,
    goBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (episodeId == null) {
        ErrorScreen(
            errorMessage = "Something went wrong. Please try again.",
            onRetry = { /* todo, refresh page on button click */ }
        )
    }
    val episode: Episode? = episodeId?.let { viewModel.getEpisodeById(it) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        TopBarBack(goBack = goBack)

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, bottom = 16.dp)

        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(10f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    episode?.imageUrl?.let {
                        EpisodeCoverImage(
                            imageUrl = it,
                            title = episode.title,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(28.dp))
            }
            item {
                Column(
                    modifier = Modifier
                        .weight(10f)
                ) {
                    Card {
                        Spacer(modifier = Modifier.height(14.dp))
                        episode?.title?.let { EpisodeTitle(title = it) }
                        episode?.episodeUrl?.let {
                            PlayerControllerArea(episodeUrl = it, duration = episode.duration, viewModel = viewModel)
                        }
                        Spacer(modifier = Modifier.height(28.dp))
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(14.dp))
                episode?.description?.let { EpisodeDescription(description = it) }
            }
            item {
                Spacer(modifier = Modifier.weight(1f))
//                episode?.comments?.let { CommentsSection(comments = it) }
//                Spacer(modifier = Modifier.weight(1f))

            }
        }
    }
}

@Composable
fun EpisodeTitle(title: String, modifier:Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Divider(
            modifier = Modifier
                .width(50.dp)
                .padding(top = 20.dp)
        )
    }
}

@Composable
fun EpisodeDescription(description: String, modifier:Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 8.dp),
    ) {
        Text(
            text = stringResource(R.string.episode_heading),
            style = MaterialTheme.typography.headlineSmall,
        )
        Text(
            text = description,
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(top = 8.dp),
        )
    }
}

@Composable
fun PlayerControllerArea(episodeUrl: String, duration: Int, viewModel: PodcastViewModel, modifier: Modifier = Modifier) {
    var sliderPosition by remember { mutableFloatStateOf(0F) }

    if (episodeUrl != viewModel.currentEpisode) {
        viewModel.stopEpisode()
        viewModel.currentEpisode = episodeUrl
    }

    var isPlaying by remember { mutableStateOf(false) }
    if (isPlaying) {
        viewModel.playEpisode(episodeUrl)
    } else {
        viewModel.pauseEpisode()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.primary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondary,
            ),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = (((sliderPosition*duration).toInt())).toHoursMinuteSeconds())
            Text(text = viewModel.getDuration().toHoursMinuteSeconds())
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.replay10),
                contentDescription = stringResource(R.string.replay10),
                modifier = Modifier
                    .size(45.dp)
                    .clickable {},
                tint = MaterialTheme.colorScheme.secondary
            )
            Icon(
                painter = if (isPlaying) {
                    painterResource(id = R.drawable.pause)
                } else {
                    painterResource(id = R.drawable.play)
                },
                contentDescription = stringResource(R.string.play_pause),
                modifier = Modifier
                    .size(70.dp)
                    .clickable {
//                        viewModel.isEpisodeSelected = true
                        isPlaying = !isPlaying
                    },
                tint = MaterialTheme.colorScheme.secondary,
            )
            Icon(
                painter = painterResource(id = R.drawable.forward30),
                contentDescription = stringResource(R.string.forward30),
                modifier = Modifier
                    .size(45.dp)
                    .clickable {},
                tint = MaterialTheme.colorScheme.secondary
            )
        }
        DisposableEffect(viewModel.currentEpisode) {
            onDispose {
//                viewModel.isPlaying = false
                viewModel.releasePlayer()
            }
        }
    }
}

@Composable
fun EpisodeCoverImage(
    imageUrl: String,
    title: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = title,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .sizeIn(maxWidth = 400.dp, maxHeight = 400.dp)
            .aspectRatio(1f)
            .clip(MaterialTheme.shapes.medium)
    )
}

@Composable
private fun TopBarBack(goBack: () -> Unit) {
    Row(Modifier.fillMaxWidth()) {
        IconButton(onClick = goBack) {
            Icon(
                painter = painterResource(R.drawable.arrowback),
                contentDescription = stringResource(R.string.back)
            )
        }
        Spacer(Modifier.weight(1f))
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PlayerScreenPreview() {
    PlayerScreen(
        episodeId = 0,
        viewModel = viewModel(),
        goBack = { },
    )
}