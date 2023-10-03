package com.example.tryggaklassenpod.data

import java.util.Date
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds


val episodesList: List<Episode> = listOf(
    Episode(
        imageUrl = "https://cdn.pixabay.com/audio/2022/05/27/23-51-43-941_200x200.jpg",
        audio = "https://cdn.pixabay.com/download/audio/2022/05/27/audio_1808fbf07a.mp3?filename=lofi-study-112191.mp3",
        duration = (2.minutes + 27.seconds),
        title = "Episode Title 1",
        description = "Discover thrilling adventures and unexpected twists in this gripping novel that keeps you on the edge of your seat until the very last page.",
        comments = listOf(
            Comment(
                comment = "OMG crazy story, this sucks",
                author = "HeyThere",
                createdAt = Date(1633873800000L),
                approved = false,
                likes = 0
            ),
            Comment(
                comment = "Great story!",
                author = "Reader123",
                createdAt = Date(1633873810000L),  // A different timestamp for the second comment
                approved = true,
                likes = 10
            ),
            Comment(
                comment = "Interesting plot twists!",
                author = "BookLover456",
                createdAt = Date(1633873820000L),
                approved = true,
                likes = 5
            ),
            Comment(
                comment = "Looking forward to the sequel!",
                author = "ExcitedReader",
                createdAt = Date(1633873830000L),
                approved = true,
                likes = 7
            )
        ),
        categories = listOf(
            "Adventure",
            "Mystery",
            "Horror",
            "Drama",
            "Biography",
            "Comedy",
            "Self-Help",
            "Young Adult"
        )
    ),
    Episode(
        imageUrl = "https://cdn.pixabay.com/audio/2023/03/02/23-27-55-753_200x200.jpg",
        audio = "https://cdn.pixabay.com/download/audio/2023/03/02/audio_501a4aea8a.mp3?filename=genres-hiphop-lofi-141320.mp3",
        duration = (12.minutes + 45.seconds),
        title = "Episode Title 2",
        description = "Immerse yourself in a world of magic and wonder as you follow a young hero's quest to save the kingdom from an ancient evil threatening its existence.",
        comments = listOf(),
        categories = listOf()
    ),
    Episode(
        imageUrl = "https://cdn.pixabay.com/audio/2022/09/02/21-42-13-13_200x200.jpg",
        audio = "https://cdn.pixabay.com/download/audio/2022/09/02/audio_72502a492a.mp3?filename=empty-mind-118973.mp3",
        duration = (2.minutes + 55.seconds),
        title = "Episode Title 3",
        description = "In a post-apocalyptic wasteland, a group of survivors must navigate danger and betrayal to find a new home, hoping for a chance at rebuilding society.",
        comments = listOf(),
        categories = listOf()
    ),
    Episode(
        imageUrl = "https://cdn.pixabay.com/audio/2022/05/27/23-51-43-941_200x200.jpg",
        audio = "https://cdn.pixabay.com/download/audio/2022/05/27/audio_1808fbf07a.mp3?filename=lofi-study-112191.mp3",
        duration = (2.minutes + 27.seconds),
        title = "Episode Title 4",
        description = "Explore the complexities of human relationships and the power of love in this heartwarming tale that will make you laugh, cry, and ponder life's mysteries.",
        comments = listOf(),
        categories = listOf()
    ),
    Episode(
        imageUrl = "https://cdn.pixabay.com/audio/2023/03/02/23-27-55-753_200x200.jpg",
        audio = "https://cdn.pixabay.com/download/audio/2023/03/02/audio_501a4aea8a.mp3?filename=genres-hiphop-lofi-141320.mp3",
        duration = (12.minutes + 45.seconds),
        title = "Episode Title 5",
        description = "Uncover the secrets of a small, close-knit community where everyone has something to hide, and the truth might just shatter their peaceful facade.",
        comments = listOf(),
        categories = listOf()
    ),
    Episode(
        imageUrl = "https://cdn.pixabay.com/audio/2022/09/02/21-42-13-13_200x200.jpg",
        audio = "https://cdn.pixabay.com/download/audio/2022/09/02/audio_72502a492a.mp3?filename=empty-mind-118973.mp3",
        duration = (2.minutes + 55.seconds),
        title = "Episode Title 6",
        description = "Embark on a culinary journey through exotic flavors and enticing aromas as you follow a chef's quest for perfection and success in the culinary world.",
        comments = listOf(),
        categories = listOf()
    ),
    Episode(
        imageUrl = "https://cdn.pixabay.com/audio/2022/05/27/23-51-43-941_200x200.jpg",
        audio = "https://cdn.pixabay.com/download/audio/2022/05/27/audio_1808fbf07a.mp3?filename=lofi-study-112191.mp3",
        duration = (2.minutes + 27.seconds),
        title = "Episode Title 7",
        description = "Discover thrilling adventures and unexpected twists in this gripping novel that keeps you on the edge of your seat until the very last page.",
        comments = listOf(),
        categories = listOf()
    ),
    Episode(
        imageUrl = "https://cdn.pixabay.com/audio/2023/03/02/23-27-55-753_200x200.jpg",
        audio = "https://cdn.pixabay.com/download/audio/2023/03/02/audio_501a4aea8a.mp3?filename=genres-hiphop-lofi-141320.mp3",
        duration = (12.minutes + 45.seconds),
        title = "Episode Title 8",
        description = "Immerse yourself in a world of magic and wonder as you follow a young hero's quest to save the kingdom from an ancient evil threatening its existence.",
        comments = listOf(),
        categories = listOf()
    ),
    Episode(
        imageUrl = "https://cdn.pixabay.com/audio/2022/09/02/21-42-13-13_200x200.jpg",
        audio = "https://cdn.pixabay.com/download/audio/2022/09/02/audio_72502a492a.mp3?filename=empty-mind-118973.mp3",
        duration = (2.minutes + 55.seconds),
        title = "Episode Title 9",
        description = "In a post-apocalyptic wasteland, a group of survivors must navigate danger and betrayal to find a new home, hoping for a chance at rebuilding society.",
        comments = listOf(),
        categories = listOf()
    ),
    Episode(
        imageUrl = "https://cdn.pixabay.com/audio/2022/05/27/23-51-43-941_200x200.jpg",
        audio = "https://cdn.pixabay.com/download/audio/2022/05/27/audio_1808fbf07a.mp3?filename=lofi-study-112191.mp3",
        duration = (2.minutes + 27.seconds),
        title = "Episode Title 10",
        description = "Explore the complexities of human relationships and the power of love in this heartwarming tale that will make you laugh, cry, and ponder life's mysteries.",
        comments = listOf(),
        categories = listOf()
    ),
    Episode(
        imageUrl = "https://cdn.pixabay.com/audio/2023/03/02/23-27-55-753_200x200.jpg",
        audio = "https://cdn.pixabay.com/download/audio/2023/03/02/audio_501a4aea8a.mp3?filename=genres-hiphop-lofi-141320.mp3",
        duration = (12.minutes + 45.seconds),
        title = "Episode Title 11",
        description = "Uncover the secrets of a small, close-knit community where everyone has something to hide, and the truth might just shatter their peaceful facade.",
        comments = listOf(),
        categories = listOf()
    ),
    Episode(
        imageUrl = "https://cdn.pixabay.com/audio/2022/09/02/21-42-13-13_200x200.jpg",
        audio = "https://cdn.pixabay.com/download/audio/2022/09/02/audio_72502a492a.mp3?filename=empty-mind-118973.mp3",
        duration = (2.minutes + 55.seconds),
        title = "Episode Title 12",
        description = "Embark on a culinary journey through exotic flavors and enticing aromas as you follow a chef's quest for perfection and success in the culinary world.",
        comments = listOf(),
        categories = listOf()
    ),
)