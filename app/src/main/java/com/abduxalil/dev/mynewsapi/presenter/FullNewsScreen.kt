package com.abduxalil.dev.mynewsapi.presenter

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.rememberImagePainter
import com.abduxalil.dev.mynewsapi.domain.entity.NewsRepo
import com.abduxalil.dev.mynewsapi.presenter.theme.MyNewsApiTheme

class FullNewsScreen(private val repo: NewsRepo) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            IconButton(onClick = { navigator.pop() }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val imagePainter = rememberImagePainter(
                    data = repo.url,
                    builder = {
                        crossfade(true)
                    }
                )
                Image(
                    painter = imagePainter,
                    contentDescription = "Repo Image",
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = repo.name,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(top = 15.dp)
                )
                Text(
                    text = repo.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 10.dp)
                )
            }

        }
    }
}

