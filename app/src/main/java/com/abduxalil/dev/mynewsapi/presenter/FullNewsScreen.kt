package com.abduxalil.dev.mynewsapi.presenter

import android.service.autofill.CustomDescription
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import coil.compose.rememberImagePainter
import com.abduxalil.dev.mynewsapi.R
import com.abduxalil.dev.mynewsapi.domain.entity.NewsRepo


@Composable
fun RepoDetailScreen(
    repoId: String?,
    repoDescription: String?,
    repoUrl: String? = "",
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
        Log.d("TAGss", "RepoDetailScreen: $repoId $repoUrl")
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imagePainter = rememberImagePainter(
                data = repoUrl,
                builder = {
                    crossfade(true)
                }
            )

            Image(
                painter = imagePainter,
                contentDescription = "Repo Image",
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(260.dp)
            )
            Text(text = "Repo ID: $repoId")
            Log.d("Tag", "all: ${repoId}/")
            Text(
                text = repoDescription.toString(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 20.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = repoDescription.toString(),
                textAlign = TextAlign.Start,
                fontSize = 16.sp,

                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}

