package com.example.tryggaklassenpod.externalResources


import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature


class YouTubeIframeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val webView = WebView(this)
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        val iframeCode = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/Li1BjZg0PD4\" frameborder=\"0\" allowfullscreen></iframe>"

        webView.loadData(iframeCode, "text/html", "utf-8")
        setContentView(webView)
    }
}


@Composable
fun callYoutubeInstance(){
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(315.dp), // Adjust height as needed
        factory = { context ->
            val webView = WebView(context)

            if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
                WebSettingsCompat.setForceDark(
                    webView.settings,
                    WebSettingsCompat.FORCE_DARK_ON
                )
            }

            webView.loadData(
                """
                    <iframe
                        width="560"
                        height="315"
                        src="https://www.youtube.com/embed/Li1BjZg0PD4?si=ZJaicsCSHW-gmfYS"
                        title="YouTube video player"
                        frameborder="0"
                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                        allowfullscreen
                    ></iframe>
                    """.trimIndent(),
                "text/html",
                "utf-8"
            )

            webView
        }
    )
}