package com.abduxalil.dev.mynewsapi.presenter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.abduxalil.dev.mynewsapi.R
import com.abduxalil.dev.mynewsapi.domain.entity.NewsRepo
import com.abduxalil.dev.mynewsapi.presenter.navigation.MyNavigator
import com.abduxalil.dev.mynewsapi.presenter.theme.MyNewsApiTheme
import io.ktor.client.engine.android.Android
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewsRepoScreen(
    viewModel: NewsViewModel = koinViewModel<NewsViewModel>(),
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NewsRepoContent(
        uiState = uiState,
        onRetry = { viewModel.onFetchNewsRepos(uiState.inputText) },
        onUpdateText = { inputText -> viewModel.onFetchNewsRepos(inputText) },
        navController = navController // Pass NavController here
    )
}

@Composable
private fun NewsRepoContent(
    uiState: NewsState,
    onRetry: () -> Unit,
    onUpdateText: (String) -> Unit,
    navController: NavController // Add NavController parameter
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Gray)
                .padding(innerPadding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                SearchBarNews(
                    uiState = uiState,
                    onUpdateText = { inputText -> onUpdateText(inputText) })
            }
            when {
                uiState.isLoading -> {
                    LoadingIndicator(modifier = Modifier.align(Alignment.Center))
                }

                uiState.isError -> {
                    ErrorContent(modifier = Modifier.align(Alignment.Center), onRetry = onRetry)
                }

                uiState.newsRepos.isEmpty() -> {
                    NoContentMessage(modifier = Modifier.align(Alignment.Center))
                }

                else -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(modifier = Modifier.padding(top = 70.dp)) {
                            RepoList(repos = uiState.newsRepos, navController = navController) // Pass NavController here
                        }
                    }
                }
            }
        }
    }
}

// Composable to display a loading indicator
@Composable
private fun LoadingIndicator(modifier: Modifier) {
    CircularProgressIndicator(modifier = modifier)
}

// Composable to show a "No Content" message
@Composable
private fun NoContentMessage(modifier: Modifier) {
    Text(
        text = stringResource(R.string.content_empty),
        color = Color.Black,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

// Error content message
@Composable
private fun ErrorContent(modifier: Modifier, onRetry: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.error_message),
            color = Color.Red,
            style = MaterialTheme.typography.bodyMedium
        )
        Button(
            onClick = onRetry,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = stringResource(R.string.retry))
        }
    }
}

//================================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarNews(
    uiState: NewsState,
    onUpdateText: (String) -> Unit
) {
    TextField(
        value = uiState.inputText,
        onValueChange = { text ->
            if (text.length <= 20) {
                onUpdateText(text)
            }
        },
        singleLine = true,
        placeholder = { Text("Search News...") },
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = Color.Black
        ),
        shape = RoundedCornerShape(24.dp),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.Gray,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray
            )
        },
        trailingIcon = {
            if (uiState.inputText.isNotEmpty()) {
                IconButton(onClick = { onUpdateText("") }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Clear text"
                    )
                }
            }
        }
    )
}

//==================================
// Composable to show the list of repositories
@Composable
private fun RepoList(repos: List<NewsRepo>, navController: NavController) {
    LazyColumn {
        items(repos) { repo ->
            RepoItem(repo = repo, navController = navController) // Pass NavController here
        }
    }
}

@Composable
private fun RepoItem(repo: NewsRepo, navController: NavController) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
            .clickable {
                navController.navigate("repoDetail/${repo.description}/${repo.id}/${repo.url}")
            }
    ) {
        Text(
            text = repo.name,
            color = Color.Black,
            style = MaterialTheme.typography.headlineSmall,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Text(
            text = repo.description,
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
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
                .padding(top = 40.dp)
                .size(260.dp)
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun MyNewsApiProject() {
    MyNewsApiTheme {
        MyNavigator()
    }
}